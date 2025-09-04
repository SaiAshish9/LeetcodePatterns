#include <stdio.h>
#include <stdlib.h>

int comparator(const void *a, const void *b)
{
    int *x = *(int **)a;
    int *y = *(int **)b;
    return x[0] - y[0];
}

int **merge(int **intervals, int n, int *returnSize)
{
    if (n == 0)
    {
        *returnSize = 0;
        return NULL;
    }

    qsort(intervals, n, sizeof(int *), comparator);

    int **merged = (int **)malloc(n * sizeof(int *));
    int index = 0;

    merged[index] = (int *)malloc(2 * sizeof(int));
    merged[index][0] = intervals[0][0];
    merged[index][1] = intervals[0][1];

    for (int i = 1; i < n; i++)
    {
        int start = intervals[i][0];
        int end = intervals[i][1];

        if (start <= merged[index][1])
        {
            if (end > merged[index][1])
                merged[index][1] = end;
        }
        else
        {
            index++;
            merged[index] = (int *)malloc(2 * sizeof(int));
            merged[index][0] = start;
            merged[index][1] = end;
        }
    }

    *returnSize = index + 1;
    return merged;
}

int main()
{
    int a1[] = {1, 3};
    int a2[] = {2, 6};
    int a3[] = {8, 10};
    int a4[] = {15, 18};

    int *intervals[4];
    intervals[0] = a1;
    intervals[1] = a2;
    intervals[2] = a3;
    intervals[3] = a4;

    int returnSize;
    int **result = merge(intervals, 4, &returnSize);

    printf("[");
    for (int i = 0; i < returnSize; i++)
    {
        printf("[%d, %d]", result[i][0], result[i][1]);
        if (i != returnSize - 1)
            printf(", ");
        free(result[i]);
    }
    printf("]\n");

    free(result);
    return 0;
}

#include <stdio.h>
#include <stdlib.h>

int comparator(const void *a, const void *b)
{
    const int *x = (const int *)a;
    const int *y = (const int *)b;
    return x[0] - y[0];
}

int **merge(int intervals[][2], int n, int *returnSize)
{
    if (n == 0)
    {
        *returnSize = 0;
        return NULL;
    }

    qsort(intervals, n, sizeof(intervals[0]), comparator);

    int **merged = (int **)malloc(n * sizeof(int *));
    int idx = 0;
    merged[idx] = (int *)malloc(2 * sizeof(int));
    merged[idx][0] = intervals[0][0];
    merged[idx][1] = intervals[0][1];

    for (int i = 1; i < n; i++)
    {
        int start = intervals[i][0];
        int end = intervals[i][1];

        if (start <= merged[idx][1])
        {
            if (end > merged[idx][1])
                merged[idx][1] = end;
        }
        else
        {
            idx++;
            merged[idx] = (int *)malloc(2 * sizeof(int));
            merged[idx][0] = start;
            merged[idx][1] = end;
        }
    }

    *returnSize = idx + 1;
    return merged;
}

int main()
{
    int intervals[4][2] = {
        {1, 3},
        {2, 6},
        {8, 10},
        {15, 18}};

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

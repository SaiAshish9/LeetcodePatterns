/*
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Brute Force: O(nlogn) + O(n^2) time | O(n) space
1. Sort the intervals based on the starting time.   O(nlogn)
2. Create a result list to store the merged intervals.
3. Iterate through the sorted intervals and for each interval:   
    a. If the result list is empty or the current interval does not overlap with the last interval in the result list, add it to the result list.
    b. If there is an overlap, merge the current interval with the last interval in the result list by updating the end time of the last interval to be the maximum of the two end times.   O(n^2)
4. Return the result list as an array. O(n) space for the result list.

Optimal Approach: O(nlogn) time | O(n) space
1. Sort the intervals based on the starting time.   O(nlogn)                            
2. Create a result list to store the merged intervals.
3. Initialize a variable to keep track of the current interval being merged.
4. Iterate through the sorted intervals starting from the second interval:
    a. If the current interval overlaps with the next interval, update the end time of the current interval to be the maximum of the two end times.
    b. If there is no overlap, add the current interval to the result list and update the current interval to be the next interval.  O(n)               
5. After the loop, add the last current interval to the result list.    O(n)        
6. Return the result list as an array. O(n) space for the result list.    

Dry Run:    
1. Sort the intervals: [[1,3],[2,6],[8,10],[15,18]] -> [[1,3],[2,6],[8,10],[15,18]]
2. Initialize result list: []
3. Set current interval to [1,3]
4. Iterate through intervals:
    a. Compare [1,3] with [2,6]: Overlap exists, update current interval to [1,6]
    b. Compare [1,6] with [8,10]: No overlap, add [1,6] to result list, update current interval to [8,10]
    c. Compare [8,10] with [15,18]: No overlap, add [8,10] to result list, update current interval to [15,18]
5. Add last current interval [15,18] to result list: [[1,6],[8,10],[15,18]]
6. Return result list as array: [[1,6],[8,10],[15,18]]
 */

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

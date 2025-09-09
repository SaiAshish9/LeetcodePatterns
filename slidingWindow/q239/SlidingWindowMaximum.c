#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int *data;
    int front, rear, size, capacity;
} Deque;

Deque *createDeque(int capacity)
{
    Deque *dq = (Deque *)malloc(sizeof(Deque));
    dq->data = (int *)malloc(sizeof(int) * capacity);
    dq->front = 0;
    dq->rear = -1;
    dq->size = 0;
    dq->capacity = capacity;
    return dq;
}

int isEmpty(Deque *dq)
{
    return dq->size == 0;
}

int front(Deque *dq)
{
    return dq->data[dq->front];
}

int back(Deque *dq)
{
    return dq->data[dq->rear];
}

void popFront(Deque *dq)
{
    if (!isEmpty(dq))
    {
        dq->front = (dq->front + 1) % dq->capacity;
        dq->size--;
    }
}

void popBack(Deque *dq)
{
    if (!isEmpty(dq))
    {
        dq->rear = (dq->rear - 1 + dq->capacity) % dq->capacity;
        dq->size--;
    }
}

void pushBack(Deque *dq, int value)
{
    dq->rear = (dq->rear + 1) % dq->capacity;
    dq->data[dq->rear] = value;
    dq->size++;
}

int *maxSlidingWindow(int *nums, int n, int k, int *returnSize)
{
    if (n == 0 || k == 0)
    {
        *returnSize = 0;
        return NULL;
    }
    if (k == 1)
    {
        *returnSize = n;
        int *res = (int *)malloc(sizeof(int) * n);
        for (int i = 0; i < n; i++)
            res[i] = nums[i];
        return res;
    }

    Deque *dq = createDeque(n);
    int *res = (int *)malloc(sizeof(int) * (n - k + 1));
    *returnSize = 0;

    for (int i = 0; i < n; i++)
    {
        while (!isEmpty(dq) && front(dq) < i - k + 1)
        {
            popFront(dq);
        }
        while (!isEmpty(dq) && nums[back(dq)] <= nums[i])
        {
            popBack(dq);
        }
        pushBack(dq, i);

        if (i - k + 1 >= 0)
        {
            res[*returnSize] = nums[front(dq)];
            (*returnSize)++;
        }
    }
    free(dq->data);
    free(dq);
    return res;
}

int main()
{
    int nums[] = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;
    int n = sizeof(nums) / sizeof(nums[0]);
    int returnSize;

    int *result = maxSlidingWindow(nums, n, k, &returnSize);

    printf("[");
    for (int i = 0; i < returnSize; i++)
    {
        printf("%d", result[i]);
        if (i < returnSize - 1)
            printf(", ");
    }
    printf("]\n");

    free(result);
    return 0;
}

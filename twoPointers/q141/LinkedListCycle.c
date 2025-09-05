#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdlib.h>

struct ListNode
{
    int val;
    struct ListNode *next;
};

struct ListNode *createNode(int val)
{
    struct ListNode *node = (struct ListNode *)malloc(sizeof(struct ListNode));
    node->val = val;
    node->next = NULL;
    return node;
}

// Detect cycle using Floyd's Tortoise and Hare
bool hasCycle(struct ListNode *head)
{
    if (head == NULL || head->next == NULL)
    {
        return false;
    }
    struct ListNode *slow = head;
    struct ListNode *fast = head->next;

    while (fast != NULL && fast->next != NULL)
    {
        if (slow == fast)
        {
            return true;
        }
        slow = slow->next;
        fast = fast->next->next;
    }
    return false;
}

int main()
{
    struct ListNode *head = createNode(3);
    head->next = createNode(2);
    head->next->next = createNode(0);
    head->next->next->next = createNode(-4);
    head->next->next->next->next = head->next;

    if (hasCycle(head))
    {
        printf("true\n");
    }
    else
    {
        printf("false\n");
    }

    return 0;
}

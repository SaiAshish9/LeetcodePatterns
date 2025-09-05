/*
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Brute Force: HashSet to store visited nodes. If we encounter a node already in the set, there's a cycle.
Time Complexity: O(N) Space Complexity: O(N)

Optimal Approach: Floyd's Cycle Detection Algorithm (Tortoise and Hare)
1. Initialize two pointers, slow and fast. Both start at the head of the linked list.
2. Move the slow pointer one step at a time (slow = slow.next).
3. Move the fast pointer two steps at a time (fast = fast.next.next).
4. If there is no cycle, the fast pointer will eventually reach the end of the list (null).
5. If there is a cycle, at some point, the fast pointer will meet the slow pointer.
6. If slow == fast, return true (cycle detected).
Time Complexity: O(N) Space Complexity: O(1)

Dry Run:
1. Initialize slow and fast pointers at the head of the linked list.
2. Move slow pointer one step and fast pointer two steps in each iteration.
3. If slow and fast pointers meet, return true (cycle detected).
*/

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

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

#include <iostream>
using namespace std;
struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x) : val(x), next(nullptr) {}
};

// Detect cycle using Floyd's Tortoise and Hare
bool hasCycle(ListNode* head) {
    if (head == nullptr || head->next == nullptr) {
        return false;
    }
    ListNode* slow = head;
    ListNode* fast = head->next;
    while (fast != nullptr && fast->next != nullptr) {
        if (slow == fast) {
            return true;
        }
        slow = slow->next;
        fast = fast->next->next;
    }
    return false;
}

int main() {
    // Create linked list: 3 -> 2 -> 0 -> -4
    ListNode* head = new ListNode(3);
    head->next = new ListNode(2);
    head->next->next = new ListNode(0);
    head->next->next->next = new ListNode(-4);
    head->next->next->next->next = head->next;
    cout << (hasCycle(head) ? "true" : "false") << endl;
    return 0;
}


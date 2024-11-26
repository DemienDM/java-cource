package org.example.homework01.reverselinkedlist;

public class ListNode {
    @SuppressWarnings("checkstyle:VisibilityModifier")
    int val;

    @SuppressWarnings("checkstyle:VisibilityModifier")
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

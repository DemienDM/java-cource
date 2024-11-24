package org.example.homework01.reverseLinkedList;

public class Main {
    public static void main(String[] args) {

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        head = reverseList(head);

        ListNode tmp = head;
        while(tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }

    private static ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
package com.raj.linkedlist;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class ReverseList {

    class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null; }
    }

    public ListNode reverseList(ListNode a) {
        return reverseLL(null, a);
    }

    public ListNode reverseLL(ListNode prev, ListNode curr) {
        if (curr == null) return prev;
        ListNode next = curr.next;
        curr.next = prev;
        return reverseLL(curr, next);
    }

    public ListNode reverseListOptimal(ListNode A) {

        ListNode node, prev, temp;

        node = A;

        if (node == null)
            return null;

        prev = null;

        while (node != null) {

            temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;

        }

        return prev;
    }

    public static void main(String[] args) {

    }
}

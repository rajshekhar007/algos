package com.raj.linkedlist;

import com.raj.linkedlist.base.ListNode;

/**
 * Created by rshekh1 on 6/16/16.
 */
public class LLIntersection {



    public ListNode getIntersectionNode(ListNode a, ListNode b) {

        int sizeA = 0, sizeB = 0;

        ListNode n = a;
        while (n != null) {
            sizeA++;
            n = n.next;
        }

        n = b;
        while (n != null) {
            sizeB++;
            n = n.next;
        }

        while (sizeA > sizeB) {
            a = a.next;
            sizeA--;
        }

        while (sizeB > sizeA) {
            b = b.next;
            sizeB--;
        }

        while (a != null && b!= null) {
            if (a == b) return a;
            a = a.next; b = b.next;
        }

        return null;
    }

    public ListNode reverseLL(ListNode prev, ListNode curr) {
        if (curr == null) return prev;
        ListNode next = curr.next;
        curr.next = prev;
        return reverseLL(curr, next);
    }

    public static void main(String[] args) {
        LLIntersection l = new LLIntersection();
        ListNode a = new ListNode(11);
        a.next = new ListNode(12);
        a.next.next = new ListNode(13);
        a.next.next.next = new ListNode(14);
        a.next.next.next.next = new ListNode(15);
        a.next.next.next.next.next = new ListNode(16);

        ListNode r = l.reverseLL(null, a);

        ListNode b = new ListNode(21);
        b.next = new ListNode(22);
        b.next.next = a.next.next.next.next;
        System.out.println(l.getIntersectionNode(a, b));
    }

}

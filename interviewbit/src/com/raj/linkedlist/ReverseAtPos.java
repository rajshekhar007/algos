package com.raj.linkedlist;

import com.raj.linkedlist.base.LinkedList;
import com.raj.linkedlist.base.Node;

public class ReverseAtPos extends LinkedList {

    /**
     * Reverse a linked list from position m to n. Do it in-place and in one-pass.

     For example:
     Given 1->2->3->4->5->NULL, m = 2 and n = 4,

     return 1->4->3->2->5->NULL.

     * @param start
     * @param m
     * @param n
     * @return
     */
    public Node reverseAt(Node start, int m, int n) {
        Node start_ = getNodeAt(m-1);
        Node end_ = getNodeAt(n+1);
        start_.next = reverseAt_m_n(start_.next, n-m+1);
        while (start_.next != null) start_ = start_.next;  // get to end
        start_.next = end_;  // now set reversed tail to rest of the original list after nth pos
        return start;
    }

    private Node reverseAt_m_n(Node start, int dist) {
        Node prev = null, n = start, cur = null;
        while (n != null && dist-- > 0) {
            cur = n;
            n = n.next;
            cur.next = prev;
            prev = cur;
        }
        return prev;
    }

    public static void main(String[] args) {
        ReverseAtPos reverseAtPos = new ReverseAtPos();
        reverseAtPos.addNode(1);
        reverseAtPos.addNode(2);
        reverseAtPos.addNode(3);
        reverseAtPos.addNode(4);
        reverseAtPos.addNode(5);
        printLL(reverseAtPos.startN);
        reverseAtPos.reverseAt(reverseAtPos.startN, 1,4);
        printLL(reverseAtPos.startN);
    }
}

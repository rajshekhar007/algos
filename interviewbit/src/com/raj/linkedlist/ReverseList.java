package com.raj.linkedlist;

import com.raj.linkedlist.base.LinkedList;
import com.raj.linkedlist.base.Node;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class ReverseList extends LinkedList {

    public Node reverseList(Node a) {
        return reverseLL(null, a);
    }

    public Node reverseLL(Node prev, Node curr) {
        if (curr == null) return prev;
        Node next = curr.next;
        curr.next = prev;
        return reverseLL(curr, next);
    }

    public Node reverse(Node start) {
        Node prev = null, n = start;
        while(n != null) {
            Node curr = n;
            n = n.next;
            curr.next = prev;
            prev = curr;
        }
        return prev;
    }

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        reverseList.addNode(1);
        reverseList.addNode(2);
        reverseList.addNode(3);
        reverseList.addNode(4);
        printLL(reverseList.startN);
        reverseList.startN = reverseList.reverse(reverseList.startN);
        System.out.println();
        printLL(reverseList.startN);
    }
}

package com.raj.linkedlist;

import com.raj.linkedlist.base.LinkedList;
import com.raj.linkedlist.base.Node;

public class IsPalindromeLL extends LinkedList {

    public static int isPalin(Node A) {
        Node start = A;
        // get size of LL
        int size = 0;
        while (A != null) {
            size ++;
            A = A.next;
        }
        size /= 2;
        A = start;
        while (size-- > 0) A = A.next;
        Node end = reverse(A);
        while (start != null && end != null && start != end) { // 1-> 2-> 3 <-2 <-1
            if (start.val != end.val) return 0;
            start = start.next;
            end = end.next;
        }
        return 1;
    }

    private static Node reverse(Node start) {
        Node prev = null, n = start;
        while (n != null) {
            Node curr = n;
            n = n.next;
            curr.next = prev;
            prev = curr;
        }
        return prev;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(2);
        linkedList.addNode(1);
        printLL(linkedList.startN);
        System.out.println(isPalin(linkedList.startN));
    }
}

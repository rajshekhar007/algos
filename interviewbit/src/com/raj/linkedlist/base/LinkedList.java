package com.raj.linkedlist.base;

public class LinkedList {

    public Node startN = null, currentN = null;

    public Node addNode(int val) {
        Node n = new Node(val);
        if (currentN != null) {
            currentN.next = n;
            currentN = n;
        }
        if (startN == null) {
            startN = n;
            currentN = n;
        }
        return n;
    }

    public Node getNodeAt(int pos) {
        Node tmp = startN;
        while (--pos > 0) tmp = tmp.next;
        return tmp;
    }

    public static void printLL(Node n) {
        if (n == null) {
            System.out.println("null");
            return;
        }
        System.out.print(n.val + " -> ");
        printLL(n.next);
    }

    @Override
    public String toString() {
        printLL(startN);
        return "";
    }
}

package com.raj.linkedlist.base;

public class Node {
    public int val;
    public Node next;
    Node(int x) {
        val = x; next = null;
    }

    @Override
    public String toString() {
        return "" + val; //+ " -> " + next;
    }
}

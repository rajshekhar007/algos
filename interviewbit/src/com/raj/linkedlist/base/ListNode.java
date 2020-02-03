package com.raj.linkedlist.base;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; next = null; }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper("N")
                .add("val", val)
                .add("->", next)
                .toString();
    }
}

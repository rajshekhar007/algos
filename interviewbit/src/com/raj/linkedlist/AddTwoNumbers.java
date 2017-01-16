package com.raj.linkedlist;

/**
 * Created by rshekh1 on 6/16/16.
 */
public class AddTwoNumbers {

    static class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null; }

        @Override
        public String toString() {
            return com.google.common.base.Objects.toStringHelper("N")
                    .add("val", val)
                    .add("->", next)
                    .toString();
        }
    }

    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode res = null, startRes = null;
        int c = 0;
        while (a!=null || b!=null || c>0) {
            int sum = 0;

            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }

            sum += c;
            c = 0;
            if (sum >= 10) {
                c = 1;
                sum -= 10;
            }
            if (res == null) {
                res = new ListNode(sum);
                startRes = res;
            }
            else {
                res.next = new ListNode(sum);
                res = res.next;
            }
        }
        return startRes;
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode a = new ListNode(1);
        a.next = new ListNode(7);
        a.next.next = new ListNode(6);
        a.next.next.next = new ListNode(4);

        ListNode b = new ListNode(2);
        b.next = new ListNode(5);
        b.next.next = a.next.next.next.next;
        System.out.println(addTwoNumbers.addTwoNumbers(a,b));
    }
}

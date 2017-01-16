package com.raj;

class Josephus {

    static class LL {
        public int size;
        public Node start, current;

        public void addNode(Node n) {
            if (n == null) return;
            if (size == 0) {
                start = n;
                current = start;
                size++;
                return;
            }
            current.next = n;
            current = current.next;
            size++;
        }

        public void deleteNextNode() {
            if (current.next == null || current.next.next == null) return;
            Node nextNode = current.next.next;
            current.next = nextNode;
            current = nextNode;
            size--;
        }

        /*@Override
        public String toString() {
            Node n = start;
            String str = "";
            int tempSize = 0;
            while (n != null && tempSize < this.size) {
                str += n.toString();
                n = n.next;
            }
            return str;
        }*/
    }

    static class Node {
        public int data;
        public Node next;

        public Node(int i) {
            this.data = i;
        }

        /*@Override
        public String toString() {
            return data + " ";
        }*/
    }

    public static void main(String[] args) {

        LL ll = new LL();

        for (int i = 1; i <= 100; i++) {
            ll.addNode(new Node(i));
        }

        //System.out.println(ll);

        ll.current.next = ll.start; //circular

        ll.current = ll.start;
        while (ll.size > 1) {
            ll.deleteNextNode();
        }
        System.out.println(ll.current.data);
    }
}
package com.raj.linkedlist;

import com.raj.linkedlist.base.LinkedList;
import com.raj.linkedlist.base.Node;

public class DetectLoopFloydAlgo extends LinkedList {
    /**
     * https://cs.stackexchange.com/questions/10360/floyds-cycle-detection-algorithm-determining-the-starting-point-of-cycle
     */
    public Node detectLoopStartPoint(Node start) {
        if (start == null || start.next == null) return null;
        if (start == start.next) return start; // node pointing to self
        Node slow = start, fast = start;
        // detect loop
        while (slow == start || slow != fast) {
            slow = slow.next;
            if (slow == null || fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
        }
        /**
         * It's a loop and we are at the meeting point.
         *
         * if,
         * x = distance from start to loop starting point,
         * y = dist from loop start point to meeting point,
         * z = dist from meeting point to loop start point
         *
         * slow distance = x+y
         * fast dist = x+y+z+y
         *
         * 2 * slow dist = fast dist
         * or, 2(x+y) = x+2y+z
         * or, x = z
         *
         * Hence, reset one pointer to start, and move them one at a time until they meet
         * to find the loop starting point
         */
        slow = start;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        DetectLoopFloydAlgo detectLoopFloydAlgo = new DetectLoopFloydAlgo();
        detectLoopFloydAlgo.addNode(1);
        detectLoopFloydAlgo.addNode(2);
        Node m = detectLoopFloydAlgo.addNode(3);
        detectLoopFloydAlgo.addNode(4);
        detectLoopFloydAlgo.addNode(5).next = m;
        //printLL(detectLoopFloydAlgo.startN);
        System.out.println(detectLoopFloydAlgo.detectLoopStartPoint(detectLoopFloydAlgo.startN));
    }
}

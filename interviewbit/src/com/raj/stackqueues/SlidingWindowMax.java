package com.raj.stackqueues;

import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class SlidingWindowMax {

    /**
     * Good Solution:
     * A heap data structure quickly comes to mind. We could boost the run time to approximately O(n lg w) (Note that I said approximately because the size of the heap changes constantly and averages about w). Insert operation takes O(lg w) time, where w is the size of the heap. However, getting the maximum value is cheap, it merely takes constant time as the maximum value is always kept in the root (head) of the heap.
     * As the window slides to the right, some elements in the heap might not be valid anymore (range is outside of the current window). How should you remove them? You would need to be somewhat careful here. Since you only remove elements that are out of the window’s range, you would need to keep track of the elements’ indices too.
     * TIme complexity : O ( n log n ). Not good enough.

     * Optimal Solution:
     * The double-ended queue is the perfect data structure for this problem. It supports insertion/deletion from the front and back.
     * The trick is to find a way such that the largest element in the window would always appear in the front of the queue.
     * How would you maintain this requirement as you push and pop elements in and out of the queue?
     * You might notice that there are some redundant elements in the queue that we shouldn’t even consider about.
     * For example, if the current queue has the elements: [10 5 3], and a new element in the window has the element 11.
     * Now, we could have emptied the queue without considering elements 10, 5, and 3, and insert only element 11 into the queue.
     *
     * A natural way most people would think is to try to maintain the queue size the same as the window’s size.
     * Try to break away from this thought and try to think outside of the box. Removing redundant elements and storing
     * only elements that need to be considered in the queue is the key to achieve the efficient O(n) solution.
     *
     * @param A
     * @param B
     * @return
     */
    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {

        ArrayList<Integer> res = new ArrayList<>();
        int window = Math.min(A.size(), B);

        // 2-way Q DS to maintain running max for window size. We need to operate on max(front) as well as back for removing unwanted items
        Deque<Node> doubleEndedQ = new LinkedList<>();

        int i;

        for (i = 0; i < window - 1; i++) {
            // Remove older items if we find a new greater item (the idea is, it will be last man standing and be max too)
            while (!doubleEndedQ.isEmpty() && A.get(i) >= doubleEndedQ.peekFirst().value) {
                doubleEndedQ.pollFirst();
            }
            // Now add the new item
            doubleEndedQ.addFirst(new Node(i, A.get(i)));
        }

        for (; i < A.size(); i++) {

            // Truncate Q from back(front has greater items of interest) until sized to window
            while (!doubleEndedQ.isEmpty() && (i - doubleEndedQ.peekLast().index >= window)) {
                doubleEndedQ.pollLast();
            }

            // Remove older items if we find a new greater item (the idea is, it will be last man standing and be max too)
            while (!doubleEndedQ.isEmpty() && A.get(i) >= doubleEndedQ.peekFirst().value) {
                doubleEndedQ.pollFirst();
            }

            // Now add the new item to the front - it will either be max(if Q became empty) or the items in coming order(ordering is required to remove them FIFO manner)
            doubleEndedQ.addFirst(new Node(i, A.get(i)));

            // The last element in Q is the max for the sliding window(items are always added to front)
            res.add(doubleEndedQ.peekLast().value);
        }

        return res;
    }

    class Node {
        int value;
        int index;

        public Node(int index, int val) {
            this.index = index;
            this.value = val;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("val", value)
                    .add("index", index)
                    .toString();
        }
    }

    public static void main(String[] args) {
        SlidingWindowMax s = new SlidingWindowMax();
        System.out.println(s.slidingMaximum(Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7), 3));
    }

}

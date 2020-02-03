package com.raj.stackqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 Given an array, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.

 More formally,

 G[i] for an element A[i] = an element A[j] such that
 j is maximum possible AND
 j < i AND
 A[j] < A[i]
 Elements for which no smaller element exist, consider next smaller element as -1.

 Example:

 Input : A : [4, 5, 2, 10, 8]
 Return : [-1, 4, -1, 2, 2]

 Example 2:

 Input : A : [3, 2, 1]
 Return : [-1, -1, -1]

 [ 34, 35, 27, 42, 5, 28, 39, 20, 28 ]
 [ -1, 34, -1, 27,-1,  5, 28,  5, 20 ]

 */
public class NearestSmallElement {

    public List<Integer> findNearestSmall(int[] A) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i : A) {
            boolean isFound = false;
            if (!stack.isEmpty()) {   // try to find the nearest smallest number, by looking through stack elements
                int size = stack.size();
                while (--size >= 0) {
                    if (stack.elementAt(size) < i) {
                        res.add(stack.elementAt(size));
                        isFound = true;
                        break;
                    }
                }
            }
            if (!isFound) res.add(-1);  // if not found, assign -1
            stack.push(i);  // add element to stack
        }
        return res;
    }

    /**
     * <<<< No need to store everything in stack >>>>
     1) Create a new empty stack st
     2) Iterate over array `A`,
     where `i` goes from `0` to `size(A) - 1`.
     a) We are looking for value just smaller than `A[i]`. So keep popping from `st` till elements in `st.top() >= A[i]` or st becomes empty.
     b) If `st` is non empty, then the top element is our previous element. Else the previous element does not exist.
     c) push `A[i]` onto st

     * @param
     */
    public List<Integer> findNearestSmallOptimized(int[] A) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i : A) {
            boolean isFound = false;
            if (!stack.isEmpty()) {
                // Pop anything that's greater than i, as we are looking for things smaller than i
                if (stack.peek() >= i) stack.pop();
            }
            if (!isFound) res.add(-1);  // if not found, assign -1
            stack.push(i);  // add element to stack
        }
        return res;
    }

    public static void main(String[] args) {
        NearestSmallElement n = new NearestSmallElement();
        System.out.println(n.findNearestSmall(new int[]{34, 35, 27, 42, 5, 28, 39, 20, 28}));
    }

}

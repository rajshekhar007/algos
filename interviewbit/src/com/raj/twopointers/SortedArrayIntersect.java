package com.raj.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class SortedArrayIntersect {

    /**
     * Find the intersection of two sorted arrays.
     OR in other words,
     Given 2 sorted arrays, find all the elements which occur in both the arrays.

     Example :

     Input :
     A : [1 2 3 3 4 5 6]
     B : [3 3 5]

     Output : [3 3 5]

     Input :
     A : [1 2 3 3 4 5 6]
     B : [3 5 7]

     Output : [3 5]
     */
    public ArrayList<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        ArrayList<Integer> res = new ArrayList<>();
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) return res;
        int i=0, j=0;
        while (i<a.size() && j<b.size()) {
            int A = a.get(i), B = b.get(j);
            if (A == B) {res.add(A); i++; j++;}
            if (A < B) i++;
            if (A > B) j++;
        }
        return res;
    }
}

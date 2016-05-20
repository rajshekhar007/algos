package com.raj.arrays;

import java.util.ArrayList;

/**
 * Created by rshekh1 on 5/1/16.
 */
public class MaxNonNegativeSubArray {

    public ArrayList<Integer> maxset(ArrayList<Integer> a) {
        ArrayList<Integer> res = new ArrayList<>();
        if (a == null || a.isEmpty()) return res;
        double max = -1d, currentMax = 0d;
        Integer maxIdx = null, currentMaxIdx = null, i = null, j = null, cI = null, cJ = null;
        for (int k=0; k<a.size(); k++) {
            int n = a.get(k);

            if (n < 0) {  // reset local maxima if -ve
                cI = null; cJ = null;
                currentMax = 0d;
                continue;
            }

            if (cI == null) cI = k;  // check if cI was reset, init it
            cJ = k;
            currentMaxIdx = cJ - cI;
            currentMax += n;

            if (currentMax == max && i != null) {
                if (currentMaxIdx > maxIdx) {
                    i = cI; j = cJ;
                    currentMaxIdx = j-i;
                }
            }

            if (currentMax > max) {
                i = cI; j = cJ;
                max = currentMax;
                maxIdx = currentMaxIdx;
            }
        }

        if (i == null) return res;
        for (int k=i; k<=j; k++) {
            res.add(a.get(k));
        }

        return res;
    }

    public static void main(String[] args) {
        MaxNonNegativeSubArray m = new MaxNonNegativeSubArray();
        ArrayList<Integer> a = new ArrayList<>();
        int [] ints = {0, 0, -1, 0};//{-1,-2,-1}; //{1, 2, 5, -7, 2, 5};
        for (int i:ints) a.add(i);
        System.out.println(m.maxset(a));
    }
}

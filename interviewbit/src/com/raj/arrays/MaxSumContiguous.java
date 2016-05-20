package com.raj.arrays;

import java.util.List;

/**
 * Created by rshekh1 on 4/23/16.
 */
public class MaxSumContiguous {

    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

     For example:

     Given the array [-2,1,-3,4,-1,2,1,-5,4],

     the contiguous subarray [4,-1,2,1] has the largest sum = 6.

     For this problem, return the maximum sum.
     */

    public int maxSubArray(final List<Integer> a) {

        if (a == null || a.size() == 0) return 0;

        int prevMax = a.get(0), currentMax = a.get(0);

        for (int i = 1; i < a.size(); i++) {

            currentMax += a.get(i);

            if (a.get(i) >= currentMax) {
                currentMax = a.get(i);
            }

            if (currentMax > prevMax) {
                prevMax = currentMax;
            }
        }
        return prevMax;
    }
}

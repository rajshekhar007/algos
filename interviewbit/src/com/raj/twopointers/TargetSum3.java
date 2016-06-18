package com.raj.twopointers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class TargetSum3 {

    /**
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
     Return the sum of the three integers.
     You may assume that each input would have exactly one solution.

     Example:
     given array S = {-1 2 1 -4},
     and target = 1.

     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)

     Algo:
     1. sort array
     2. iterate i till length of array
        2.1 Follow same aprroach as in SortedArraySum
        2.2 Keep updating bestSum near to target
     */

    public int threeSumClosest(List<Integer> a, int b) {
        if (a == null || a.isEmpty()) return -1;
        Collections.sort(a);
        int absDiffSum = 999999999, actualSum = 0;
        for (int i=0; i<a.size()-2; i++) {
            int j = i+1, k = a.size()-1;
            while (j < k) {
                int sum = a.get(i) + a.get(j) + a.get(k);
                if (sum == b) return sum;
                int dSum = Math.abs(b-sum);
                if (dSum < absDiffSum) {
                    absDiffSum = dSum;
                    actualSum = sum;
                }
                if (sum < b) j++;
                if (sum > b) k--;
            }
        }
        return actualSum;
    }

    public static void main(String[] args) {
        TargetSum3 t = new TargetSum3();
        System.out.println(t.threeSumClosest(Arrays.asList(-1, 2, 1, -4), 1));
        System.out.println(t.threeSumClosest(Arrays.asList(-10,-10,-10), -5));

    }

}

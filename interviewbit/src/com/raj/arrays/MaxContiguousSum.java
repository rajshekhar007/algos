package com.raj.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class MaxContiguousSum {

    // DO NOT MODFIY THE LIST.
    public static int maxSubArray(final List<Integer> a) {

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

    public static void main(String[] args) {
        System.out.println(maxSubArray(Arrays.asList(-2,1,3)));
    }

}

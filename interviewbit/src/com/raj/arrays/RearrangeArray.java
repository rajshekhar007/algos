package com.raj.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rshekh1 on 6/8/16.
 */
public class RearrangeArray {

    /**
     * 1) Increase every array element arr[i] by (arr[arr[i]] % n)*n.
     2) Divide every element by n.
     Let us understand the above steps by an example array {3, 2, 0, 1} In first step, every value is incremented by
     (arr[arr[i]] % n)*n After first step array becomes {7, 2, 12, 9}. The important thing is, after the increment
     operation of first step, every element holds both old values and new values.

     Old value can be obtained by arr[i]%n
     New value can be obtained by arr[i]/n

     In second step, all elements are updated to new or output
     values  by doing arr[i] = arr[i]/n. After second step, array becomes {1, 0, 3, 2}
     */

    public void arrange(List<Integer> a) {
        int n = a.size();
        int[] arr = new int[n];
        for (int i=0; i<a.size(); i++) {
            arr[i] = a.get(i);
        }

        // First step: arr[i] = arr[i] + (arr[arr[i]] % n) * n which is storing in base n and is a trick to store both old & new values
        for (int i=0; i < arr.length; i++) {
            arr[i] += (arr[arr[i]] % n) * n;
        }

        // Second Step: Divide all values by n
        for (int i=0; i<n; i++) {
            arr[i] /= n;
            a.set(i, arr[i]);
        }
        System.out.println(a);
    }

    public static void main(String[] args) {
        RearrangeArray r = new RearrangeArray();
        r.arrange(Arrays.asList(3,2,0,1));
    }

}

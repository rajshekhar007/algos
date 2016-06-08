package com.raj.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class LargestNumber {

    public String largestNumber(final List<Integer> a) {
        String largestN = "";
        List<Integer> arr = new ArrayList<>(a);
        for (int i=0; i<arr.size(); i++) {
            int j = findMax(arr, i);
            largestN += arr.get(j);
            swap(arr, i, j);
        }
        return largestN;
    }

    public void swap(List<Integer> arr, int i, int j) {
        int t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }

    public int findMax(List<Integer> arr, int j) {
        int maxIdx = j;
        for (int i=j; i<arr.size(); i++) {
            float n = arr.get(i) / 1.0f;
            float max = arr.get(maxIdx) / 1.0f;
            if ((n/10) >= 1) n /= 10;
            if ((max/10) >= 1) max /= 10;
            if (n > max) maxIdx = i;
            if (n == max && arr.get(i) < arr.get(maxIdx)) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public static void main(String[] args) {
        LargestNumber l = new LargestNumber();
        System.out.println(l.largestNumber(Arrays.asList(3, 30, 34, 5, 9)));
    }
}

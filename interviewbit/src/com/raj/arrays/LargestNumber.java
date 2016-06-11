package com.raj.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rshekh1 on 6/7/16.
 */
public class LargestNumber {

    public String largestNumber(final List<Integer> a) {
        if (a == null || a.isEmpty()) return "";
        String largestN = "";
        List<Integer> arr = new ArrayList<>(a);
        for (int i=0; i<arr.size(); i++) {
            int maxIdx = findMax(arr, i);
            largestN += arr.get(maxIdx) == 0 ? "" : arr.get(maxIdx);
            swap(arr, i, maxIdx);
        }
        return largestN.isEmpty() ? "0" : largestN;
    }

    public void swap(List<Integer> arr, int i, int j) {
        int t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }

    public int findMax(List<Integer> arr, int j) {
        int maxIdx = j;
        for (int i=j+1; i<arr.size(); i++) {
            maxIdx = compare(arr, maxIdx, i);
        }
        return maxIdx;
    }

    public int compare(List<Integer> arr, int i, int j) {
        String n1Str = arr.get(i) + "" + arr.get(j) + "";
        String n2Str = arr.get(j) + "" + arr.get(i) + "";
        if (Long.parseLong(n1Str) >= Long.parseLong(n2Str)) {
            return i;
        } else return j;
    }

    public static void main(String[] args) {
        LargestNumber l = new LargestNumber();
        System.out.println(l.largestNumber(Arrays.asList(3,30,34,5,9)));
        System.out.println(l.largestNumber(Arrays.asList(0,0,0,0,0)));
    }

}

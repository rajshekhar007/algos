package com.raj.arrays;

import java.util.ArrayList;

/**
 * Created by rshekh1 on 4/14/16.
 */
public class AddNToArray {

    /**
     * Given a non-negative number represented as an array of digits,

     add 1 to the number ( increment the number represented by the digits ).

     The digits are stored such that the most significant digit is at the head of the list.

     Example:

     If the vector has [1, 2, 3]

     the returned vector should be [1, 2, 4]

     as 123 + 1 = 124.

     Can the output have 0’s before the most significant digit? Or in other words, is 0 1 2 4 a valid output?
     A : For the purpose of this question, NO. Even if the input has zeroes before the most significant digit.
     * @param a
     * @return
     */

    public ArrayList<Integer> plusOne(ArrayList<Integer> a) {

        Integer[] arr = new Integer[a.size()]; a.toArray(arr);

        int c = 1;
        for (int i=arr.length-1; i>=0; i--) {
            int num = arr[i] + c;
            c = 0;
            if (num >= 10) {
                arr[i] = num - 10;
                c = 1;
            } else {
                arr[i] = num;
            }
        }

        boolean msbIsZero = true;
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (c == 1) res.add(c);
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == 0 && msbIsZero && c != 1) continue;  // Zeros can be skipped only if a carry wasn't present
            msbIsZero = false;
            res.add(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        AddNToArray addNToArray = new AddNToArray();
        ArrayList<Integer> a = new ArrayList<>(); a.add(0);a.add(9); a.add(9);
        ArrayList res = addNToArray.plusOne(a);
        System.out.println(res);
    }

}


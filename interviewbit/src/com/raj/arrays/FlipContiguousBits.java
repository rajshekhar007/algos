package com.raj.arrays;

import java.util.ArrayList;

/**
 * Created by rshekh1 on 4/23/16.
 */
public class FlipContiguousBits {

    /**
     * You are given a binary string(i.e. with characters 0 and 1) S consisting of characters S1, S2, …, SN. In a single operation, you can choose two indices L and R such that 1 ≤ L ≤ R ≤ N and flip the characters SL, SL+1, …, SR. By flipping, we mean change character 0 to 1 and vice-versa.

     You aim is to perform ATMOST one operation such that in final string number of 1s is maximised. If you don’t want to perform the operation, return an empty array. Else, return an array consisting of two elements denoting L and R. If there are multiple solutions, return the lexicographically smallest pair of L and R.

     Notes:
     - Pair (a, b) is lexicographically smaller than pair (c, d) if a < c or, if a == b, then b < d.

     For example,

     S = 010

     Pair of [L, R] | Final string
     _______________|_____________
     [1 1]          | 110
     [1 2]          | 100
     [1 3]          | 101
     [2 2]          | 000
     [2 3]          | 001

     We see that two pairs [1, 1] and [1, 3] give same number of 1s in final string. So, we return [1, 1].
     Another example,

     If S = 111

     No operation can give us more than three 1s in final string. So, we return empty array [].
     */


    public ArrayList<Integer> flip(String A) {

        System.out.print(A);
        ArrayList<Integer> res = new ArrayList<>();

        int currentMax = 0, max = 0;
        Integer L = null, Lprev = null, R = null, Rprev = null;

        char[] arr = A.toCharArray();

        for (int i = 0; i < A.length(); i++) {

            // Reset L,R as there is no point in continuing this run if currentMax has bottomed out
            if (arr[i] == '1' && currentMax == 0) {
                if (L != null && R != null) { // if they were set in this run, save them as L,R denote local maxima
                    Lprev = L;
                    Rprev = R;
                }
                L = null;
                R = null;
                continue;
            }

            // Try flipping zeros for local maxima
            if (arr[i] == '0') {
                if (L == null) L = i;
                currentMax ++;
            }

            // Already set 1 will decrement local maxima
            if (arr[i] == '1') {
                currentMax --;
            }

            // Adjust global maxima
            if (currentMax > max) {
                max = currentMax;
                R = i;  // set R bounds now
            }

        }

        if (L != null && R != null) {  // if both were set, this has to be the global maxima
            res.add(L+1); res.add(R+1);
            return res;
        }

        if (Lprev != null && Rprev != null) { // if not L,R, check if an earlier local maxima was found
            res.add(Lprev+1); res.add(Rprev+1);
            return res;
        }

        return res;
    }

    public static void main(String[] args) {
        FlipContiguousBits f = new FlipContiguousBits();
        System.out.println(f.flip("010"));
        System.out.println(f.flip("111"));
        System.out.println(f.flip("100111000011000011"));
        System.out.println(f.flip("10011100011000011"));
        System.out.println(f.flip("1001110011000011"));
        System.out.println(f.flip("100111011000011"));
    }

}

package com.raj.arrays;

import java.util.ArrayList;

/**
 * Created by rshekh1 on 4/26/16.
 */
public class SetZeros {

    /**
     * Given an m x n matrix of 0s and 1s, if an element is 0, set its entire row and column to 0.

     Do it in place.

     Example

     Given array A as

     1 0 1
     1 1 1
     1 1 1
     On returning, the array A should be :

     0 0 0
     1 0 1
     1 0 1
     Note that this will be evaluated on the extra memory used. Try to minimize the space and time complexity.

     * @param a
     */
    public void setZeroes(ArrayList<ArrayList<Integer>> a) {

        if (a == null || a.isEmpty()) return;

        int m = a.size();
        int n = a.get(0).size();
        int firstRowFlag = 1, firstColFlag = 1;

        for (int i=0; i<m; i++) {
            if (a.get(i).get(0) == 0) firstColFlag = 0;
        }

        for (int i=0; i<n; i++) {
            if (a.get(0).get(i) == 0) firstRowFlag = 0;
        }

        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {

                if (a.get(i).get(j) == 0) {
                    a.get(0).set(j, 0);
                    a.get(i).set(0, 0);
                }
            }
        }

        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {

                if (a.get(0).get(j) == 0 || a.get(i).get(0) == 0) {
                    a.get(i).set(j, 0);
                }
            }
        }

        if (firstRowFlag == 0) {
            for (int i=0; i<n; i++) {
                a.get(0).set(i, 0);
            }
        }

        if (firstColFlag == 0) {
            for (int i=0; i<m; i++) {
                a.get(i).set(0, 0);
            }
        }

    }

    public void setZeroes1(ArrayList<ArrayList<Integer>> a) {
        if (a == null || a.isEmpty()) return;

        int m = a.size();
        int n = a.get(0).size();

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {

                if (a.get(i).get(j) == 0) {
                    setZero(a, i, j, m, n);
                }
            }
        }

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {

                if (a.get(i).get(j) == 2) {
                    a.get(i).set(j, 0);
                }
            }
        }

    }

    public void setZero(ArrayList<ArrayList<Integer>> a, int i, int j, int m, int n) {
        for (int k=0; k<m; k++) if(a.get(k).get(j) != 0) a.get(k).set(j, 2);
        for (int l=0; l<n; l++) if(a.get(i).get(l) != 0) a.get(i).set(l, 2);
    }

    public static void main(String[] args) {
        SetZeros setZeros = new SetZeros();
        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> i = new ArrayList<>(); i.add(0); i.add(0);
        ArrayList<Integer> j = new ArrayList<>(); j.add(1); j.add(1);
        a.add(i); a.add(j);
        setZeros.setZeroes(a);
        System.out.println(a);
    }
}

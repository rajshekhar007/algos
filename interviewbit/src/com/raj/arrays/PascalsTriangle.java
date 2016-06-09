package com.raj.arrays;

import java.util.ArrayList;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class PascalsTriangle {

    public ArrayList<Integer> getRow(int a) {
        ArrayList<Integer> res = new ArrayList<>();
        int[] A = new int[a+1];
        int[] B = new int[a+1];
        A[0] = 1;
        boolean useAForB = true;
        boolean useBForA = false;
        for (int i = 1; i <= a; i++) {
            for (int j = 0; j <= i; j++) {
                if (useAForB) {
                    int p = (j - 1) < 0 ? 0 : A[j - 1];
                    B[j] = A[j] + p;
                }
                if (useBForA) {
                    int p = (j - 1) < 0 ? 0 : B[j - 1];
                    A[j] = B[j] + p;
                }
            }
            useAForB = !useAForB;
            useBForA = !useBForA;
        }
        for (int i = 0; i <= a; i ++) {
            if (useAForB) res.add(A[i]);
            if (useBForA) res.add(B[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        PascalsTriangle p = new PascalsTriangle();
        System.out.println(p.getRow(0));
    }
}

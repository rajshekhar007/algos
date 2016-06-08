package com.raj.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Print an array of size m x n in a spiral order.
 * 1  2  3  4  5
 * 6  7  8  9  10
 * 11 12 13 14 15
 * 16 17 18 19 20
 *
 * prints:
 * 1,2,3,4,5,10,15,20,19,18,17,16,11,6,7,8,9,14,13,12
 */
class SpiralArray {

    public static int[] printSpiralArray(int[][] A, int m, int n) {

        int [] spiralArr = new int[m*n];

        char counter = 'C';  // c or r denoting which counter is incrementing or decrementing
        char colMode = '+', rowMode = '+'; // both start in + mode initially

        int i=0, R=0, C=0;
        boolean isVisited[][] = new boolean[m][n];

        while (i < (m*n)) {

            // Check Boundary conditions : R or C or alreadyVisited? Update counter, mode & R,C, if met
            if (R<0 || R>m-1 || C<0 || C>n-1 || isVisited[R][C]) {
                if (counter == 'R') {
                    counter = 'C';
                    R = (rowMode=='+') ? --R : ++R;
                    rowMode = flipMode(rowMode);
                } else {
                    counter = 'R';
                    C = (colMode=='+') ? --C : ++C;
                    colMode = flipMode(colMode);
                }
            }

            // Print
            if (R>=0 && C>=0 && R<m && C<n && !isVisited[R][C]) {
                spiralArr[i++] = A[R][C];
                isVisited[R][C] = true;
            }

            // Update R or C
            if (counter == 'R') {
                if (rowMode == '+') {
                    R++;
                } else {
                    R--;
                }
            }
            if (counter == 'C') {
                if (colMode == '+') {
                    C++;
                } else {
                    C--;
                }
            }

        }
        return spiralArr;
    }

    private static char flipMode(char mode) {
        return mode == '+' ? '-' : '+';
    }

    public static void main(String[] args) {
        int[][] A = {{1}}; //{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
        int[] spiralArr = printSpiralArray(A, A.length, A[0].length);
        for (int i = 0; i < spiralArr.length; i++) {
            System.out.println(spiralArr[i]);
        }

        List<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> b = new ArrayList<Integer>();
        b.add(1);
        a.add(b);
        Solution s = new Solution();
        System.out.println(s.spiralOrder(a));
    }

     static class Solution {
        // DO NOT MODIFY THE LIST
        public ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> a) {

            ArrayList<Integer> result = new ArrayList<Integer>();

            // Populate result;

            char counter = 'C';  // c or r denoting which counter is incrementing or decrementing
            char colMode = '+', rowMode = '+'; // both start in + mode initially

            int i = 0, R = 0, C = 0;
            int m = a.size(); int n = a.get(0).size();
            boolean isVisited[][] = new boolean[m][n];

            while (i < (m*n)) {

                // Check Boundary conditions : R or C or alreadyVisited? Update counter, mode & R,C, if met
                if (R<0 || R>m-1 || C<0 || C>n-1 || isVisited[R][C]) {
                    if (counter == 'R') {
                        counter = 'C';
                        R = (rowMode=='+') ? --R : ++R;
                        rowMode = flipMode(rowMode);
                    } else {
                        counter = 'R';
                        C = (colMode=='+') ? --C : ++C;
                        colMode = flipMode(colMode);
                    }
                }

                // Print
                if (R>=0 && C>=0 && R<m && C<n && !isVisited[R][C]) {
                    result.add(a.get(R).get(C));
                    isVisited[R][C] = true;
                    i++;
                }

                // Update R or C
                if (counter == 'R') {
                    if (rowMode == '+') {
                        R++;
                    } else {
                        R--;
                    }
                }
                if (counter == 'C') {
                    if (colMode == '+') {
                        C++;
                    } else {
                        C--;
                    }
                }

            }

            return result;
        }

        private static char flipMode(char mode) {
            return mode == '+' ? '-' : '+';
        }
    }


}
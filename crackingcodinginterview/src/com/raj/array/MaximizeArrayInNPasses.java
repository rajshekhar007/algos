package com.raj.array;

/**
 * @author: sraj1
 * Date:    10/21/12
 */

/**
 * Given Array with numbers, maximize it's value from left to right in N passes, where 1 pass is 1 swap operation of adjacent integers
 * <p/>
 * Algo :
 * - Loop till N > 0
 * - Find the max integer from i till i+N
 * - Exchange numbers, if max!=i
 * - Decrement N by swaps used, increment i
 * - Loop end
 * <p/>
 * e.g.
 * [2]5187 (N=5)
 * 8[2]517 (N=2)
 * 85[2]17 (N=1)
 * 852[1]7 (N=1)
 * 8527[1] (N=0)
 */
public class MaximizeArrayInNPasses {

    public static int[] maximizeArr(int[] arr, int N) {
        for (int i = 0; i < arr.length && N > 0; i++) {
            int maxPos = findMaxFromHere(arr, i, N);
            if (i != maxPos)
                N -= swap(arr, i, maxPos);
        }
        return arr;
    }

    private static int findMaxFromHere(int[] arr, int i, int N) {
        int maxPos = i;
        for (int j = i; j <= i + N && j < arr.length; j++) {
            if (arr[j] > arr[maxPos])
                maxPos = j;
        }
        return maxPos;
    }

    private static int swap(int[] arr, int i, int maxPos) {
        // Change change! It should swap with adjacent in 1 pass. You can't directly swap.
        for (int j = maxPos; j > i; j--) {
            int tmp = arr[j - 1];
            arr[j - 1] = arr[j];
            arr[j] = tmp;
        }

        return maxPos - i;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 8, 7};//{2,7,5,1,8};
        arr = maximizeArr(arr, 5);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


}

package com.raj.sort;

/**
 * @author: sraj1
 * Date:    10/21/12
 */
public class Sort {

    /**
     * Algo:
     * - Save ith element
     * - for each iteration of inner loop,
     * - keep shifting integers towards right if jth element is lesser. Hence the loop will stop if jth is greater than kth
     * - Insert ith at j, which is the correct place for this element is the sub array scanned
     */
    public static void insertionSort(int[] A) {

        for (int i = 1; i < A.length; i++) {

            int j = i;
            int save = A[j];
            for (int k = j - 1; k >= 0 && A[k] > save; k--) {
                A[j] = A[k];
                j--;
            }

            A[j] = save;

        }

    }

    /**
     * Merge Sort using extra space
     */
    public static void MSort(int[] A, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        MSort(A, start, mid);
        MSort(A, mid + 1, end);
        Merge(A, start, mid, end);
    }

    private static void Merge(int[] A, int start, int mid, int end) {
        int[] tmp = new int[end - start];
        int iStart = start, iEnd = mid;
        int jStart = mid + 1, jEnd = end;
        int i = 0;

        while (iStart < iEnd && jStart < jEnd) {
            if (A[iStart] < A[jStart]) {
                tmp[i++] = A[iStart++];
            } else {
                tmp[i++] = A[jStart++];
            }
        }

        while (iStart < iEnd) {
            tmp[i++] = A[iStart++];
        }

        while (jStart < jEnd) {
            tmp[i++] = A[jStart++];
        }

        for (int k = 0; k < tmp.length; k++) {
            A[start + k] = tmp[k];
        }
    }

    /**
     * Quick Sort. See CLR book for explanation with diagram
     */
    public static void QSort(int[] A, int start, int end) {
        if (A == null || start >= end) return;
        int borderIdx = partition(A, start, end);
        // borderIdx returned will be at it's right position...hence sort either side of it
        QSort(A, start, borderIdx - 1);
        QSort(A, borderIdx + 1, end);
    }

    private static int partition(int[] A, int start, int end) {
        int borderIdx = start - 1;
        int pivot = A[end];
        for (int i = start; i <= end - 1; i++) {
            // increment borderIdx and bring the lesser integer to left of border
            if (A[i] < pivot) swap(A, i, ++borderIdx);
            // the greater integers stays towards the right of border
        }
        // finally put pivot back to it's place
        swap(A, ++borderIdx, end);
        return borderIdx;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        int[] A = {8, 3, 4, 9, 7, 5, 6, 2};
        //insertionSort(A);
        //MSort(A, 0, A.length-1);
        QSort(A, 0, A.length - 1);
        for (int i : A)
            System.out.print(i + " ");
    }

}

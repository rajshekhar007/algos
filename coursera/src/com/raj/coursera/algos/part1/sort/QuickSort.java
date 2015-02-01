package com.raj.coursera.algos.part1.sort;

import com.raj.helper.FileReaderHelper;
import com.raj.helper.Util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sweta on 1/31/2015.
 */
public class QuickSort {


    public static void QSort(Integer[] A, int startIdx, int endIdx) {

        if (A == null || A.length == 0 || A.length == 1) {
            return;
        }

        if (startIdx >= endIdx) {
            return;
        }

        //int pivotIdx = choosePivotAsFirstElement(A, startIdx, endIdx);
        //int pivotIdx = choosePivotAsLastElement(A, startIdx, endIdx);
        int pivotIdx = choosePivotAsMedianElement(A, startIdx, endIdx);

        int partitionIdx = partitionAroundPivot(A, startIdx, endIdx, pivotIdx); // pivot moves to it's final sorted place

        QSort(A, startIdx, partitionIdx - 1); // hence new partitions don't include the pivot

        QSort(A, partitionIdx + 1, endIdx);

    }

    private static int partitionAroundPivot(Integer[] A, int startIdx, int endIdx, int pivotIdx) {

        if (startIdx > endIdx) return startIdx;

        Util.runtime += (endIdx - startIdx); // number of comparisons - the pivot element is compared to each of the other m−1 elements in the subarray in this recursive call

        int iPartitionIdx = startIdx;

        for (int j = startIdx + 1; j <= endIdx; j++) {
            // If this element is less than pivot, swap
            if (A[j] < A[pivotIdx]) {
                iPartitionIdx++;
                swap(A, iPartitionIdx, j);
            }
        }

        // Move the pivot to it's correct final sorted position, which is at the partition boundary
        swap(A, iPartitionIdx, pivotIdx);
        return iPartitionIdx;
    }

    public static void swap(Integer[] A, int i, int j) {
        if (i != j) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }

    private static int choosePivotAsFirstElement(Integer[] A, int startIdx, int endIdx) {
        return startIdx;
    }

    /**
     * Swap first & last index
     *
     * @param A
     * @param startIdx
     * @param endIdx
     * @return
     */
    private static int choosePivotAsLastElement(Integer[] A, int startIdx, int endIdx) {
        swap(A, startIdx, endIdx);
        return startIdx;
    }

    /**
     * Swap first and the median element
     *
     * @param A
     * @param startIdx
     * @param endIdx
     * @return
     */
    private static int choosePivotAsMedianElement(Integer[] A, int startIdx, int endIdx) {

        // get median of first, middle & last elements
        int mid = (endIdx - startIdx) / 2 + startIdx;

        Integer[] medianArr = new Integer[3];
        medianArr[0] = A[startIdx];
        medianArr[1] = A[mid];
        medianArr[2] = A[endIdx];

        Arrays.sort(medianArr);

        int pivotIdx = 0;
        if (medianArr[1] == A[startIdx]) pivotIdx = startIdx;
        else if (medianArr[1] == A[mid]) pivotIdx = mid;
        else pivotIdx = endIdx;

        swap(A, startIdx, pivotIdx);
        return startIdx;

    }

    public static void runProgram(Integer[] A) {
        Util.startProfiling(A);
        QSort(A, 0, A.length - 1);
        Util.endProfiling(A);
    }

    public static void main(String[] args) {
        Integer[] inputArray = {1, 3, 5, 2, 4, 6}; //{5, 1, 2, 6, 4, 8, 7, 9}; //{1,3,5,2,4,6}; //{4,94,87,24,44,30,37,97,47,93}

        Integer[] randomArray = Util.generateRandomArray(29, 0, 100);
        //runProgram(randomArray);

        List<Integer> integerList = FileReaderHelper.read("E:\\workspace-intellij\\algos\\coursera\\resource\\QuickSort.txt", true);
        Integer[] inputArrayFile = new Integer[integerList.size()];
        System.out.println("File integer count = " + integerList.size() + ", Integer array size = " + inputArrayFile.length);

        runProgram(integerList.toArray(inputArrayFile));

    }


}

package com.raj.coursera.algos.part1.sort;

/**
 * Created by Sweta on 1/24/2015.
 */
public class MergeSort {

    /**
     * Merge Sort routine
     *
     * @param array - passed array to sort
     * @param start - denotes the start index of the array / sub-array
     * @param end   - denotes the end index of the array / sub-array
     */
    public static void mergeSort(int[] array, int start, int end) {

        if (array == null || array.length == 0 || start >= end) {
            return;
        }

        // Divide array
        int mid = (start + end) / 2;

        // Create sub-problems & recurse to solve
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);

        // Conquer by merging sub-solutions
        merge(array, start, mid, end);

    }

    /**
     * Merges left & right partition marked by indices in linear time using extra space O(n)
     *
     * @param array
     * @param start
     * @param mid
     * @param end
     */
    public static void merge(int[] array, int start, int mid, int end) {

        int iStart = start, iEnd = mid;
        int jStart = mid + 1, jEnd = end;

        int k = 0;       // start of merged array
        int[] mergedAndSortedArray = new int[end - start + 1];

        // Copy smallest from either partition
        while (iStart <= iEnd && jStart <= jEnd) {
            if (array[iStart] < array[jStart]) {
                mergedAndSortedArray[k++] = array[iStart++];
            } else {
                mergedAndSortedArray[k++] = array[jStart++];
            }
        }

        // Copy over leftovers from either partitions
        while (iStart <= iEnd) {
            mergedAndSortedArray[k++] = array[iStart++];
        }

        while (jStart <= jEnd) {
            mergedAndSortedArray[k++] = array[jStart++];
        }

        // Now update the original array with merged & sorted array
        k = 0;
        while (start <= end) {
            array[start++] = mergedAndSortedArray[k++];
        }

    }

    public static void main(String[] args) {
        int[] inputArray = {1, 3, 5, 2, 4, 6};
        System.out.println("Input Array: ");
        for (int i = 0; i < inputArray.length; i++) {
            System.out.print(" " + inputArray[i]);
        }
        mergeSort(inputArray, 0, inputArray.length - 1);
        System.out.println("\nSorted Array: ");
        for (int i = 0; i < inputArray.length; i++) {
            System.out.print(" " + inputArray[i]);
        }
    }

}

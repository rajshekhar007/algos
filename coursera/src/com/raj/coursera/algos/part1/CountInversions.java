package com.raj.coursera.algos.part1;

import com.raj.helper.FileReaderHelper;

import java.util.List;

/**
 * Count Inversion - uses enhanced MergeSort routine
 *
 * Created by Sweta on 1/24/2015.
 */
public class CountInversions {

    static int runtime = 0, space = 0;
    static boolean isDebugEnabled = true;

    /**
     * @param array - passed array to sort
     * @param start - denotes the start index of the array / sub-array
     * @param end   - denotes the end index of the array / sub-array
     */
    public static long sortAndCountInversions(Integer[] array, int start, int end) {

        runtime++;
        if (array == null || array.length == 0 || start >= end) {
            return 0;
        }

        // Divide array
        int mid = (start + end) / 2;

        // Create sub-problems & recurse to solve
        long inversionCountA = sortAndCountInversions(array, start, mid);
        long inversionCountB = sortAndCountInversions(array, mid + 1, end);

        // Conquer by solving & merging sub-problems
        long inversionCountC = mergeAndCountSplitInversions(array, start, mid, end);

        return inversionCountA + inversionCountB + inversionCountC;

    }

    /**
     * Algo:
     * If an inversion doesn't exist, all left elements will be smaller than the any(first) right element.
     * If an inversion exists, right partition will have a smaller element than left. At that point all elements
     * remaining in left are inverted.
     *
     * @param array
     * @param start
     * @param mid
     * @param end
     */
    public static long mergeAndCountSplitInversions(Integer[] array, int start, int mid, int end) {

        runtime++;
        int iStart = start, iEnd = mid;
        int jStart = mid + 1, jEnd = end;

        int k = 0;       // start of merged array
        int numElements = end - start + 1;
        int[] mergedAndSortedArray = new int[end - start + 1];
        space += end - start + 1;
        System.out.println("Space: " + space);
        long inversionCount = 0;

        // Copy smallest from either partition
        while (iStart <= iEnd && jStart <= jEnd) {
            if (array[iStart] < array[jStart]) {
                mergedAndSortedArray[k++] = array[iStart++];
            } else {
                // If left element is greater than right, it means we have inversions (see algo above)
                inversionCount += iEnd - iStart + 1;
                // Debug print
                if (isDebugEnabled) {
                    for (int i = iStart; i <= iEnd; i++) {
                        System.out.printf("(%s,%s), ", array[i], array[jStart]);
                    }
                }

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

        space -= numElements;
        return inversionCount;
    }

    public static long countInversionsBruteForce(Integer[] array) {
        long count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    count++;
                    if (isDebugEnabled) {
                        System.out.printf("(%s,%s), ", array[i], array[j]);
                    }
                }
            }
        }
        return count;
    }

    public static void runProgram(Integer[] inputArray) {
        System.out.println("Input Array: ");
        for (int i = 0; i < inputArray.length; i++) {
            System.out.print(" " + inputArray[i]);
        }
        System.out.println("\n\nInversions -----> ");

        long start_time = System.currentTimeMillis();

        System.out.println("\n--------- BRUTE FORCE ----------");
        //System.out.println("Count = " + countInversionsBruteForce(inputArray) + "\n");

        long end_time = System.currentTimeMillis();
        long difference = end_time - start_time;
        System.out.println("\nRuntime complexity = " + runtime + ", Space complexity = " + space + ", Total time in msecs = " + difference);

        runtime = 0;
        space = 0;
        start_time = System.currentTimeMillis();

        System.out.println("\n--------- DIVE & CONQUER ----------");
        long totalInversionCount = sortAndCountInversions(inputArray, 0, inputArray.length - 1);
        System.out.println("Inversion Count: " + totalInversionCount);
        for (int i = 0; i < inputArray.length; i++) {
            System.out.print(" " + inputArray[i]);
        }

        end_time = System.currentTimeMillis();
        difference = end_time - start_time;
        System.out.println("\nRuntime complexity = " + runtime + ", Space complexity = " + space + ", Total time in msecs = " + difference);
    }

    public static void main(String[] args) {
        Integer[] inputArray = {5, 1, 2, 6, 4, 8, 7, 9};//{1,3,5,2,4,6};
        //runProgram(inputArray);

        isDebugEnabled = false;
        List<Integer> integerList = FileReaderHelper.read("E:\\workspace-intellij\\algos\\coursera\\resource\\IntegerArray.txt", true);
        System.out.println("File integer count = " + integerList.size());
        Integer[] inputArrayFile = new Integer[integerList.size()];
        System.out.println("Integer array size = " + inputArrayFile.length);
        runProgram(integerList.toArray(inputArrayFile));

    }

}

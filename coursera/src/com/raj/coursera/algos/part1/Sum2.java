/*
 * Copyright (c) Shekhar Raj
 */

package com.raj.coursera.algos.part1;

import com.google.common.collect.Sets;
import com.raj.helper.FileReaderHelper;

import java.util.List;
import java.util.Set;

/**
 * The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in the Week 6 lecture on hash table applications).
 * The file contains 1 million integers, both positive and negative (there might be some repetitions!).This is your array of integers, with the ith row of the file specifying the ith entry of the array.
 * <p>
 * Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that there are distinct numbers x,y in the input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)
 * <p>
 * Write your numeric answer (an integer between 0 and 20001) in the space provided.
 * <p>
 * OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For example, you could compare performance under the chaining and open addressing approaches to resolving collisions.
 * <p>
 * Count : 20
 * File count = 1000000
 * Long array size = 1000000
 * Count : 100424
 * <p>
 * <p>
 * Created by Sweta on 3/2/2015.
 */
public class Sum2 {

    private Long[] A;
    private Set<Long> lookupTable = Sets.newHashSet();
    private long minTargetRange, maxTargetRange;

    public Sum2(Long[] a, long minTargetRange, long maxTargetRange) {
        A = a;
        this.minTargetRange = minTargetRange;
        this.maxTargetRange = maxTargetRange;
    }

    private static void runProgram(Long[] inputArray, long minTargetRange, long maxTargetRange) {
        Sum2 sum2 = new Sum2(inputArray, minTargetRange, maxTargetRange);
        long count = sum2.sum2Count();
        System.out.println("Count : " + count);
    }

    public static void main(String[] args) {
        Long[] inputArray = {4L, 2L, 1L, 3L, 5L};
        runProgram(inputArray, 3, 9);

        List<Long> longList = FileReaderHelper.readAsLong("E:\\workspace-intellij\\algos\\coursera\\resource\\algo1-programming_prob-2sum.txt");
        System.out.println("File count = " + longList.size());
        Long[] inputArrayFile = new Long[longList.size()];
        System.out.println("Long array size = " + inputArrayFile.length);
        runProgram(longList.toArray(inputArrayFile), -10000, 10000);
    }

    /**
     * Finds counts of :
     * x + y = targetSum, where targetSum is a range
     *
     * @return
     */
    public long sum2Count() {

        populateLookupTable();

        long count = 0;

        for (long x : A) {

            for (long targetSum = minTargetRange; targetSum <= maxTargetRange; targetSum++) {
                long y = targetSum - x;
                if (x != y && lookupTable.contains(y)) {
                    //System.out.println(x + "," + y);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Used for looking up an integer in O(1) time
     */
    private void populateLookupTable() {
        for (long i : A) {
            lookupTable.add(i);
        }
    }

}

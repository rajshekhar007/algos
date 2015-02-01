package com.raj.helper;

import java.util.Random;

/**
 * Created by Sweta on 1/31/2015.
 */
public class Util {

    public static long runtime = 0, space = 0, start_time, end_time, difference;
    public static boolean isDebugEnabled = true;

    public static Integer[] generateRandomArray(int length, int min, int max) {
        Integer[] arr = new Integer[length];
        Random random = new Random(min);
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(max);
        }
        return arr;
    }

    public static void startProfiling(Integer[] A) {
        System.out.println("########################## START PROGRAM EXECUTION #############################################");
        runtime = 0;
        space = 0;
        start_time = System.currentTimeMillis();

        System.out.println("\n--------- Input Array ----------");

        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }

    }

    public static void endProfiling(Integer[] A) {

        System.out.println("\n--------- Output Array ----------");

        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }
        System.out.println("\nCount = " + runtime);

        end_time = System.currentTimeMillis();
        difference = end_time - start_time;
        System.out.println("\nRuntime complexity = " + runtime + ", Space complexity = " + space + ", Total time in msecs = " + difference);

        System.out.println("################################ END PROGRAM EXECUTION #####################################\n");

    }

}

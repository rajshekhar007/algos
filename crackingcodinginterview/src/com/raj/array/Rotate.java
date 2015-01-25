package com.raj.array;

/**
 * @author: sraj1
 * Date:    10/8/12
 */
public class Rotate {

    private static void rotate(char[] arr, int d) {

        int srcIdx = 0, destIdx = -1;
        int pivotIdx = srcIdx;  // index from where we start swapping values
        char save, tmp = arr[0];
        int len = arr.length;
        int cnt = 0;  // loop has to run for all numbers

        while (cnt < len) {
            cnt++;
            destIdx = srcIdx + d;
            if (destIdx >= len)
                destIdx -= len;

            save = arr[destIdx];
            arr[destIdx] = tmp;
            tmp = save;
            srcIdx = destIdx;

            if (srcIdx == pivotIdx) {   // means we are again swapping pivot, circle detected!
                srcIdx = srcIdx + 1;
                pivotIdx = srcIdx;  // new pivot index
                tmp = arr[srcIdx];  // update value to write
            }
        }
        //arr[destIdx] = tmp;
        System.out.println(arr);
    }

    public static void main(String[] args) {
        String str = "abcdef";
        char[] arr = str.toCharArray();
        rotate(arr, 3);
        System.out.println(arr);
        int[] oldArray = {3, 5, 7, 9};
        int[] newArray = {2, 4, 6, 8, 9, 7, 5, 3};
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        for (int i = 0; i < newArray.length; i++)
            System.out.println(newArray[i]);
        System.out.println(Math.ceil(-82.4));
    }

}

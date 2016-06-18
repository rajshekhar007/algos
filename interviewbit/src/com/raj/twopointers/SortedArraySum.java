package com.raj.twopointers;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class SortedArraySum {

    /**
     * Find if A[i] + A[j] == 0
     * Runs in O(n) time complexity as i is decrementing until j and j is incrementing until i. They converge.
     Logic:
     If A[i] + A[J] > 0,
     Then A[i + 1] + A[J] is also greater than 0, as A[i + 1] > A[i].
     This means if we have tried J for ‘i’, then when moving to i + 1, we should only try values of j < J.
     This concludes that our ‘j’ should be monotonically decreasing
     * @param arr
     * @return
     */
    public static boolean findSum(int[] arr) {
        int i=0, j=arr.length-1;
        while (i<j && i!=j) {
            int sum = arr[i] + arr[j];
            if (sum == 0) {
                System.out.println(arr[i]+","+arr[j]);
                return true;
            }
            if (sum < 0) i++;
            if (sum > 0) j--;
        }
        return false;
    }

    public static void main(String[] args) {
        findSum(new int[] {-4,-2,-1,0,2,3});
    }
}

package com.raj.leetcode.dp;

/**
 * 0-1 Knapsack problem : Maximize value of items added to a Knapsack that can only carry upto a maximum weight
 * https://www.geeksforgeeks.org/knapsack-problem/
 *
 * W [10, 20, 30]       // weight
 * V [60, 100, 120]     // value
 *
 */
public class Knapsack {

    static int[] W = {10,20,30,40,50};
    static int[] V = {60,100,120,150,180};
    static int W_total = 50;
    int N = W.length;
    static int comparisons = 0;

    /**
     * O(2^n) exponential complexity as each option has 2 paths
     * => each level adds twice the number of steps => 2 + 4 + 8 + 16 ... => 2^n
     *
     * Constant space complexity
     *
     *              f(i, w)
     *              /      \
     *     f(i+1,w+W[i])   f(i+1,w)    ----> each level 2 child for each option
     *        /     \           /   \
     *f(i+2,w'+W[i]) f(i+2,w')  ..  ....
     *
     * @param i
     * @param weightSoFar
     * @return
     */
    public int recursive_2_power_n(int i, int weightSoFar) {
        comparisons ++;
        // Base case
        if (i == N || weightSoFar == W_total) return 0; // exhausted all options or no more weight left

        // try ith option, if the weight is within limits or don't try it - evaluate their values & find max
        if (weightSoFar + W[i] <= W_total) {
            return Math.max(V[i] + recursive_2_power_n(i+1, weightSoFar + W[i]),  // try i
                    recursive_2_power_n(i+1, weightSoFar));  // don't try i
        } else return recursive_2_power_n(i+1, weightSoFar);
    }


    private int[][] dpTable = new int[W.length][W_total+1];

    /**
     * Fill DP table right to left for overlapping sub problem results
     * @param n
     * @param weightLeft
     * @return
     */
    public int dp_W_n(int n, int weightLeft) {
        comparisons ++;
        // Base case
        if (n <= 0 || weightLeft <= 0) return 0; // exhausted all options or no more weight left

        if (dpTable[n][weightLeft] > 0) return dpTable [n][weightLeft];  // already pre-computed

        // try ith option, if the weight is within limits or don't try it - evaluate their values & find max
        if (W[n] <= W_total) {
            dpTable[n][weightLeft] = Math.max(V[n] + dp_W_n(n-1, weightLeft - W[n]),  // try i
                    dp_W_n(n-1, weightLeft));  // don't try i
        } else dpTable[n][weightLeft] = dp_W_n(n-1, weightLeft);

        return dpTable[n][weightLeft];
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        comparisons = 0;
        System.out.println(ks.recursive_2_power_n(0, 0) + " , Comparisons = " + comparisons);

        comparisons = 0;
        System.out.println(ks.dp_W_n(W.length-1, W_total) + " , Comparisons = " + comparisons);
    }

}

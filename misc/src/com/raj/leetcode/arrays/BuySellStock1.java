package com.raj.leetcode.arrays;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by rshekh1 on 9/2/17.
 */
public class BuySellStock1 {
    /*
    Say you have an array for which the ith element is the price of a given stock on day i.
    If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

    Example 1:
    Input: [7, 1, 5, 3, 6, 4]
    Output: 5
    max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

    Example 2:
    Input: [7, 6, 4, 3, 1]
    Output: 0

    In this case, no transaction is done, i.e. max profit = 0.
    */

    // O(n2) solve
    public int bruteForce(int[] prices) {
        // Maximize profit
        int maxProfit = 0;
        int buy = Integer.MAX_VALUE;
        int sell = Integer.MIN_VALUE;

        for (int i=0; i<prices.length; i++)
            for (int j=i+1; j<prices.length; j++) {
                if (buy > 0 && (prices[j] - prices[i]) > maxProfit) {
                    buy = prices[i];
                    sell = prices[j];
                    maxProfit = sell - buy;
                }
        }
        System.out.println("BUY=" + buy + " ,SELL=" + sell + ", Profit=" + maxProfit);
        return maxProfit;
    }

    public int On(int[] prices) {
        // Maximize profit
        int maxProfit = 0;
        int buy = Integer.MAX_VALUE;
        int sell = Integer.MIN_VALUE;

        for (int price : prices) {
            // Buy has to happen first, then sell
            if (price < buy) buy = price;
            else if ((price - buy) > maxProfit) {
                maxProfit = price - buy;
                sell = price;
            }
        }
        System.out.println("BUY=" + buy + " ,SELL=" + sell + ", Profit=" + maxProfit);
        return maxProfit;
    }


    public static void main(String[] args) {
        BuySellStock1 buySellStock1 = new BuySellStock1();
        getInput().forEach(input -> buySellStock1.bruteForce(input));
        getInput().forEach(input -> buySellStock1.On(input));
    }

    public static List<int[]> getInput() {
        return Lists.newArrayList(new int[]{7, 1, 5, 3, 6, 4}, new int[]{7, 6, 4, 3, 1});
    }

}

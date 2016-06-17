package com.raj.dynamicprog;

/**
 * Created by rshekh1 on 6/16/16.
 */
public class LongestPalindromeSubsequence {

    /**
     * Dynamic programming solve for Longest Palindrome Subsequence
     * http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
     *
     * @param s
     * @return
     */
    public String LPS(String s) {

        if (s == null || s.isEmpty()) return "";

        boolean[][] table = new boolean[s.length()][s.length()];

        int PStart = 0, PEnd = 0;  // P's start & end index

        // 1 char substring are P
        for (int i = 0; i < s.length(); i++) {
            table[i][i] = true;
            PStart = i; PEnd = i;
        }

        // check for 2 char P
        for (int i = 0; i < s.length(); i++) {
            if (i+1 < s.length() && s.charAt(i)==s.charAt(i+1)) {
                table[i][i + 1] = true;
                PStart = i;
                PEnd = i+1;
            }
        }

        // check for 2+ char P
        for (int length = 2; length < s.length(); length++) {

            for (int startIdx = 0; startIdx < s.length() - length; startIdx++) {
                //0 1 2 = 3 length, hence length starts from 2 in outer loop
                //a b c d e f g
                int endIdx = startIdx + length;  // substring's endIdx

                // it's a P if startIdx==endIdx and table[startIdx+1][endIdx-1] is true ==> cached DP sub-problem solution
                if (table[startIdx + 1][endIdx - 1] && s.charAt(startIdx) == s.charAt(endIdx)) {

                    table[startIdx][endIdx] = true;

                    if (length > PEnd-PStart) {  // update maxP's indexes
                        PStart = startIdx;
                        PEnd = endIdx;
                    }
                }
            }
        }
        return s.substring(PStart, PEnd+1);
    }

    public static void main(String[] args) {
        LongestPalindromeSubsequence l = new LongestPalindromeSubsequence();
        System.out.println(l.LPS("abbccbb"));
        System.out.println(l.LPS("abb"));
        System.out.println(l.LPS("cababa"));
        System.out.println(l.LPS("abacdfgdcaba"));
    }

}

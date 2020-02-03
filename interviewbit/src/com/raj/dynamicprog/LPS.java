package com.raj.dynamicprog;

/**
 * https://www.geeksforgeeks.org/?p=19155
 */
public class LPS {

    // A utility function to get max of two integers
    static int max (int x, int y) { return (x > y)? x : y; }

    // Recursive : Returns the length of the longest palindromic subsequence in seq
    static int lps(char[] s, int i, int j)
    {
        // Base Case 1: If there is only 1 character
        if (i == j)
            return 1;

        // Base Case 2: If there are only 2 characters and both are same
        if (s[i] == s[j] && (j - i) == 1)
            return 2;

        // If the first and last characters match
        if (s[i] == s[j])
            return lps (s, i+1, j-1) + 2;

        // If the first and last characters do not match
        return max( lps(s, i, j-1), lps(s, i+1, j) );
    }

    /**
     *   L(0, 5)
        /        \
     /          \
     L(1,5)          L(0,4)
     /    \            /    \
     /      \          /      \
     L(2,5)    L(1,4)  L(1,4)  L(0,3)
     In the above partial recursion tree, L(1, 4) is being solved twice. If we draw the complete recursion tree, then we can see that there are many subproblems which are solved again and again. Since same suproblems are called again, this problem has Overlapping Subprolems property.
     *
     *
     *
     * @param args
     */


    /* Driver program to test above functions */
    public static void main(String[] args) {
        System.out.println("The length of the LPS is = " + lps("GEEKSFORGEEKS".toCharArray(), 0, "GEEKSFORGEEKS".length() - 1));
    }
}

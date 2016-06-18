package com.raj.strings;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class LongestPalindromeSubstring {

    public String longestPalindrome1(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) return s;
        String longestP = "";
        for (int i=0; i<s.length(); i++) {
            for (int j = s.length() - 1; j > i; j--) {
                int k = 0;
                String P = "", PS = "", PE = "";
                while (i + k < s.length() && j - k >= i + k && s.charAt(i + k) == s.charAt(j - k)) {
                    PS += s.charAt(i + k);
                    if (i+k != j-k) PE = s.charAt(j - k)+PE;
                    k++;
                }
                if (k > 0 && i+k == j-i) P = PS + PE;
                if (P.length() > longestP.length()) longestP = P;
                }
            }
        return longestP;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        return longestPalindromeSubsequence(s, 0, s.length() - 1);
    }

    public String longestPalindromeSubsequence(String s, int i, int j) {
        if (i == j) return s.charAt(i)+"";  // 1 char Palindrome
        if (i+1 == j && s.charAt(i) == s.charAt(j)) return s.charAt(i)+""+s.charAt(i+1); // 2 char palindrome
        if (s.charAt(i) != s.charAt(j)) {           // first & last do not match
            String l = longestPalindromeSubsequence(s, i, j - 1);
            String r = longestPalindromeSubsequence(s, i + 1, j);
            return l.length() > r.length() ? l : r;
        } else { // first & last match
            return s.charAt(i) + longestPalindromeSubsequence(s, i+1, j-1) + s.charAt(j);
        }
    }

    public int lps(String s) {
        if (s == null || s.isEmpty()) return 0;
        return lps(s, 0, s.length() - 1);
    }

    /**
     * Refer : http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
     *
     * @param s
     * @param i
     * @param j
     * @return
     */
    public int lps(String s, int i, int j) {
        // Base Case 1: If there is only 1 character
        if (i == j)
            return 1;

        // Base Case 2: If there are only 2 characters and both are same
        if (s.charAt(i) == s.charAt(j) && i + 1 == j)
            return 2;

        // If the first and last characters match
        if (s.charAt(i) == s.charAt(j))
            return lps (s, i+1, j-1) + 2;

        // If the first and last characters do not match
        return Math.max(lps(s, i, j - 1), lps(s, i + 1, j));
    }


    public static void main(String[] args) {
        LongestPalindromeSubstring l = new LongestPalindromeSubstring();
        System.out.println(l.longestPalindrome("abb"));
        System.out.println(l.longestPalindrome("cababa"));
        System.out.println(l.longestPalindrome("abacdfgdcaba"));  // nasty case

        System.out.println(l.lps("abb"));
        System.out.println(l.lps("cababa"));
        System.out.println(l.lps("abacdfghdcaba"));
        System.out.println(l.lps("GEEKSFORGEEKS"));

    }

    /**
     * Let’s try another example:
     S = “abacdfgdcaba”, S’ = “abacdgfdcaba”.
     The longest common substring between S and S’ is “abacd”. Clearly, this is not a valid palindrome.

     We could see that the longest common substring method fails when there exists a reversed copy of a non-palindromic
     substring in some other part of S. To rectify this, each time we find a longest common substring candidate,
     we check if the substring’s indices are the same as the reversed substring’s original indices.
     If it is, then we attempt to update the longest palindrome found so far; if not, we skip this and find the next candidate.
     */
}

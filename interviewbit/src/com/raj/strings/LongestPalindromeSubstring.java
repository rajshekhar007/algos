package com.raj.strings;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class LongestPalindromeSubstring {

    // Refer to DP solution
    public String longestPalindrome(String s) {
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

    public static void main(String[] args) {
        LongestPalindromeSubstring l = new LongestPalindromeSubstring();
        System.out.println(l.longestPalindrome("abb"));
        System.out.println(l.longestPalindrome("cababa"));
        System.out.println(l.longestPalindrome("abacdfgdcaba"));  // see DP solution
    }
}

package com.raj.leetcode.substring;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * Created by rshekh1 on 9/18/17.
 */
public class LongestSubstringNonRepeat {

    /*
    Given a string, find the length of the longest substring without repeating characters.
    Examples:
    Given "abcabcbb", the answer is "abc", which the length is 3.
    Given "bbbbb", the answer is "b", with the length of 1.
    Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
    Given "abcabnbcbba" answer is 4.
    */

    int comparisons = 0;

    public void run(String text) {
        comparisons = 0;
        System.out.println(this.OofNxLongestSubStringLength(text) + ", comparisons=" + comparisons);

        comparisons = 0;
        System.out.println(this.OofN(text) + ", comparisons=" + comparisons);
    }

    /**
     * Runtime = O(n-k)xk, where k = length of longest substring  -> O(nk)
     * Space = O(k)
     *
     * @param text
     * @return
     */
    public String OofNxLongestSubStringLength(String text) {
        String longestSubStr = "";
        if (text == null || text.trim().isEmpty()) return longestSubStr;

        for (int i = 0; i < text.length(); i++) {
            if (longestSubStr.length() >= text.substring(i).length()) break;  // no need to continue if the longest substring is already bigger than rest of string
            Set charSet = Sets.newHashSet();
            String subStr = "";
            for (int j = i; j < text.length(); j++) {   comparisons ++;
                if (charSet.contains(text.charAt(j))) break;
                charSet.add(text.charAt(j));
                subStr += text.charAt(j);
                if (subStr.length() > longestSubStr.length()) longestSubStr = subStr;
            }
        }
        return longestSubStr;
    }

    static final int NO_OF_CHARS = 10;

    /**
     * O(kn) = O(n), where constant k = valid chars(a-z)
     * Uses visitedChars LinkedHashSet to maintain insertion order and for O(1) lookups
     * @param text
     * @return
     */
    public String OofN(String text) {
        String longest = "";
        if (text == null || text.trim().isEmpty()) return longest;

        Set<Character> visitedChars = Sets.newLinkedHashSet();
        for (int  i = 0; i < text.length(); i++) {    comparisons ++;
            char ch = text.charAt(i);
            if (visitedChars.contains(ch)) {
                // Get visited chars in the order of insertion
                String currentLong = "";
                for (char c : visitedChars) {   comparisons ++;
                    currentLong += c;
                }
                if (currentLong.length() > longest.length()) longest = currentLong;
                visitedChars.remove(ch);
            }
            visitedChars.add(ch);
        }
        return longest;
    }

    /**
     * Table stores the length of substring by char
     * @param str
     * @return
     */
    static int OofNSolution2(String str) {
        int n = str.length();
        int cur_len = 1;    // length of current substring
        //String longest = "";
        int max_len = 1;    // result
        int prev_index;     //  previous index
        int i;
        int visited[] = new int[NO_OF_CHARS];

        /* Initialize the visited array as -1, -1 is
         used to indicate that character has not been
         visited yet. */
        for (i = 0; i < NO_OF_CHARS; i++) {
            visited[i] = -1;
        }

        /* Mark first character as visited by storing the
             index of first   character in visited array. */
        visited[str.charAt(0)-97] = 0;

        /* Start from the second character. First character is
           already processed (cur_len and max_len are initialized
         as 1, and visited[str[0]] is set */
        for(i = 1; i < n; i++) {
            prev_index = visited[str.charAt(i)-97];
            System.out.println(str.charAt(i) + ", prev_index=" + prev_index);
            /* If the current character is not present in
           the already processed substring or it is not
              part of the current NRCS, then do cur_len++ */
            int idx = i - cur_len;
            if(prev_index == -1 || idx > prev_index) {
                cur_len++;

            /* If the current character is present in currently
               considered NRCS, then update NRCS to start from
               the next character of previous instance. */
            }
            else {
                /* Also, when we are changing the NRCS, we
                   should also check whether length of the
                   previous NRCS was greater than max_len or
                   not.*/
                if(cur_len > max_len)
                    max_len = cur_len;

                cur_len = i - prev_index;
            }

            // update the index of current character
            visited[str.charAt(i)-97] = i;
            for (int j = 0; j < NO_OF_CHARS; j++) {
                if (visited[j] >= 0) System.out.print(" " + j + "->" + visited[j]);
            }
            System.out.println();
        }

        // Compare the length of last NRCS with max_len and
        // update max_len if needed
        if(cur_len > max_len) {
            max_len = cur_len;
        }

        return max_len;
    }

    public static void main(String[] args) {
        LongestSubstringNonRepeat longestSubstringNonRepeat = new LongestSubstringNonRepeat();
        getInput().forEach(input -> longestSubstringNonRepeat.run(input));
    }

    public static List<String> getInput() {
        return Lists.newArrayList("bbbbb", "abcdabcd", "abcabcbb", "abcabnbcbb");
    }


}

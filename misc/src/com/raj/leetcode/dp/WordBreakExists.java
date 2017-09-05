package com.raj.leetcode.dp;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * For example, given
 * s = "leetcode",
 * dict = ["lee", "leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 *
 * https://www.programcreek.com/2014/03/leetcode-word-break-ii-java/
 *
 * @author rshekh1
 */
public class WordBreakExists {

    int comparisons = 0;

    /**
     * O(n^2) solution = n+(n-1)+(n-2)....+2+1 = n(n+1)/2 worst case
     * Uses DP Table to track found solutions
     * @param text
     * @return
     */
    public boolean dpTable(String text) {
        comparisons = 0;
        int[] table = new int[text.length()+1];
        Arrays.fill(table, -1);
        table[0] = 0;  // init so that the loop below runs for prefix check condition
        for (int i = 0; i < text.length(); i++) {
            if (table[i] == -1) continue;  // we only want to find suffixes if the prefix is valid word
            for (int j = i+1; j <= text.length(); j++) {  // j runs length+1 as j starts from i+1 th and has to scan till the end
                comparisons++;
                if (isValidWord(text.substring(i, j))) {
                    table[j] = i;  // Mark as start index i.e. i for this endIndex array position, so that we know what's the start of this word
                }
            }
        }

        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "->" + table[i]);
        }

        System.out.println("Comparisons = " + comparisons);

        return table[text.length()] != -1;  // the last index of table will only be set if there was at least one word break combination found for text
    }

    /**
     * Similar to WordBreak problem in com.raj.leetcode.dp, also print the combinations
     */
    public List<String> dpTableDFS(String text) {
        // We'll need to have an array with list of strings for each array position to store multiple words, if any
        List<String>[] table = new List[text.length()+1];
        table[0] = new ArrayList<>();  // init

        for (int i = 0; i < text.length(); i++) {
            if (table[i] == null) continue;
            for (int j = i+1; j <= text.length(); j++) {
                String subStr = text.substring(i, j);
                if (isValidWord(subStr)) {
                    if (table[j] == null) table[j] = new ArrayList<>();
                    table[j].add(subStr);
                }
            }
        }

        List<String> result = Lists.newArrayList();

        // dfs to find word chains, starting from reverse so as to be able to traverse to correct table index after finding word by subtracting it's length
        dfsReverse(table, result, "", table.length-1);

        return result;
    }

    public void dfsReverse(List<String> [] table, List<String> result, String soFar, int i) {
        if (i == 0) result.add(soFar);  // found a combination, add to final result list

        for (String subResult : table[i]) {
            dfsReverse(table, result, subResult + " " + soFar, (i-subResult.length()));
        }

    }

    private Set dict = Sets.newHashSet("lee", "leet", "etc" ,"ode", "code", "cat", "cats", "and", "sand", "dog");

    public boolean isValidWord(String s) {
        return dict.contains(s);
    }

    public static void main(String[] args) {
        WordBreakExists wordBreakExists = new WordBreakExists();
        //getInput().forEach(input -> System.out.println("Found: " + wordBreakExists.dpTable(input) + "\n"));
        getInput().forEach(input -> System.out.println("Found: " + wordBreakExists.dpTableDFS(input) + "\n"));
    }

    public static List<String> getInput() {
        return Lists.newArrayList("catsanddog", "leetcode");
    }
}

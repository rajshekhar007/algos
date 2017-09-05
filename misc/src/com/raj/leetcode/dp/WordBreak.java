package com.raj.leetcode.dp;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by rshekh1 on 9/2/17.
 */
public class WordBreak {

    /*
     Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word.
     You may assume the dictionary does not contain duplicate words. Return all such possible sentences.

     For example, given
     s = "catsanddog",
     dict = ["cat", "cats", "and", "sand", "dog"].

     A solution is ["cats and dog", "cat sand dog"].
     */

    /*
                            catsanddog
               /              |                \                         \
            c,f(atsanddog)    ca,f(tsanddog)   cat,f(sanddog)             cats,f(anddog)
             |                  |                |         \     \              |
           X(c not word)      X(ca no word)    s,f(anddog) sa.. sand,f(dog)   ...and,f(dog)
                                              /             \     |
                                             X               X   ... dog
     */
    int comparisons = 0;
    private boolean found = false;

    public void reset() {
        cache.clear(); comparisons = 0; found = false;
    }

    public void run(String text) {
        reset();
        this.recursive("", text);
        System.out.println(found + ", comparisons=" + comparisons);

        reset();
        comparisons = 0;
        this.dpRecursive(text);
        System.out.println(cache + ", comparisons=" + comparisons);
    }

    /**
     * O(2^n) solve -
     * https://stackoverflow.com/questions/31370674/time-complexity-of-the-word-break-recursive-solution
     *
     */
    public void recursive(String soFar, String remaining) {
        comparisons ++;
        // Base case (exit criteria)
        if (remaining == null || remaining.isEmpty()) {
            System.out.println(soFar);
            found = true;
            return;
        }

        // Recursive case
        String current = "";
        for (int i=0; i<remaining.length(); i++) {
            comparisons ++;
            current += remaining.charAt(i);
            if (isValidWord(current)) {   // recurse on remaining, if a valid prefix word is found
                recursive(soFar + " " + current, remaining.substring(i+1));
            }
        }
    }

    // Cache overlapping sub-problem results
    public HashMap<String, List<String>> cache = new HashMap<>();

    /**
     * Dynamic Programming solution using recursion and memoization -
     * http://www.zrzahid.com/word-break-problem/
     * @param
     * @return
     */
    public List<String> dpRecursive(String subProblem) {

        // f(subProblem) can have multiple solutions, hence maintain list to store various unique results
        List<String> subProblemResult = Lists.newArrayList();

        if (subProblem == null || subProblem.trim().isEmpty()) return subProblemResult;

        comparisons ++;
        if (cache.containsKey(subProblem)) {  // if f(remaining) is in cache, just return precomputed values
            return cache.get(subProblem);
        }

        if (isValidWord(subProblem)) {  // if subProblem is itself a valid word, then this is one solve.
            subProblemResult.add(subProblem);
            // But we still need to check for other permutations. Hence, don't return.
        }

        // Subproblem solve = Break word and recurse on them
        String current = "";
        for (int i = 0; i < subProblem.length(); i++) {  comparisons ++;

            current += subProblem.charAt(i);

            if (isValidWord(current)) {   // cat

                List<String> subProblemResultRecursive = dpRecursive(subProblem.substring(i+1)); // f(sanddog)

                // Subproblem Result = cat + f(sanddog)
                for (String subProblemResultRecursiveLineItem : subProblemResultRecursive) {   // may have mutiple string combinations
                    subProblemResult.add(current + " " + subProblemResultRecursiveLineItem);
                }
            }
        }

        // Now cache the newly found result(s) of subProblem
        cache.put(subProblem, subProblemResult);
        return subProblemResult;
    }

    public void dpTable(String text) {
        int[] table = new int[text.length()+1];  // DP Table to store
        Arrays.fill(table, -1); // Init dp array with -1 indicating no solution

        for (int i = 0; i < text.length(); i++) {

        }

    }

    private Set dict = Sets.newHashSet("cat", "cats", "and", "sand", "dog", "a", "aa", "aaa", "aaaa", "aaaaa");

    public boolean isValidWord(String s) {
        comparisons ++;
        return dict.contains(s);
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        getInput().forEach(input -> wordBreak.run(input));
    }

    public static List<String> getInput() {
        return Lists.newArrayList("catsanddog", "aaaaaaaaaa");
    }

}

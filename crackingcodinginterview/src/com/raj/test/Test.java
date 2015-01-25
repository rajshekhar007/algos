package com.raj.test;

public class Test {

    /*
       # Use of Trie Data Structure(a 26-way branching tree) is suitable for String prefix searches
       # Gives a Big O(length of input word in findLongestPrefix) optimal run time complexity which is better than any
         other Data structure. For millions of inserted words in trie too, the search would only run for the length of
         input word! Hence it will be blazing fast...
       # HashMap would be suitable for full String searches. Not optimal for prefix searches as you would run upto
         O(n*l) worst case performance.

       For simplicity I am assuming:
        1. "ABC" is semantically equal to "abc" (Ignore case while insertion and searches)
        2. Only valid English alphabets allowed
        3. Per requirements, longest prefix should be one of the setup strings or inserted strings in trie

     */

    private Trie trie;

    public Test() {
        trie = new Trie();
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.setup(new String[]{" "});
        String longestPrefix = t.findLongestPrefix("abcdd");
        System.out.println("Longest Prefix is : " + longestPrefix);


        // too lazy to write more tests, please alter inputs and run
    }

    public void setup(String[] prefixWords) {
        for (String s : prefixWords) {
            trie.insert(s.toLowerCase());  // lets keep everything in lowercase
        }
    }

    public String findLongestPrefix(String inputWord) {
        return trie.findLongestPrefix(inputWord.toLowerCase());   // trie has lowercase char nodes
    }

}

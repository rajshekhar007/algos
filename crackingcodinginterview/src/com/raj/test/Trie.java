package com.raj.test;

public class Trie {

    private Node root;

    Trie() {
        root = new Node(' ', false);
    }

    public void insert(String word) {

        Node node = root;
        String wordUpper = word.toLowerCase();
        for (int i = 0; i < wordUpper.length(); i++) {
            char ch = wordUpper.charAt(i);
            int idx = ch - 'a';
            if (node.childs[idx] == null) {
                // create node for this char
                Node newNode = new Node(ch, false);
                node.childs[idx] = newNode;
            }
            node = node.childs[idx];
        }
        node.isWord = true; // this last node is the last char in passed string, hence this node is a word in any case
    }

    public String findLongestPrefix(String inputWord) {

        Node node = root;
        String longestPrefixSoFar = "";
        int wordPtrIdx = 0; // keeps track of where last word was found in longestPrefix search

        for (int i = 0; i < inputWord.length(); i++) {

            if (node == null) {
                break;  // no need to go further, bail out
            }

            // process this node
            char ch = inputWord.charAt(i);
            int idx = ch - 'a';

            if (node.childs[idx] != null) {

                // if this child is not null, add it to longestPrefixSoFar, but returned longestPrefix would end with
                // isWord flag(per requirement it should be a valid inserted word), hence update wordPtr

                longestPrefixSoFar += node.childs[idx].ch;

                if (node.childs[idx].isWord) {
                    wordPtrIdx = longestPrefixSoFar.length();
                }

            }

            node = node.childs[idx];
        }

        return longestPrefixSoFar.substring(0, wordPtrIdx);  // return string till wordPtrIdx, trim chars after that
    }

    private class Node {
        char ch;
        boolean isWord;  // indicates the end of input string
        Node[] childs;   // children of this node

        Node(char ch, boolean isWord) {
            this.ch = ch;
            this.isWord = isWord;
            childs = new Node[26];  // English has 26 alphabets, ignoring case
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", isWord=" + isWord +
                    '}';
        }
    }

}

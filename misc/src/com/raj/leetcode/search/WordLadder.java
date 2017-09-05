package com.raj.leetcode.search;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

/**
 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 Only one letter can be changed at a time.
 Each transformed word must exist in the word list.
 For example,
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log","cog"]

 Shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 * Created by rshekh1 on 9/4/17.
 */
public class WordLadder {

    int comparisons = 0;

    class Node {
        String word;
        String transformation = "";
        int length = 0;
        Node(String s) {
            word = s;
            addTransformation(s);
        }
        Node(String s, String t, int l) {
            transformation = t;
            word = s;
            length = l;
            addTransformation(s);
        }

        public void addTransformation(String s) {
            length ++;
            transformation += transformation.isEmpty() ? s : " -> " + s;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("word", word)
                    .add("transformation", transformation)
                    .add("length", length)
                    .toString();
        }
    }

    public Node run(String input) {
        String start = input.split(",")[0];
        String end = input.split(",")[1];
        return bfs(start, end);
    }

    /**
     * BFS will return the shortest transformation by design
     */
    public Node bfs(String start, String end) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start));

        Set<String> alreadyTransformed = Sets.newHashSet();  // no need for repetitive transformations as it will increase length
        alreadyTransformed.add(start);

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (int i = 0; i < n.word.length(); i++) {  // flip ith char
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] wordChars = n.word.toCharArray();
                    wordChars[i] = ch;
                    String newWord = String.valueOf(wordChars);
                    Node newNode = new Node(newWord, n.transformation, n.length);

                    if (alreadyTransformed.contains(newWord) || !isValidWord(newWord)) continue;
                    alreadyTransformed.add(newWord);

                    if (newWord.equals(end)) return newNode;
                    else queue.add(newNode);
                }
            }
        }
        return null;
    }

    private Set dict = Sets.newHashSet("hot","dot","dog","lot","log","cog");

    public boolean isValidWord(String s) {
        comparisons ++;
        return dict.contains(s);
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        getInput().forEach(input -> System.out.println(wordLadder.run(input)));
    }

    public static List<String> getInput() {
        return Lists.newArrayList("hit,cog");
    }


}

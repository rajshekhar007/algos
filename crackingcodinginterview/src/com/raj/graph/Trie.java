package com.raj.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: sraj1
 * Date:    10/31/12
 */
public class Trie implements Serializable {

    long mem0, mem1;  // for memory usage calculations
    private Node root;

    Trie() {
        mem0 = Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory();

        root = new Node(' ', false);
    }

    public void insert(Set<String> dictionaryWords) {
        Iterator<String> it = dictionaryWords.iterator();
        while (it.hasNext())
            this.insert(it.next());
    }

    public void insert(String word) {

        Node node = root;
        String wordUpper = word.toUpperCase();
        for (int i = 0; i < wordUpper.length(); i++) {
            char ch = wordUpper.charAt(i);
            int idx = ch - 'A';
            if (node.childs[idx] == null) {
                // create node for this char
                Node newNode = new Node(ch, false);
                node.childs[idx] = newNode;
            }
            node = node.childs[idx];
        }
        node.isWord = true; // this last node is the last char in passed string, hence this node is a word in any case
    }

    public boolean contains(String word) {
        Node node = root;
        String wordUpper = word.toUpperCase();
        for (int i = 0; i < wordUpper.length(); i++) {
            char ch = wordUpper.charAt(i);
            int idx = ch - 'A';
            if (node.childs[idx] == null) return false;
            node = node.childs[idx];
        }
        return node.isWord;
    }

    public boolean containsPrefix(String prefix) {
        Node node = root;
        String prefixUpper = prefix.toUpperCase();
        for (int i = 0; i < prefixUpper.length(); i++) {
            char ch = prefixUpper.charAt(i);
            int idx = ch - 'A';
            if (node.childs[idx] == null) return false;
            node = node.childs[idx];
        }
        return true;
    }

    public List<String> getWordsWithPrefix(String prefix) {
        Node node = root;
        String prefixUpper = prefix.toUpperCase();
        for (int i = 0; i < prefixUpper.length(); i++) {
            char ch = prefixUpper.charAt(i);
            int idx = ch - 'A';
            if (node.childs[idx] == null) return null;
            node = node.childs[idx];
        }

        // we are at the node from where we have to search all words
        List<String> list = new ArrayList<String>();
        doDFS(node, prefix, list);
        return list;
    }

    private void doDFS(Node node, String prefix, List<String> list) {
        if (node.isWord) list.add(prefix);
        prefix += node.ch;
        for (Node n : node.childs)
            doDFS(n, prefix, list);
    }

    public void delete(String word) {

    }

    public long getMemoryUsed() {
        mem1 = Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory();
        return mem1 - mem0;
    }

    private class Node implements Serializable {
        char ch;
        boolean isWord;
        Node[] childs;

        Node(char ch, boolean isWord) {
            this.ch = ch;
            this.isWord = isWord;
            childs = new Node[26];
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

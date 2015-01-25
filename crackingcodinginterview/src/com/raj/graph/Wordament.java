package com.raj.graph;

import com.raj.graph.helper.FileReaderHelper;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author: sraj1
 * Date:    10/30/12
 */
public class Wordament {

    /**
     * @link http://www.careercup.com/question?id=10506747
     * <p/>
     * BOGGLE game : Write a program to implement Boggle Game.
     * <p/>
     * You are given a 4x4 matrix of letters and a dictionary, find all the valid words in the matrix. Following are the conditions
     * 1. If a letter is used, it should not be used again in the same word search
     * 2. The word path can be of any direction
     * 3. There has to be a path of the letters forming the word( in other words all the letters in the word must have to adjacent to one another)
     * <p/>
     * Example:
     * D A T H
     * C G O A
     * S A T L
     * B E D G
     * <p/>
     * Some of the Valid words are:
     * DATA, HALO, HALT, SAG, BEAT, TOTAL, GLOT, DAG
     * <p/>
     * Not valid words:
     * DAGCD ( D cannot be used again)
     * DOG ( There is no path between letters)
     */

    /*
    Algo:
    - initialize a N X N board with random character at each 2D array position
    - keep visited 2D boolean array flag to check for loop scenarios, reset it after backtracking everytime
    - DFS or BFS? DFS is better as it has lower space requirements, faster, and we have to traverse starting with each element anyways
    - for(i in rows)
        for(j in cols)
        doDFS(i,j,"");
    - doDFS(i,j,soFar)
        soFar+= board[i][j];
        visited[i][j]=true;
        if(soFar is valid word) print soFar;
        for(ii=-1 to 1)
          for(jj=-1 to 1)
            int row=i+ii;
            int col=j+jj;
            if(!visited[row][col])
              doDFS(row,col,soFar);
              // backtrack
              visited[row][col]=false;
    - O(n2)
    */

    //private HashSet<String> dictionaryWords = new HashSet<String>();
    private Trie trie;
    private char[][] board;
    private int nRows, nCols;
    private boolean visited[][];
    private HashSet<String> foundWords;

    public Wordament(int r, int c) {
        nRows = r;
        nCols = c;
        trie = new Trie();
    }

    public static void main(String[] args) {
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        Date date = new Date();
        long start = date.getTime();

        Wordament wordament = new Wordament(8, 8);
        wordament.initBoard();
        wordament.solve();

        date = new Date();
        long end = date.getTime();

        System.out.println("Memory = " + wordament.trie.getMemoryUsed() / (1024 * 1024) + " MB" + "\nTime taken = " + (end - start) / 1000 + " secs");

    }

    private void initBoard() {
        board = new char[nRows][nCols];
        visited = new boolean[nRows][nCols];
        foundWords = new HashSet<String>();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                int random = ((int) (Math.random() * 100)) % 26;
                char ch = (char) (65 + random);
                if (ch == 'Z' || ch == 'X' || ch == 'K' || ch == 'V' || ch == 'Q') ch = 'A';
                board[i][j] = ch;
            }
        }

        // init Trie helper to check if a word is valid
        FileReaderHelper fileReaderHelper = new FileReaderHelper();
        fileReaderHelper.readWords();
        trie.insert(fileReaderHelper.getDictionaryWords());

        checkSerilizedTrieSize();

        printBoard();
    }

    public void printBoard() {
        for (int i = 0; i < nRows; i++) {
            System.out.println();
            for (int j = 0; j < nCols; j++) {
                char c = board[i][j];
                System.out.print(c + "  ");
            }
        }
        System.out.println();
    }

    public void solve() {
        System.out.println("\nPrinting words: ");
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
                doDFS(i, j, "");

        TreeSet<String> sortedWords = new TreeSet<String>(new WordamentComparator());
        sortedWords.addAll(foundWords);
        for (String word : sortedWords)
            System.out.println(word);

    }

    /**
     * Use of TRIE to not continue DFS if the prefix is not in dictionary, greatly improved runtime
     */
    private void doDFS(int i, int j, String soFar) {
        // boundary checks
        if (i < 0 || i >= nRows || j < 0 || j >= nCols) return;
        if (visited[i][j]) return;
        if (!trie.containsPrefix(soFar)) return;    // if no words start with this prefix, just return...

        soFar += board[i][j];
        visited[i][j] = true;    // mark visited and go ahead with this choice

        if (soFar.length() > 2 && trie.contains(soFar.toUpperCase())) {
            //System.out.println(soFar);
            foundWords.add(soFar);
        }

        for (int ii = -1; ii <= 1; ii++) {
            for (int jj = -1; jj <= 1; jj++) {
                int row = i + ii;
                int col = j + jj;

                doDFS(row, col, soFar);    // starting this character do all DFS for neighbors
            }
        }

        visited[i][j] = false;          // backtrack ... undo mark visited
    }

    private void checkSerilizedTrieSize() {
        // DEBUG: Check file size of trie dict via serialization... this is very very slow
        /*System.out.println(trie.contains("ACT"));
        FileReaderHelper.serialize(trie);
        trie = (Trie) FileReaderHelper.deserialize();
        System.out.println(trie.contains("ACT"));*/
    }

    private class WordamentComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o2.length() != o1.length())
                return o2.length() - o1.length();
            else return o1.compareTo(o2);
        }
    }

}

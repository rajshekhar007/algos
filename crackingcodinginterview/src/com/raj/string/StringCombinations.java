package com.raj.string;

/**
 * @author: sraj1
 * Date:    10/8/12
 */
public class StringCombinations {

    // Equivalent to printing all possible subsets (can also be implemented as Bit mask i.e. print those positions for which bit is 1 in 010)
    static void printCombinations(String soFar, String inp) {

        //if(inp.length() == 0)  --> not required as we print all combinations, like 1 letter, 2 letter words and necessarily full formed n letter words
        System.out.println(soFar);

        for (int i = 0; i < inp.length(); i++) {
            printCombinations(soFar + inp.charAt(i), inp.substring(i + 1));  // now input is remaining word starting next character as we have already printed everything with letter before
        }
    }

    static void printPermutations(String soFar, String inp) {

        if (inp.length() == 0)
            System.out.println(soFar);

        for (int i = 0; i < inp.length(); i++) {
            printPermutations(soFar + inp.charAt(i), inp.substring(0, i) + inp.substring(i + 1));
        }

    }

    public static void main(String[] args) {
        printCombinations("", "abcd");
        System.out.println("\n");
        //printPermutations("", "abcd");
    }
}

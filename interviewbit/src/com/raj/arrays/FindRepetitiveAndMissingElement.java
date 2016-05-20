package com.raj.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rshekh1 on 4/23/16.
 */
public class FindRepetitiveAndMissingElement {

    /**
     * You are given a read only array of n integers from 1 to n.

     Each integer appears exactly once except A which appears twice and B which is missing.

     Return A and B.

     Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

     Note that in your output A should precede B.

     Example:

     Input:[3 1 2 5 3]

     Output:[3, 4]

     A = 3, B = 4
     * @param a
     * @return
     */
    public ArrayList<Integer> repeatedNumber(final List<Integer> a) {
        ArrayList<Integer> res = new ArrayList<>(2);
        boolean[] bitArr = new boolean[a.size()+1];

        for (int i=0; i<a.size(); i++) {
            if (bitArr[a.get(i)] == true) res.add(0, a.get(i));
            else bitArr[a.get(i)] = true;
        }

        for (int i=1; i<bitArr.length; i++) {
            if (bitArr[i] == false) res.add(1, i);
        }

        return res;
    }

    public static void main(String[] args) {
        FindRepetitiveAndMissingElement f = new FindRepetitiveAndMissingElement();
        List<Integer> a = new ArrayList<>();
        a.add(3); a.add(1); a.add(2); a.add(5); a.add(3);
        System.out.println(f.repeatedNumber(a));
    }
}

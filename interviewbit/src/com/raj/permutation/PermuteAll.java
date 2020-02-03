package com.raj.permutation;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @author rshekh1
 */
public class PermuteAll {

    /**
     * Given a collection of numbers, return all possible permutations.
     *
     * Example:
     *
     * [1,2,3] will have the following permutations:
     *
     * [1,2,3]
     * [1,3,2]
     * [2,1,3]
     * [2,3,1]
     * [3,1,2]
     * [3,2,1]
     *
     */

    ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
        if (A == null || A.isEmpty() || A.size() == 1) {
            res.add(A); return res;
        }
        permute(new ArrayList<>(), A);
        return res;
    }

    public void permute(ArrayList<Integer> soFar, ArrayList<Integer> remaining) {
        // exit criteria
        if (remaining.isEmpty()) res.add(soFar);

        // recursive criteria
        for (int i = 0; i < remaining.size(); i++) {
            permute(getSoFar(soFar, remaining.get(i)), getRemaining(i, remaining));
        }
    }

    private ArrayList<Integer> getSoFar(ArrayList<Integer> soFar, Integer intToAdd) {
        ArrayList<Integer> list = new ArrayList<>(soFar);
        list.add(intToAdd);
        return list;
    }

    private ArrayList<Integer> getRemaining(Integer idx, ArrayList<Integer> remaining) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < remaining.size(); i++) {
            if (i != idx) list.add(remaining.get(i));
        }
        return list;
    }

    public static void main(String[] args) {
        PermuteAll permuteAll = new PermuteAll();
        permuteAll.permute(Lists.newArrayList(1,2,3));
        System.out.println(permuteAll.res);
    }

}

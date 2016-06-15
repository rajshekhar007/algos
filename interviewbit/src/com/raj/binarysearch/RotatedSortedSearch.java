package com.raj.binarysearch;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rshekh1 on 6/11/16.
 */
public class RotatedSortedSearch {

    public int search(final List<Integer> a, int t) {
        int s = 0, e = a.size()-1;
        while (s <= e) {
            int m = s + (e-s)/2;
            if (a.get(m) == t) return m;
            int c = isCloserTo(s, m, e, t, a);
            if (c == m) {
                if (t < a.get(m)) e = m-1;
                else s = m+1;
            } else if (c == s) e = m-1;
            else s = m+1;
        }
        return -1;
    }

    private int isCloserTo(int s, int m, int e, int t, List<Integer> a) {
        if (Math.abs(t-a.get(s)) < Math.abs(t-a.get(m)) && Math.abs(t-a.get(s)) < Math.abs(t-a.get(e))) return s;
        if (Math.abs(t-a.get(m)) < Math.abs(t-a.get(s)) && Math.abs(t-a.get(m)) < Math.abs(t-a.get(e))) return m;
        return e;
    }

    public static void main(String[] args) {
        RotatedSortedSearch r = new RotatedSortedSearch();
        System.out.println(r.search(Arrays.asList(4,5,6,7,0,1,2), 4));
    }


}

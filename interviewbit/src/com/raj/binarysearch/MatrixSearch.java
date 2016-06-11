package com.raj.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rshekh1 on 6/9/16.
 */
public class MatrixSearch {

    public int searchMatrix(ArrayList<List<Integer>> a, int b) {
        int row = -1, start = 0, end = a.size()-1;
        int m = end, n = a.get(0).size()-1;
        while(start <= end) {
            int mid = start + (end-start)/2;
            if (b == a.get(mid).get(0)) return 1;
            if (a.get(mid).get(0) < b && b <= a.get(mid).get(n)) {
                row = mid; break;
            }
            if (b < a.get(mid).get(0)) end = mid-1;
            if (b > a.get(mid).get(0)) start = mid+1;
        }
        if (row == -1) return 0;

        int idx = 0; start = 0; end = a.get(row).size()-1;
        while(start <= end) {
            int mid = start + (end-start)/2;
            if (b == a.get(row).get(mid)) return 1;
            if (b < a.get(row).get(mid)) end = mid-1;
            if (b > a.get(row).get(mid)) start = mid+1;
        }

        return idx;
    }

    public static void main(String[] args) {
        MatrixSearch m = new MatrixSearch();
        ArrayList<List<Integer>> a = new ArrayList<>();
        a.add(Arrays.asList(3, 3, 11, 12, 14));
        a.add(Arrays.asList(16, 17, 30, 34, 35));
        a.add(Arrays.asList(80, 84, 85, 85, 88));
        a.add(Arrays.asList(88, 91, 92, 93, 94));
        System.out.println(m.searchMatrix(a, 94));
    }
}

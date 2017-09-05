package com.raj.arrays;

import java.util.*;

/**
 * Created by rshekh1 on 6/7/16.
 */
public class LargestNumber {

    public String largestNumber(final List<Integer> a) {
        if (a == null || a.isEmpty()) return "";
        String largestN = "";
        List<Integer> arr = new ArrayList<>(a);
        for (int i=0; i<arr.size(); i++) {
            int maxIdx = findMax(arr, i);
            largestN += arr.get(maxIdx) == 0 ? "" : arr.get(maxIdx);
            swap(arr, i, maxIdx);
        }
        return largestN.isEmpty() ? "0" : largestN;
    }

    public void swap(List<Integer> arr, int i, int j) {
        int t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }

    public int findMax(List<Integer> arr, int j) {
        int maxIdx = j;
        for (int i=j+1; i<arr.size(); i++) {
            maxIdx = compare(arr, maxIdx, i);
        }
        return maxIdx;
    }

    public int compare(List<Integer> arr, int i, int j) {
        String n1Str = arr.get(i) + "" + arr.get(j) + "";
        String n2Str = arr.get(j) + "" + arr.get(i) + "";
        if (Long.parseLong(n1Str) >= Long.parseLong(n2Str)) {
            return i;
        } else return j;
    }

    public static void main(String[] args) {
        LargestNumber l = new LargestNumber();
        System.out.println(l.largestNumber(Arrays.asList(3,30,34,5,9)));
        System.out.println(l.largestNumber1(Arrays.asList(9,999,9999,99,9998)));
    }

        public String largestNumber1(final List<Integer> a) {
            if (a == null || a.isEmpty()) return "";
            String largestN = "";
            List<N> sortedList = new ArrayList<>();
            int multiplier = 1, max = -99999;
            for (Integer i : a) {
                if (i > max) max = i;
            }
            while (max > 0) {
                max/=10;
                multiplier*=10;
            }
            multiplier/=10;
            for (Integer i : a) {
                float f = (i*1f)/multiplier;
                String s = i+"";
                while (f < 1) {
                    s+= s.charAt(0)+"";
                    f*=10;
                }
                sortedList.add(new N(i, s == "" ? i+"" : s));
            }
            Collections.sort(sortedList, new Comparator<N>(){
                public int compare(N n1, N n2) {
                    return n2.s.compareTo(n1.s);
                }
            });
            for (N n : sortedList) {
                largestN += n.i + "";
            }
            return largestN;
        }

        class N {
            Integer i; String s;
            N(int ii, String ss) {
                i = ii; s = ss;
            }
        }


}

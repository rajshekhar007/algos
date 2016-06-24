package com.raj.hashing;

import java.util.*;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class MaxPointsStraightLine {

    public int maxPoints1(List<Integer> a, List<Integer> b) {
        Map<Double,Integer> map = new HashMap<>();
        for (int i=0; i<a.size(); i++)
            for (int j=i+1; j<a.size(); j++) {
                int x1 = a.get(i); int y1 = a.get(i);
                int x2 = a.get(j); int y2 = a.get(j);
                double n = (y2/1d) - (y1/1d); double d = (x2/1d) - (x1/1d);
                Double slope = (d==0) ? 99999999d : n/d;
                Integer count = map.get(slope);
                map.put(slope, count == null ? 1 : ++count);
            }
        int max = 0;
        for (int i : map.values()) {
            max = i > max ? i : max;
        }
        return max;
    }

    /**
     * http://yucoding.blogspot.com/2013/12/leetcode-question-max-points-on-line.html
     */
    private HashMap<Double, Integer> hashMap;

    public int maxPoints(List<Integer> A, List<Integer> B) {

        hashMap = new HashMap<>();

        if (A == null || B == null)
            return -1;

        if (A.size() == 0)
            return 0;

        int n = A.size();
        int x1, y1, x2, y2;
        int val;
        int max = 0;

        for (int i = 0; i < n; i++) {

            x1 = A.get(i);
            y1 = B.get(i);
            hashMap.clear();

            for (int j = 0; j < n; j++) {

                if (i == j)
                    continue;

                x2 = A.get(j);
                y2 = B.get(j);

                double slope = y2 - y1;
                int den = x2 - x1;

                if (den == 0)
                    slope = Double.POSITIVE_INFINITY;
                else
                    slope = slope / den;

                val = 1;

                if (hashMap.containsKey(slope)) {
                    val = hashMap.get(slope) + 1;
                }

                hashMap.put(slope, val);

            }

            for (Map.Entry<Double, Integer> entry : hashMap.entrySet()) {
                val = entry.getValue();
                max = Math.max(max, val);
            }
        }

        return max + 1;
    }


    public static void main(String[] args) {
        MaxPointsStraightLine m = new MaxPointsStraightLine();
        System.out.println(m.maxPoints(Arrays.asList(1,2,3,1), Arrays.asList(1,2,3,1)));
    }
}

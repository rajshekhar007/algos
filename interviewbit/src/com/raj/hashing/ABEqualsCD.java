package com.raj.hashing;

import com.google.common.base.*;
import com.google.common.base.Objects;

import java.util.*;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class ABEqualsCD {

    /**
     * Given an array A of integers, find the index of values that satisfy A + B = C + D, where A,B,C & D are integers values in the array

     Note:

     1) Return the indices `A1 B1 C1 D1`, so that
     A[A1] + A[B1] = A[C1] + A[D1]
     A1 < B1, C1 < D1
     A1 < C1, B1 != D1, B1 != C1

     2) If there are more than one solutions,
     then return the tuple of values which are lexicographical smallest.

     Assume we have two solutions
     S1 : A1 B1 C1 D1 ( these are values of indices int the array )
     S2 : A2 B2 C2 D2

     S1 is lexicographically smaller than S2 iff
     A1 < A2 OR
     A1 = A2 AND B1 < B2 OR
     A1 = A2 AND B1 = B2 AND C1 < C2 OR
     A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 < D2
     Example:

     Input: [3, 4, 7, 1, 2, 9, 8]
     Output: [0, 2, 3, 5] (O index)
     If no solution is possible, return an empty list.
     */

    class Value {
        int A,B;

        public Value(int a, int b) {
            A = a;
            B = b;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("A", A)
                    .add("B", B)
                    .toString();
        }
    }

    public List<Integer> equal(List<Integer> a) {
        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer,Value> sumMap = new HashMap<>();  // stores sum and their indices
        Map<Value, Value> sortedResult = new TreeMap<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Map.Entry<Value,Value> me1 = (Map.Entry<Value, Value>) o1;
                Map.Entry<Value,Value> me2 = (Map.Entry<Value, Value>) o2;
                if (me1.getKey().A < me2.getKey().A) return -1;
                // keep adding other conditions.......
                return 0;
            }
        });
        for (int i = 0; i < a.size(); i++) {
            for (int j = i+1; j < a.size(); j++) {
                if (i == j) continue;
                int sum = a.get(i) + a.get(j);
                Value value = sumMap.get(sum);
                if (value != null) {
                    res.add(value.A);
                    res.add(value.B);
                    res.add(i);
                    res.add(j);
                    return res;
                }
                if (!sumMap.containsKey(sum)) {
                    sumMap.put(sum, new Value(i,j));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ABEqualsCD a = new ABEqualsCD();
        System.out.println(a.equal(Arrays.asList(3, 4, 7, 1, 2, 9, 8)));
    }
}

package com.raj.strings;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class CompareVersionNumbers {

    public int compareVersion(String a, String b) {
        if (a == null || a.isEmpty()) return -1;
        if (b == null || b.isEmpty()) return 1;
        if (!a.contains(".") && !b.contains(".")) {
            return Double.valueOf(a).compareTo(Double.valueOf(b));
        }
        String[] A = a.split("\\.");
        String[] B = b.split("\\.");
        int i=0;
        while(i<A.length && i<B.length) {
            int val = Double.valueOf(A[i]).compareTo(Double.valueOf(B[i]));
            if (val != 0) return val;
            i++;
        }

        if (A.length == B.length) return 0;

        if (A.length > B.length) {
            while(i < A.length && Double.valueOf(A[i]) == 0) i++;
            if (A.length == i) return 0;    // all were zeroes
            else return 1;  // some positive number was there after zero
        } else {  // (B.length > A.length)
            while(i < B.length && Double.valueOf(B[i]) == 0) i++;
            if (B.length == i) return 0;    // all were zeroes
            else return -1;  // some positive number was there after zero
        }

    }

    public static void main(String[] args) {
        CompareVersionNumbers c = new CompareVersionNumbers();
        System.out.println(c.compareVersion("1.0", "1"));
    }
}

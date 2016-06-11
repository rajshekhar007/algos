package com.raj.arrays;

import java.util.ArrayList;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class ConcentricMatrix {

    public ArrayList<ArrayList<Integer>> prettyPrint(int a) {
        int n = (2*a)-1;
        int m = n/2;
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i=0; i<n; i++) {
            arr.add(new ArrayList<>());
            for (int j=0; j<n; j++) {
                int d = Math.max(Math.abs(m-i), Math.abs(m-j));
                arr.get(i).add(d + 1);
            }
        }

        for (int i=0; i<n ; i++) {
            for (int j=0; j<n ; j++) {
                System.out.print(arr.get(i).get(j) + " ");
            }
            System.out.println();
        }
        return arr;
    }

    public static void main(String[] args) {
        ConcentricMatrix c = new ConcentricMatrix();
        c.prettyPrint(4);
    }
}

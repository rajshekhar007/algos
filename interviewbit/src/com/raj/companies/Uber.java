package com.raj.companies;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class Uber {

    /**
     * Problem Statement
     You are given two circles, A and B, on a Cartesian plane, each defined by three descriptors:

     X: the x-coordinate of the circle's center
     Y: the y-coordinate of the circle's center
     R: the radius of the circle

     Circles A and B will both be centered either on the X-axis (i.e.: YA = 0 and YB = 0), or on the Y-axis (i.e.: XA = 0 and XB = 0).

     A pair of circles (A and B) will have one of the following relationship types:

     Touching: they touch each other at a single point.
     Concentric: they have the same center point.
     Intersecting: they intersect each other (touching at two points).
     Disjoint-Outside: disjoint with one existing outside of the other.
     Disjoint-Inside: disjoint with one contained inside the other (but not concentric).

     Complete the circles function which takes an array of strings, info, as its parameter. Each string element in info contains six space-separated integers denoting a test case (as shown in the Input Format). The function should return an array of strings where the ith element in the return array is the relationship for the circles defined in the ith element of info.

     Input Format
     The first line contains an integer, N (the number of test cases).
     The N subsequent lines of test cases each contain six space-separated integers describing the X, Y, and R values for circles A and B, respectively. For example:

     N
     XA0 YA0 RA0 XB0 YB0 RB0
     ...
     XAN-1 YAN-1 RAN-1 XBN-1 YBN-1 RBN-1

     Note: Reading input from stdin and calling circles is handled for you by the locked code in the editor. Your task is to process the array of input strings in circles.

     Constraints
     1 ≤ N ≤ 5000
     0 ≤ X,Y,R ≤ 5000

     Output Format
     Your circles function should return an array of N strings where the ith element is the relationship for the circles in info[i]. Recall that the relationships defined in the Problem Statement are Touching, Disjoint-Inside, Disjoint-Outside, Concentric, and Intersecting.

     Note: Outputting the array returned by circles is handled for you by the locked code in the editor.

     Sample Input 0
     4
     12 0 21 14 0 23
     0 45 8 0 94 9
     35 0 13 10 0 38
     0 26 8 0 9 25

     Sample Output 0
     Touching
     Disjoint-Outside
     Touching
     Touching

     Sample Input 1
     5
     0 5 9 0 9 7
     0 15 11 0 20 16
     26 0 10 39 0 23
     37 0 5 30 0 11
     41 0 0 28 0 13

     Sample Output 1
     Intersecting
     Touching
     Touching
     Intersecting
     Touching
     */

    static String[] circles(String[] info) {

        if (info == null || info.length == 0) {
            return null;
        }

        int numInput = info.length;
        String[] result = new String[numInput];

        for (int i = 0; i < numInput; i++) {
            // Algo to find the answer
            String[] sArr = info[i].split(" ");
            // A's points
            int r = Integer.valueOf(sArr[2]);
            int Acenter = Integer.valueOf(sArr[0]) == 0 ? Integer.valueOf(sArr[1]) : Integer.valueOf(sArr[0]);
            int Astart = Acenter - r;
            int Aend = Acenter + r;

            // B's points
            r = Integer.valueOf(sArr[5]);
            int Bcenter = Integer.valueOf(sArr[3]) == 0 ? Integer.valueOf(sArr[4]) : Integer.valueOf(sArr[3]);
            int Bstart = Bcenter - r;
            int Bend = Bcenter + r;

            // Concentric: they have the same center point.
            //------C-----
            //  ----C---
            // => A's C == B's C
            if (Acenter == Bcenter) result[i] = "Concentric";

            // Intersecting: they intersect each other (touching at two points).
            // 5-----C------14    (overlap)
            //   9-----C-------16
            // => A's start is before/after B's & A's end is before/after B's & C don't match
            if ((Astart < Bstart && Aend < Bend && Aend < Bend && Acenter < Bcenter && Acenter > Bstart)
                    || (Bstart < Astart && Bend < Aend && Bend < Aend && Bcenter <= Acenter && Bcenter > Acenter)) result[i] = "Intersecting";


                //Disjoint-Outside: disjoint with one existing outside of the other.
                //-----C-----       -------C------
                // => A's end is before B's start or vice versa
            else if (Aend < Bstart || Bend < Astart) result[i] = "Disjoint-Outside";

                //Disjoint-Inside: disjoint with one contained inside the other (but not concentric).
                //--------C---------
                //     -----C----
                // => B's start & end is within A's start & end but C's don't match
            else if ( (Astart < Bstart && Aend > Bend) || (Bstart < Astart && Bend > Aend)) result[i] = "Disjoint-Inside";

                // Touching  - any one of the points match, other points shouldn't match
                //-----C---- -----C----

                // -------C------
                // -----C-----
                // => A's start matches B's start or A's end
            else if (Astart == Bstart || Aend == Bend) result[i] = "Touching";
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("/Users/rshekh1/myapps/workspace-intellij/Algos-1.0/algos/interviewbit/resource/uber_input.txt"));
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/rshekh1/myapps/workspace-intellij/Algos-1.0/algos/interviewbit/resource/out.txt"));
        String[] res;

        int _info_size = 0;
        _info_size = Integer.parseInt(in.nextLine().trim());
        String[] _info = new String[_info_size];
        String _info_item;
        for(int _info_i = 0; _info_i < _info_size; _info_i++) {
            try {
                _info_item = in.nextLine();
            } catch (Exception e) {
                _info_item = null;
            }
            _info[_info_i] = _info_item;
        }

        res = circles(_info);
        for(int res_i=0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}

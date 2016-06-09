package com.raj.arrays;

/**
 * Created by rshekh1 on 6/8/16.
 */
public class RotateMatrix {


/*

    the basica idea is to treat it as layers and we rotate one layer at a time..

    say we have a 4x4

    1   2   3   4
            5   6   7   8
            9   10  11  12
            13  14  15  16
    after we rotate it clockwise by 90 we get

    13  9   5   1
            14  10  6   2
            15  11  7   3
            16  12  8   4
    so let's decompose this, first we rotate the 4 corners essentially

            1           4


            13          16
    then we rotate the following diamond which is sort of askew

    2
            8
            9
            15
    and then the 2nd skewed diamond

    3
            5
            12
            14
    so that takes care of the outer edge so essentially we do that one shell at a time until

    finally the middle square (or if it's odd just the final element which does not move)

            6   7
            10  11
            so now let's figure out the indices of each layer, assume we always work with the outermost layer, we are doing

            [0,0] -> [0,n-1], [0,n-1] -> [n-1,n-1], [n-1,n-1] -> [n-1,0], and [n-1,0] -> [0,0]
            [0,1] -> [1,n-1], [1,n-2] -> [n-1,n-2], [n-1,n-2] -> [n-2,0], and [n-2,0] -> [0,1]
            [0,2] -> [2,n-2], [2,n-2] -> [n-1,n-3], [n-1,n-3] -> [n-3,0], and [n-3,0] -> [0,2]
    so on and so on until we are halfway through the edge

    so in general the pattern is

    [0,i] -> [i,n-i], [i,n-i] -> [n-1,n-(i+1)], [n-1,n-(i+1)] -> [n-(i+1),0], and [n-(i+1),0] to [0,i]
    */

    public void rotate(int[][] a, int n) {
        int tmp;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                tmp = a[i][j];
                a[i][j] = a[j][n - i - 1];
                a[j][n - i - 1] = a[n - i - 1][n - j - 1];
                a[n - i - 1][n - j - 1] = a[n - j - 1][i];
                a[n - j - 1][i] = tmp;
            }
        }
    }
}

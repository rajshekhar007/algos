package com.raj.math;

/**
 * Created by rshekh1 on 6/8/16.
 */
public class GridPaths {

    /**
     * A robot is located at the top-left corner of an A x B grid (marked ‘Start’ in the diagram below).
     The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
     corner of the grid (marked ‘Finish’ in the diagram below).

     How many possible unique paths are there?

     Note: A and B will be such that the resulting answer fits in a 32 bit signed integer.

     Example :

     Input : A = 2, B = 2
     Output : 2

     2 possible routes : (0, 0) -> (0, 1) -> (1, 1)
     OR  : (0, 0) -> (1, 0) -> (1, 1)
     */

    public int uniquePaths(int a, int b) {
        return uniquePaths(0, 0, a-1, b-1, "00->");
    }

    private int uniquePaths(int i, int j, int a, int b, String path) {
        if (i == a && j == b) {
            System.out.println(path);
            return 1;
        }
        if (i > a || j > b) return 0;
        return uniquePaths(i, j+1, a, b, path+""+i+""+(j+1)+"->") + uniquePaths(i+1, j ,a ,b, path+""+(i+1)+""+j+"->");
    }

    public static void main(String[] args) {
        GridPaths g = new GridPaths();
        System.out.println(g.uniquePaths(100,1));
    }

}

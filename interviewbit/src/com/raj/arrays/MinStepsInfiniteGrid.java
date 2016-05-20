package com.raj.arrays;

import java.util.ArrayList;

/**
 * Created by rshekh1 on 4/23/16.
 */
public class MinStepsInfiniteGrid {

    /**
     * You are in an infinite 2D grid where you can move in any of the 8 directions :

     (x,y) to
     (x+1, y),
     (x - 1, y),
     (x, y+1),
     (x, y-1),
     (x-1, y-1),
     (x+1,y+1),
     (x-1,y+1),
     (x+1,y-1)
     You are given a sequence of points and the order in which you need to cover the points. Give the minimum number of steps in which you can achieve it. You start from the first point.

     Example :

     Input : [(0, 0), (1, 1), (1, 2)]
     Output : 2
     It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
     */

    public class Solution {
        // X and Y co-ordinates of the points in order.
        // Each point is represented by (X.get(i), Y.get(i))
        public int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
            int steps = 0;
            for (int i = 1; i < X.size(); i++) {

                int x1 = X.get(i-1), x2 = X.get(i);
                int y1 = Y.get(i-1), y2 = Y.get(i);
                steps += max( abs(x2-x1), abs(y2-y1) );
            }
            return steps;
        }

        public int max(int x, int y) {
            return (x>=y ? x : y);
        }

        public int abs(int x) {
            if (x >= 0) return x;
            return -1 * x;
        }
    }
}

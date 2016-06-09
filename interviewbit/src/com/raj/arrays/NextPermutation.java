package com.raj.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rshekh1 on 6/7/16.
 */
public class NextPermutation {

    /**
     * Implement the next permutation, which rearranges numbers into the numerically next greater permutation of numbers.
     If such arrangement is not possible, it must be rearranged as the lowest possible order ie, sorted in an ascending order.
     The replacement must be in-place, do not allocate extra memory.

     Examples:
     1,2,3 → 1,3,2

     3,2,1 → 1,2,3

     1,1,5 → 1,5,1

     20, 50, 113 → 20, 113, 50

     WRONG SOLUTION
     */

    public void nextPermutation(List<Integer> a) {

        if (a == null || a.isEmpty() || a.size() == 1) return;
        int lastIdx = a.size() - 1;

        if (a.size() == 2) {
            swap(a, lastIdx, lastIdx-1);
        } else if ( ((a.get(lastIdx-2) < a.get(lastIdx-1)) && (a.get(lastIdx-1) < a.get(lastIdx)))
                || ((a.get(lastIdx-2) > a.get(lastIdx-1)) && (a.get(lastIdx-1) < a.get(lastIdx))) ) {
            swap(a, lastIdx-1, lastIdx);
        } else if ( ((a.get(lastIdx-2) < a.get(lastIdx-1)) && (a.get(lastIdx-1) > a.get(lastIdx)))
                || ((a.get(lastIdx-2) > a.get(lastIdx-1)) && (a.get(lastIdx-1) < a.get(lastIdx))) ) {
            if (a.get(lastIdx-2) < a.get(lastIdx)) {
                swap(a, lastIdx-2, lastIdx); swap(a, lastIdx-1, lastIdx);
            } else {
                swap(a, lastIdx-2, lastIdx); swap(a, lastIdx-2, lastIdx-1);
            }
        } else if ((a.get(lastIdx-2) > a.get(lastIdx-1)) && (a.get(lastIdx-1) > a.get(lastIdx))) {
            swap(a, lastIdx-2, lastIdx);
        }

        for (int i : a) System.out.print(i+" ");
        System.out.println();

    }

    private void swap(List<Integer> a, int i, int j) {
        int tmp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, tmp);
    }

    public static void main(String[] args) {
        NextPermutation n = new NextPermutation();
        n.nextPermutation(Arrays.asList(1,2,3));
        n.nextPermutation(Arrays.asList(1,3,2));
        n.nextPermutation(Arrays.asList(2,1,3));
        n.nextPermutation(Arrays.asList(2,3,1));
        n.nextPermutation(Arrays.asList(3,1,2));
        n.nextPermutation(Arrays.asList(3,2,1));
        n.nextPermutation(Arrays.asList(873, 855, 693, 461, 634, 102, 207, 58, 60, 972, 50, 20, 406, 90, 494, 684, 572, 841, 253, 734, 937, 803, 482, 147, 987, 914, 997, 807, 866, 262, 526, 229, 891, 985, 905, 824, 507 ));
//873 855 693 461 634 102 207 58 60 972 50 20 406 90 494 684 572 841 253 734 937 803 482 147 987 914 997 807 866 262 526 229 891 985 507 824 905
//873 855 693 461 634 102 207 58 60 972 50 20 406 90 494 684 572 841 253 734 937 803 482 147 987 914 997 807 866 262 526 229 905 507 824 891 985


    }

}

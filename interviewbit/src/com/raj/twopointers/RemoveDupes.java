package com.raj.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class RemoveDupes {

    public int removeDuplicates(ArrayList<Integer> a) {
        if (a == null || a.isEmpty() || a.size() == 1) return a.size();
        int i=0, j=1, originalSize=a.size(), newSize=originalSize;
        while (i < a.size() && j < a.size()) {
            if (a.get(i).equals(a.get(j))) {
                j++; newSize--;
            }
            else if (!a.get(i).equals(a.get(j))) {
                i++;
                if (i<j) a.set(i,a.get(j));
                j++;
            }
        }
        while (originalSize > newSize) a.remove(--originalSize);
        return newSize;
    }

    public static void main(String[] args) {
        RemoveDupes r = new RemoveDupes();
        //System.out.println(r.removeDuplicates(new ArrayList<>(Arrays.asList(1,1,3,3,3,4,4))));
        System.out.println(r.removeDuplicates(new ArrayList<>(Arrays.asList(5000,5000,5000))));
    }
}

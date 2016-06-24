package com.raj.hashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class LongestSubstringNoRepeat {

    public int lengthOfLongestSubstring(String a) {
        int max = 0;
        for (int i=0; i<a.length(); i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j=i; j<a.length(); j++) {
                char ch = a.charAt(j);
                if (set.contains(new Character(ch))) {
                    break;
                }
                set.add(new Character(ch));
            }
            max = (set.size() > max) ? set.size() : max;   // update max pointer
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringNoRepeat l = new LongestSubstringNoRepeat();
        System.out.println(l.lengthOfLongestSubstring("dadbc"));
    }
}

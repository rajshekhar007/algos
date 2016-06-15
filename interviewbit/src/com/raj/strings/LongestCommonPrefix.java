package com.raj.strings;

import java.util.*;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix1(List<String> a) {

        // Find minimum length string
        int minLength = 0, minPos = 0;
        for (int i=0; i<a.size(); i++) {
            String s = a.get(i);
            if (s.length() < minLength) {
                minLength = s.length(); minPos = i;
            }
        }

        // Create lookup dictionary for each letter
        Map<Character, List<String>> map = new HashMap<>();
        String minStr = a.get(minPos);
        for (int i=0; i<minStr.length(); i++) {
            List<String> strsAtChar = map.get(minStr.charAt(i)) == null ? new ArrayList<>() : map.get(minStr.charAt(i));
            strsAtChar.add(minStr.substring(i));
            map.put(minStr.charAt(i), strsAtChar);
        }

        // Check with next string for min matched prefix, also finding longest prefix
        String longestPrefix = "";
        for (int i=0; i<a.size(); i++) {
            if (i == minPos) continue;
            String s = a.get(i);
            for (int j = 0; j < s.length(); j++) {
                List<String> strings = map.get(s.charAt(j));
                if (strings == null || strings.isEmpty() || longestPrefix.isEmpty()) continue;
                if (!longestPrefix.isEmpty()) {
                    String prefix = getLongestPrefix(longestPrefix, s);
                    if (prefix.length() > longestPrefix.length()) longestPrefix = prefix;
                }
                for (String s1 : strings) {
                    String prefix = getLongestPrefix(s1, s);
                    if (prefix.length() > longestPrefix.length()) longestPrefix = prefix;
                }
            }
        }
        return longestPrefix;
    }

    public String longestCommonPrefix(List<String> a) {
        if (a== null || a.size() == 0) return "";
        if (a.size() == 1) return a.get(0);
        String longestPrefix = getLongestPrefix(a.get(0), a.get(1));
        for (int i = 1; i < a.size(); i++) {
            longestPrefix = getLongestPrefix(longestPrefix, a.get(i));
        }
        return longestPrefix;
    }


    private String getLongestPrefix(String s1, String s2) {
        String longestPrefix = "";
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                int k = 0;
                String prefix = "";
                while (i+k < s1.length() && j+k < s2.length() && s1.charAt(i+k) == s2.charAt(j+k)) {
                    prefix += s1.charAt(i+k);
                    k++;
                }
                if (prefix.length() > longestPrefix.length()) longestPrefix = prefix;
            }
        }
        return longestPrefix;
    }

    public static void main(String[] args) {
        LongestCommonPrefix l = new LongestCommonPrefix();
        //System.out.println(l.getLongestPrefix("abncdhfcdefgh","xyzczcdbcdejk"));
        System.out.println(l.longestCommonPrefix(Arrays.asList("abcndhfcdefgh","xyzczcdbcdejk","fgcdjh")));
    }
}

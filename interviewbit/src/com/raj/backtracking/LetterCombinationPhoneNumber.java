package com.raj.backtracking;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class LetterCombinationPhoneNumber {

    private HashMap<Integer, String> map = new HashMap<>();
    private List<String> result = Lists.newArrayList();

    public LetterCombinationPhoneNumber() {
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        map.put(0, "");
    }

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) {
            return result;
        }
        permuteLetterCombinations("", digits);
        return result;
    }

    public void permuteLetterCombinations(String soFar, String rest) {
        if(rest.isEmpty()) {
            result.add(soFar);
            return;
        }

        Integer curr = Integer.valueOf(rest.substring(0,1));
        String letters = map.get(curr);
        for(int i=0; i<letters.length(); i++) {
            permuteLetterCombinations(soFar + letters.charAt(i), rest.substring(1));
        }
    }

    public static void main(String[] args) {
        LetterCombinationPhoneNumber l = new LetterCombinationPhoneNumber();
        System.out.println(l.letterCombinations("234"));
    }
}

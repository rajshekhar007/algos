package com.raj.backtracking;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class LetterCombinationPhoneNumber {

    /**
     *              " ", 234
     *           /     |    \
     *         a,34   b,34   c,34    (decompose 2 and permute on each letter of 2)
     *         / | \
     *      ad,4 ae,4 af,4           (decompose 3 and permute on each letter of 3)
     *       /
     *  (adg,adh,adi)
     *
     *
     */
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
        if(rest.isEmpty()) {       // Base case - when no digits are left to permute
            result.add(soFar);
            return;
        }

        Integer curr = Integer.valueOf(rest.substring(0,1));   // take the first digit
        String letters = map.get(curr);      // decompose digit into letters
        for(int i=0; i<letters.length(); i++) {        // iterate on each letter
            permuteLetterCombinations(soFar + letters.charAt(i), rest.substring(1));  // keep appending letter to soFar and permute on leftover digits
        }
    }

    public static void main(String[] args) {
        LetterCombinationPhoneNumber l = new LetterCombinationPhoneNumber();
        System.out.println(l.letterCombinations("234"));
    }
}

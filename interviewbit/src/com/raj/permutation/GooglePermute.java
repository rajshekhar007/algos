package com.raj.permutation;

/**
 * Created by rshekh1 on 6/14/16.
 */
public class GooglePermute {

    /**
     * if input string is-
     * 1> "a", then .next() iteratively will return a, aa, aaa, aaaa, aaaaa ....
     * 2> "abc" then a, b, c, aa, ab, ac, ba, bb, bc, ca, cb, cc, aaa, aab, aac, aba, abb, abc, aca, acb ....
     */

    private String s="", P="";

    public GooglePermute(String s) {
        this.s = s;
    }

    public String next() {
        P = getNextP();
        String nextS = "";
        for (char c : P.toCharArray()) {
            int d = Integer.valueOf(c + "");
            nextS = nextS + s.charAt(d);
        }
        return nextS;
    }

    private String getNextP() {
        if (P.isEmpty()) return "0";
        int carry = 1;
        String nextP = "";
        for (int i=P.length()-1; i>=0; i--) {
            char ch = P.charAt(i);
            int d = Integer.parseInt(ch+"") + carry;
            carry = 0;
            if (d >= s.length()) {
                carry = 1;
                d = 0;
            }
            nextP = d + "" + nextP;
        }

        if (carry == 1) nextP = "0" + nextP;

        return nextP;
    }

    public static void main(String[] args) {
        GooglePermute g = new GooglePermute("abc");
        for (int i = 0; i < 50; i++) {
            System.out.println(g.next());
        }
    }

}

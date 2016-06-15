package com.raj.strings;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class Integer2Roman {

    enum RomanLetters {
        I(1),V(5),X(10),L(50),C(100),D(500),M(1000);
        int value;
        RomanLetters(int i) {
            value = i;
        }
        public static List<RomanLetters> getList() {
            return Arrays.asList(M, D, C, L, X, V, I);
        }
    }

    public String intToRoman(int n) {
        String nStr = n+"";
        int p = 1;
        String R = "";
        for (int i = 0, j = nStr.length()-1; i < nStr.length(); i++,j--) {

            double digit = Integer.valueOf(nStr.charAt(i)+"") * Math.pow(10, j);
            double next2Digits = 0;
            if (i+1 < nStr.length()) next2Digits = digit + nStr.charAt(i+1) * Math.pow(10, j+1);
            else next2Digits = digit;

            while (digit > 0) {
                for (int k = 0; k < RomanLetters.getList().size(); k++) {

                    while ((digit/RomanLetters.getList().get(k).value) >=1) {
                        R += RomanLetters.getList().get(k).name();
                        digit -= RomanLetters.getList().get(k).value;
                    }

                    while ((digit/RomanLetters.getList().get(k).value) >=0.8
                            && k+1<RomanLetters.getList().size()
                            && isAllowedBefore(RomanLetters.getList().get(k).name(), RomanLetters.getList().get(k+1).name())
                            && (RomanLetters.getList().get(k).value-digit)>=RomanLetters.getList().get(k+1).value) {
                        R += RomanLetters.getList().get(k+1).name() + RomanLetters.getList().get(k).name();
                        digit -= (RomanLetters.getList().get(k).value - RomanLetters.getList().get(k+1).value);
                    }

                    while ((digit/RomanLetters.getList().get(k).value) >=0.9
                            && k+2<RomanLetters.getList().size()
                            && isAllowedBefore(RomanLetters.getList().get(k).name(), RomanLetters.getList().get(k+2).name())
                            && (RomanLetters.getList().get(k).value-digit)>=RomanLetters.getList().get(k+2).value) {
                        R += RomanLetters.getList().get(k+2).name() + RomanLetters.getList().get(k).name();
                        digit -= (RomanLetters.getList().get(k).value - RomanLetters.getList().get(k+2).value);
                    }
                }
            }
        }
        return R;
    }

    public boolean isAllowedBefore(String r1, String r2) {
        if (r1.equals("M") && r2.equals("C")) return true;
        if (r1.equals("D") && r2.equals("C")) return true;
        if (r1.equals("C") && r2.equals("X")) return true;
        if (r1.equals("L") && r2.equals("X")) return true;
        if (r1.equals("X") && r2.equals("I")) return true;
        if (r1.equals("V") && r2.equals("I")) return true;
        return false;
    }

    public static void main(String[] args) {
        Integer2Roman i = new Integer2Roman();
        /*System.out.println(i.intToRoman(3));
        System.out.println(i.intToRoman(5));
        System.out.println(i.intToRoman(7));
        System.out.println(i.intToRoman(9));
        System.out.println(i.intToRoman(40));
        System.out.println(i.intToRoman(349));
        System.out.println(i.intToRoman(662));*/
        System.out.println(i.intToRoman(400));
    }
}

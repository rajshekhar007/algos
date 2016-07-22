package com.raj.companies;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class Netflix {

    /**
     encode(str) -> str
     "az" -> "126"

     encoding - FIXED
     a -> 1
     b -> 2
     ...
     z -> 26


     input "126"
     az
     abf
     lf
     output :3 total ways


     126 -> length of string 1 to input.length()
     str += input.substring[i,i+length] if i+length < input.length else w/e is left     => (1,2,6), (12,6), (1,26), (126-not valid)




     Map to store numbers to chars

            soFar, rest
            "",    126
         /                  \
      (1),26               (12),6
       /       \              \
     (1,2),6   (1,26),""     (12,6),""
       /
     (1,2,6),""
     */

    private static int count = 0;

    public static void getString(String soFar, String rest) {
        System.out.println(soFar + " : " + rest);
        if (rest.isEmpty()) {
            if (isValidString(soFar)) count++;
            return;
        }

        getString(soFar+","+rest.charAt(0), rest.substring(1));

        if (rest.length() >= 2) getString(soFar+","+rest.charAt(0)+rest.charAt(1), rest.substring(2));

    }

    private static boolean isValidString(String str) {
        String[] strArr = str.split(",");
        for (String s : strArr) {
            if (s.isEmpty()) continue;
            if (Integer.parseInt(s) <= 0 || Integer.parseInt(s) > 26) return false;
        }
        return true;
    }

    public static int getCount(String input) {
        getString("", input);
        return count;
    }

    //INPUT: "10"  => (1,0),"" , (10),""

    public static void main(String[] args) {
        System.out.println(getCount("10"));
    }

}

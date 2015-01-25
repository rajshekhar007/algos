package com.raj.string;

/**
 * @author: sraj1
 * Date:    10/9/12
 */
public class TelephoneKeyCombinations {

    /*
    Programming Interviews Exposed problem
    Print all string combinations for 644-2423 for telephone keys

    Algo:
    - init array with [dig][char equivalent] and store all char equivalents of tel key
    - init ptr to keep track of which char is currently read from above array
    - loop forever till end check
    - out[i] = arr[ i ][ ptr[i] ]  --> init all out chars
    - print out and check if this is the end, which will be when all ptrs are referring to last element in arr
    - otherwise increment last char in arr by 1 and adjust other chars accordingly
    */
    static String inp = "2437";
    // which maps to: ABC  GHI  DEF  PQR
    static String inpAllChars = "ABCGHIDEFPQR";
    static char[] out = new char[4];
    static char[][] arr = new char[4][3];
    static int[] ptr = new int[4];

    static void init() {
        int cnt = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++)
                arr[i][j] = inpAllChars.charAt(cnt++);
    }

    static boolean checkEnd() {
        for (int i = 0; i < 4; i++)
            if (ptr[i] != 2)
                return false;
        return true;
    }

    static void printCombinations() {
        init();
        while (true) {
            for (int i = 0; i < 4; i++) {
                out[i] = arr[i][ptr[i]];
            }
            System.out.println(out);
            if (checkEnd())
                break;
            rotate();
        }
    }

    static void rotate() {
        ptr[3]++;
        for (int i = 3; i >= 0; i--) {
            if (ptr[i] > 2) {
                ptr[i] = 0;
                ptr[i - 1]++;
            }
        }
    }

    public static void main(String[] args) {
        printCombinations();
    }

}

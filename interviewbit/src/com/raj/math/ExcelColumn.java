package com.raj.math;

/**
 * Created by rshekh1 on 6/8/16.
 */
public class ExcelColumn {

    public int titleToNumber(String a) {
        if (a == null || a.trim().length() == 0) return 0;
        String s = a.trim().toUpperCase();
        int num = 0;
        for (int i=s.length()-1,j=0; i>=0; i--,j++) {
            num += getValue(s.charAt(i)) * Math.pow(26,j);
        }
        return num;
    }

    private int getValue(char ch) {
        return (int) ch-'A'+1;
    }

    public static void main(String[] args) {
        ExcelColumn e = new ExcelColumn();
        System.out.println(e.titleToNumber("BZ"));
    }

}
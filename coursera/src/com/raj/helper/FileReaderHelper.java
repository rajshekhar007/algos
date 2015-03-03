/*
 * Copyright (c) Shekhar Raj
 */

package com.raj.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: sraj1
 * Date:    10/31/12
 */
public class FileReaderHelper {

    public static List read(String fileName, boolean convertToInteger) {

        File file = new File(fileName);
        List<String> stringList = new ArrayList<String>();
        List<Integer> integerList = new ArrayList<Integer>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line.trim();
                if (convertToInteger) {
                    integerList.add(Integer.parseInt(line));
                } else {
                    stringList.add(line);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertToInteger ? integerList : stringList;
    }

    public static List<Long> readAsLong(String fileName) {

        File file = new File(fileName);
        List<Long> longList = new ArrayList<Long>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line.trim();
                longList.add(Long.parseLong(line));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return longList;
    }

    public static List readAsIntegerListOfArrays(String fileName, int arraySize) {

        File file = new File(fileName);
        List integerList = new ArrayList();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line.trim();
                String[] strArr = line.split(" ");
                int[] intArr = new int[arraySize];
                for (int i = 0; i < arraySize; i++) {
                    intArr[i] = Integer.parseInt(strArr[i]);
                }
                integerList.add(intArr);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integerList;
    }


}

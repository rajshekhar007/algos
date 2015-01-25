package com.raj.graph.helper;

import java.io.*;
import java.util.HashSet;

/**
 * @author: sraj1
 * Date:    10/31/12
 */
public class FileReaderHelper {

    private HashSet<String> dictionaryWords = new HashSet<String>();

    public static void serialize(Object object) {
        // Object serialization
        try {
            FileOutputStream fos = new FileOutputStream("E:\\workspace-intellij\\algos\\crackingcodinginterview\\resource\\serial.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Object deserialize() {
        // Object deserialization
        Object obj = null;
        try {
            FileInputStream fis = new FileInputStream("E:\\workspace-intellij\\algos\\crackingcodinginterview\\resource\\serial.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj = ois.readObject();
            ois.close();
            System.out.println("Deserialized object: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return obj;
    }

    public HashSet<String> getDictionaryWords() {
        return dictionaryWords;
    }

    public void setDictionaryWords(HashSet<String> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }

    public void readWords() {

        File file = new File("E:\\workspace-intellij\\algos\\crackingcodinginterview\\resource\\full_list.txt"); //linux.words //Unabr.dict
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line.trim();
                if (!dictionaryWords.contains(line)) dictionaryWords.add(line.toUpperCase());
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(dictionaryWords.contains("DAM"));
    }

}

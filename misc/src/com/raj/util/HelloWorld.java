package com.raj.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HelloWorld {
    public static void main(String[] args) {
        /*Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.info("Hello World");*/
        //System.out.println(rand7());
        float cnt = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        while (cnt++ < 10000000) {
            int n = rand5();
            if (!map.containsKey(n))
                map.put(n, 0);
            else map.put(n, map.get(n) + 1);
            //System.out.print(n);
            //System.out.println();
        }

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        map.clear();
        cnt = 0;

        while (cnt++ < 10000000) {
            int n = myrand7();
            if (!map.containsKey(n))
                map.put(n, 0);
            else map.put(n, map.get(n) + 1);
            //System.out.print(n);
            //System.out.println();
        }

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static int rand5() {
        Random random = new Random();
        int n = random.nextInt(5) + 1;
        //System.out.println(n);
        return n;
    }

    public static int rand7() {
        while (true) {
            int num = 5 * (rand5() - 1) + rand5();
            if (num < 22) return ((num % 7) + 1);
        }
    }

    public static int myrand7() {
        int n = rand5();
        Float f = n * 0.4f; //rand5() * 0.4f;
        //System.out.print(n + " : " + f + " = ");
        return (n + Math.round(f));
    }

}
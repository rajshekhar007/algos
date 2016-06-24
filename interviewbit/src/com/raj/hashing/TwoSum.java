package com.raj.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class TwoSum {

    public ArrayList<Integer> twoSum(final List<Integer> a, int b) {
        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<a.size(); i++) {
            if (map.containsKey(a.get(i))) {
                res.add(map.get(a.get(i)));
                res.add(i);
                return res;
            }
            map.put(b-a.get(i), i);
        }
        return res;
    }
}

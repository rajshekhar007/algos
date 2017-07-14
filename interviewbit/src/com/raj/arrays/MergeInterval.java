package com.raj.arrays;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by rshekh1 on 7/13/17.
 */
public class MergeInterval {

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.isEmpty() || intervals.size() == 1) return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        ArrayList<Interval> out = new ArrayList<>();
        Interval mergedI = intervals.get(0);
        for(int i=1; i<intervals.size(); i++) {
            Interval currentI = intervals.get(i);
            if (mergedI.end < currentI.start) {
                out.add(mergedI);
                mergedI = currentI;
            } else {
                mergedI.start = Math.min(mergedI.start, currentI.start);
                mergedI.end = Math.max(mergedI.end, currentI.end);
            }
        }
        out.add(mergedI);
        return out;
    }



    public static void main(String[] args) {
        MergeInterval m = new MergeInterval();
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(3,8));
        intervals.add(new Interval(2,9));
        intervals.add(new Interval(1,10));
        intervals.add(new Interval(4,7));
        intervals.add(new Interval(5,6));

        System.out.println(m.merge(intervals));

        intervals.clear();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,5));
        intervals.add(new Interval(6,7));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(12,16));
        intervals.add(new Interval(18,20));
        intervals.add(new Interval(4,9));
        System.out.println(m.merge(intervals));

    }

}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("start", start)
                .add("end", end)
                .toString();
    }
}

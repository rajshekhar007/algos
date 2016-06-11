package com.raj.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rshekh1 on 4/24/16.
 */
public class MergeIntervals {

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

     You may assume that the intervals were initially sorted according to their start times.

     Example 1:

     Given intervals [1,3],[6,9] insert and merge [2,5] would result in [1,5],[6,9].

     Example 2:

     Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] would result in [1,2],[3,10],[12,16].

     This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

     Make sure the returned intervals are also sorted.

     * @param intervals
     * @param newInterval
     * @return
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

        ArrayList<Interval> res = new ArrayList<>();
        boolean doneMerge = false;

        // Sort new interval
        sort(newInterval);

        if (intervals == null || intervals.isEmpty()) {
            res.add(newInterval);
            return res;
        }

        if (newInterval == null) {
            return intervals;
        }

        // Case - New interval lies before given intervals
        if (isNewIntervalBefore(newInterval, intervals.get(0))) {
            res.add(newInterval);
        }

        for (int i=0; i<intervals.size(); i++) {

            // Once new interval is merged, no need to process anything - just add remaining input intervals
            if (doneMerge) {
                res.add(intervals.get(i));
                continue;
            }

            // Case - New interval lies in b/w 2 intervals (no overlap)
            if (isNewIntervalInBetween(intervals, i, i+1, newInterval)) {
                res.add(intervals.get(i));
                res.add(newInterval);
                res.add(intervals.get(++i));
                doneMerge = true;
                continue;
            }

            // Try merging new interval with ith interval
            Interval mergedInterval = merge(intervals.get(i), newInterval);

            if (mergedInterval == null) {  // If it can't be merged, just copy the ith interval into results
                res.add(intervals.get(i));
            } else {
                // Case - If it merges with ith, keep progressing with next intervals until merge completes
                Interval newMergedInterval = mergedInterval;
                while (newMergedInterval != null && ++i < intervals.size()) {
                    newMergedInterval = merge(mergedInterval, intervals.get(i));
                    mergedInterval = newMergedInterval != null ? newMergedInterval : mergedInterval;
                }
                // Save the last non null merged interval
                res.add(mergedInterval);
                // Add the i+nth interval as it is leftover from a try merge
                if (i < intervals.size()) {
                    res.add(intervals.get(i));  // Always check ArrayIndexOutofBounds if i is manipulated
                }
                doneMerge = true;   // New interval is assimilated
            }
        }

        // Case - New interval is after all input intervals
        if (isNewIntervalAfter(newInterval, intervals.get(intervals.size()-1))) {
            res.add(newInterval);
        }

        return res;
    }

    private Interval merge(Interval i1, Interval i2) {
        // If i2 is smaller than i1, swap them
        if (i1.start > i2.start) return merge(i2, i1);

        // Negate conditions is easier - can't merge when...
        if (i1.end < i2.start) return null;

        // i2 is a subset interval of i1 (full overlap)
        if (i2.start >= i1.start && i2.end <= i1.end) return i1;

        // The remaining condition is when i2 merges with i1 resulting in a larger interval (partial overlap)
        return new Interval(i1.start, i2.end);
    }

    private void sort(Interval i) {
        if (i.start > i.end) {
            int tmp = i.start;
            i.start = i.end;
            i.end = tmp;
        }
    }

    private boolean isNewIntervalBefore(Interval i, Interval j) {
        if (i.end < j.start) return true;
        return false;
    }

    private boolean isNewIntervalAfter(Interval i, Interval j) {
        if (j.end < i.start) return true;
        return false;
    }

    private boolean isNewIntervalInBetween(List<Interval> intervals, int i, int j, Interval newInterval) {
        if (i >= intervals.size() || j >= intervals.size()) {
            return false;
        }
        Interval a = intervals.get(i);
        Interval b = intervals.get(j);
        return (a.end < newInterval.start && newInterval.end < b.start);
    }

    public static void main(String[] args) {
        MergeIntervals m = new MergeIntervals();
        ArrayList<Interval> intervals = new ArrayList<>();

        intervals.add(new Interval(1,3));
        intervals.add(new Interval(6,9));
        System.out.println(m.insert(intervals, new Interval(2,5)));

        intervals.clear();
        System.out.println(m.insert(intervals, new Interval(1,1)));

        intervals.clear();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,5));
        intervals.add(new Interval(6,7));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(12,16));
        intervals.add(new Interval(18,20));
        intervals.add(new Interval(22,26));
        System.out.println(m.insert(intervals, new Interval(4,9)));

        intervals.clear();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(8,10));
        System.out.println(m.insert(intervals, new Interval(3,6)));

        intervals.clear();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(6,9));
        System.out.println(m.insert(intervals, new Interval(-1,0)));

        intervals.clear();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(6,9));
        System.out.println(m.insert(intervals, new Interval(-1,1)));

        intervals.clear();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(6,9));
        System.out.println(m.insert(intervals, new Interval(9,20)));

        intervals.clear();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,6));
        System.out.println(m.insert(intervals, new Interval(10,8)));

        intervals.clear();
        intervals.add(new Interval(29823256,32060921));
        intervals.add(new Interval(33950165,64859907));
        intervals.add(new Interval(65277782,65296274));
        intervals.add(new Interval(67497842,68386607));
        intervals.add(new Interval(70414085,73339545));
        intervals.add(new Interval(73896106,75605861));
        intervals.add(new Interval(79672668,84539434));
        intervals.add(new Interval(84821550,86558001));
        intervals.add(new Interval(91116470,92198054));
        intervals.add(new Interval(96147808,98979097));
        System.out.println(m.insert(intervals, new Interval(36210193, 61984219)));

    }

    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    static class Interval {
        int start, end;
        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "("+start+","+end+") ";
        }
    }

    public ArrayList<Interval> optimizedInsert(ArrayList<Interval> intervals, Interval newInterval) {

        // Add new interval to intervals and sort them
        intervals.add(newInterval);

        ArrayList<Interval> result = new ArrayList<Interval>();

        if(intervals==null||intervals.size()==0)
            return result;

        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                if(i1.start!=i2.start)
                    return i1.start-i2.start;
                else
                    return i1.end-i2.end;
            }
        });

        Interval pre = intervals.get(0);
        for(int i=0; i<intervals.size(); i++){
            Interval curr = intervals.get(i);
            if(curr.start>pre.end){
                result.add(pre);
                pre = curr;
            }else{
                Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
                pre = merged;
            }
        }
        result.add(pre);
        return result;
    }

}

/*
 * Copyright (c) Shekhar Raj
 */

package com.raj.coursera.algos.part1.graph;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.raj.helper.FileReaderHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Sweta on 2/14/2015.
 */
public class SCC {

    boolean isGraphReversed = false;
    int finishingTimeCounter = 0;  // global counter for topological sort of graph by DFS finish times - 1st Pass
    int start = 0;          // global start node, will help find the leader for 2nd pass
    Map<Integer, List<Integer>> sccMapByLeader = Maps.newHashMap();  // stores the output
    private List<Node> graph;

    public static void printGraph(List<Node> graph) {
        System.out.println("V " + " visit" + " finish" + " leader" + " outE" + " inE");
        for (Node n : graph) {
            System.out.println(n);
        }
        System.out.println("");
    }

    public static void runProgram() throws InterruptedException {

        List integerListOfArrays = FileReaderHelper.readAsIntegerListOfArrays("E:\\workspace-intellij\\algos\\coursera\\resource\\SCC.txt", 2);

        printSystemStats("After reading file");

        int arraySize = integerListOfArrays.size();
        int[][] inputArray = new int[arraySize][2];

        int i = 0;
        for (Object intArr : integerListOfArrays) {
            inputArray[i] = (int[]) intArr;
            i++;
        }

        SCC scc = new SCC();
        scc.createGraph(inputArray);
    }

    public static void printSystemStats(String desc) {

        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int mb = 1024 * 1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] at " + desc + " #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);

        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        printSystemStats("Start program");
        runProgram();
        Thread.sleep(2000);
    }

    public void clearVisitedFlags() {
        for (Node n : graph) {
            n.isVisited = false;
        }
    }

    public void computeSCC() {

        isGraphReversed = true; // reverse graph

        // 1st Pass : iterate on non visited nodes of the reversed graph to compute finishing times
        for (int i = graph.size() - 1; i > 0; i--) {
            if (graph.get(i).isVisited) {
                continue;
            }
            start = i;
            DFS(i);
        }

        printSystemStats("After PASS 1");

        // reset graph to it's original state
        isGraphReversed = false;
        clearVisitedFlags();

        // 2nd Pass : iterate based on topological finish times sort on the original graph to find SCC
        for (int i = graph.size() - 1; i > 0; i--) {
            Integer nodeIdxToRefer = graph.get(i).finishTimeNodeIdxReference;  // this is our node to do DFS on
            if (graph.get(nodeIdxToRefer).isVisited) {
                continue;
            }
            start = nodeIdxToRefer;
            DFS(nodeIdxToRefer);
        }

        printSystemStats("After PASS 2");

    }

    public void DFS(int i) {

        Node node = graph.get(i);

        // mark i as explored
        node.isVisited = true;

        // set leader for this node
        node.leader = start;

        List<Integer> edges = null;
        if (isGraphReversed) {
            edges = node.incomingEdges;  // neighbors for a reversed graph
        } else {
            edges = node.outgoingEdges;
        }

        for (Integer j : edges) {

            if (!graph.get(j).isVisited) {

                if (!isGraphReversed) {
                    if (!sccMapByLeader.containsKey(node.leader)) {
                        sccMapByLeader.put(node.leader, Lists.<Integer>newArrayList());
                    }
                    sccMapByLeader.get(node.leader).add(j);
                }

                DFS(j);
            }
        }

        // compute finishing times on the reversed graph
        if (isGraphReversed) {
            finishingTimeCounter++;
            node.finishingTimeOrder = finishingTimeCounter;  // finish time for this node
            graph.get(finishingTimeCounter).finishTimeNodeIdxReference = node.vertex;  // holds reference to the node which we'll actually process while iterating in the 2nd pass
        }

    }

    /**
     * Creates an adjacency list graph by vertices and their outgoing / incoming edges from the inputList
     *
     * @param A
     */
    public void createGraph(int[][] A) throws InterruptedException {

        printSystemStats("Before creating graph");

        Thread.sleep(2000);

        int listSize = A[A.length - 1][0];
        this.graph = Lists.newArrayListWithCapacity(listSize);

        System.out.println("List Size: " + listSize);

        for (int i = 0; i <= listSize; i++) {
            Node n = new Node(i);
            this.graph.add(n);
        }

        for (int i = 0; i < A.length; i++) {
            int vertex = A[i][0];
            int edge = A[i][1];
            this.graph.get(vertex).outgoingEdges.add(edge);
            this.graph.get(edge).incomingEdges.add(vertex);
        }

        printSystemStats("After creating graph");

        this.computeSCC();

        //printGraph(this.graph);
        //System.out.println("SCC by leader : " + sccMapByLeader);

        List<Integer> sccList = Lists.newArrayList();
        for (Map.Entry<Integer, List<Integer>> me : sccMapByLeader.entrySet()) {
            sccList.add(me.getValue().size() + 1);
        }

        Collections.sort(sccList);
        Collections.reverse(sccList);

        printSystemStats("After Sorting SCC list");

        System.out.println("Sorted SCC sizes : " + sccList);

    }

    private class Node {
        Integer vertex;
        Boolean isVisited;
        Integer finishingTimeOrder;
        Integer leader;
        Integer finishTimeNodeIdxReference;  // denotes which node to refer to when iterating in 2nd pass, hence no sorting based on finish times is required
        List<Integer> outgoingEdges;  // the list of adjacent vertices representing outgoing edges
        List<Integer> incomingEdges;  // the list of adjacent vertices representing incoming edges, hence reversing the graph isn't required

        Node(int vertex) {
            this.isVisited = false;
            this.finishingTimeOrder = 0;
            this.vertex = vertex;
            this.outgoingEdges = Lists.newArrayList();
            this.incomingEdges = Lists.newArrayList();
        }

        @Override
        public String toString() {
            String str = vertex + "  " + isVisited + "  " + finishingTimeOrder + "      " + leader + "      ";
            for (Integer i : outgoingEdges) {
                str += i + ",";
            }
            str += "   ";
            for (Integer i : incomingEdges) {
                str += i + ",";
            }
            return str;
        }
    }

}

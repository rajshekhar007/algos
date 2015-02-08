/*
 * Copyright (c) Shekhar Raj
 */

package com.raj.coursera.algos.part1.graph;

import com.google.common.collect.Maps;
import com.raj.helper.FileReaderHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Min cut graph problem solved by contraction algorithm
 * <p/>
 * Created by Sweta on 2/7/2015.
 */
public class MinCut {

    /**
     * Accepts an adjacency list denoted by map of String vertices & their list adjacent nodes
     *
     * @param graph
     * @return
     */
    public static int minCutGraphContraction(Map<String, List<String>> graph) {

        if (graph == null || graph.isEmpty() || graph.size() < 2) {
            return 0;
        }

        while (graph.size() > 2) {

            String randomVertex = getRandomSeedVertex(graph);

            List<String> adjacentEdges = graph.get(randomVertex);

            if (adjacentEdges != null && adjacentEdges.size() >= 1) {
                // Get vertex on the edge to contract
                String v1 = randomVertex, v2 = adjacentEdges.get(0);

                // Lets call the new merged vertex as ...
                String mergedVertex = v1 + v2 + "C";  // C denotes, it's contracted just to avoid collisions

                contractGraph(graph, v1, v2, mergedVertex);
            }

        }

        // If the size of the graph is 2, return the adj list size as min cut
        return graph.entrySet().iterator().next().getValue().size();

    }

    public static String getRandomSeedVertex(Map<String, List<String>> graph) {
        int randomSeed = (int) (Math.random() * graph.size());
        String key = null;
        for (Map.Entry<String, List<String>> me : graph.entrySet()) {
            if (randomSeed == 0) {
                key = me.getKey();
                break;
            } else {
                randomSeed--;
            }
        }
        return key;
    }

    public static void contractGraph(Map<String, List<String>> graph, String v1, String v2, String mergedVertex) {

        // Add mergedVertex entry to graph
        List<String> v1AdjList = graph.get(v1);
        List<String> v2AdjList = graph.get(v2);

        List<String> mergedVertexAdjList = new ArrayList<String>();

        // Create adj list for the merged vertex. Don't add the contracted vertices
        for (String adjV : v1AdjList) {
            if (!adjV.equals(v1) && !adjV.equals(v2)) {
                mergedVertexAdjList.add(adjV);
            }
        }
        for (String adjV : v2AdjList) {
            if (!adjV.equals(v1) && !adjV.equals(v2)) {
                mergedVertexAdjList.add(adjV);
            }
        }

        // Add it to graph & update visitedKeyVertices
        graph.put(mergedVertex, mergedVertexAdjList);

        // Remove v1 & v2 from adjacency list graph as they are merged now
        graph.remove(v1);
        graph.remove(v2);

        // Replace Graph With new Merged Vertices References - they will only be in the v1 + v2 lists
        for (String mergedAdjV : mergedVertexAdjList) {
            List<String> adjList = graph.get(mergedAdjV);
            for (int i = 0; i < adjList.size(); i++) {
                if (adjList.get(i).equals(v1) || adjList.get(i).equals(v2)) {
                    adjList.set(i, mergedVertex);
                }
            }
        }

    }

    public static int runProgram() {

        List<String> stringList = FileReaderHelper.read("E:\\workspace-intellij\\algos\\coursera\\resource\\kargerMinCut.txt", false);
        Map<String, List<String>> graph = Maps.newHashMap();

        for (String str : stringList) {
            List<String> items = Arrays.asList(str.split("\\t"));
            graph.put(items.get(0), items.subList(1, items.size()));
        }

        int result = minCutGraphContraction(graph);

        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {

        int minResult = runProgram();

        for (int i = 0; i < 100; i++) {
            int result = runProgram();
            if (result < minResult) {
                minResult = result;
            }
        }

        System.out.println("Final min cut result = " + minResult);
    }

}

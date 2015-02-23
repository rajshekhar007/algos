/*
 * Copyright (c) Shekhar Raj
 */

package com.raj.coursera.algos.part1.graph;

import com.raj.helper.FileReaderHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Naive O(mn) implementation. Look at DijkstraHeap for a more elegant/ efficient implementaion of Dijkstra's algorithm.
 * <p>
 * Created by Sweta on 2/21/2015.
 */
public class Dijkstra {

    private static int debugNode = 61;
    private Queue<Node> Q;
    private Node[] graph;

    Dijkstra() {
        Q = new LinkedList<Node>();
    }

    public static void runProgram() throws InterruptedException {

        List<String> inputList = FileReaderHelper.read("E:\\workspace-intellij\\algos\\coursera\\resource\\dijkstraData.txt", false);

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.createGraph(inputList);
        // As we start from source vertex 1, it's min distance is 0
        dijkstra.setStartVertex(1);
        dijkstra.computeShortestPaths();
        dijkstra.printResult();
    }

    public static void main(String[] args) throws InterruptedException {
        runProgram();
        Thread.sleep(2000);
    }

    public void setStartVertex(int v) {
        Node startNode = this.graph[1];
        startNode.minDistanceSoFar = 0;
        this.Q.add(startNode);
        System.out.println("=== Start Vertex : " + v);
    }

    /**
     * An O(mn) algorithm to compute shortest paths
     */
    public void computeShortestPaths() {

        System.out.println("Computing shortest paths .......");

        for (int i = 1; i < graph.length; i++) {   // for all vertices 'n'

            Node sourceNode = graph[i];
            Edge[] edges = sourceNode.edges;

            if (sourceNode.vertex == debugNode) {
                System.out.println("\n******SourceNode=" + sourceNode);
            }

            for (int j = 1; j < edges.length; j++) {  // for all edges 'm' of vertex i

                // First compute the new distance by adding source node's min distance
                Edge edge = edges[j];
                int dist1 = sourceNode.minDistanceSoFar + edge.distance;  // this is the new calculated distance

                // Then get the destination edge's distance
                Node edgeNode = graph[edge.vertex];

                int dist2 = edgeNode.minDistanceSoFar;  // the other competing distance already set for this node

                if (edgeNode.vertex == debugNode) {
                    System.out.print("\nS:" + sourceNode);
                    System.out.print(" -----> E:" + edgeNode);
                    System.out.print("EdgeLength:" + edge.distance + ", NewDist: " + dist1 + ", OldEdgeDist: " + dist2);
                }

                // New greedy distance is min of the above two
                if (dist1 < dist2) {
                    edgeNode.minDistanceSoFar = dist1;
                    edgeNode.path += "->" + sourceNode.vertex;
                }

                if (edgeNode.vertex == debugNode && dist1 < dist2) {
                    System.out.print(", FinalEdgeNode: " + edgeNode);
                }
            }
        }
        printShortestDistances();
    }

    /**
     * An O(mn) algorithm to compute shortest paths
     */
    public void computeShortestPaths1() {

        System.out.println("Computing shortest paths .......");

        while (!Q.isEmpty()) {   // BFS for all vertices 'n'
            //System.out.println("Q -> " + Q);
            Node sourceNode = Q.remove();
            sourceNode.isVisited = true;
            Edge[] edges = sourceNode.edges;
            //.out.println("Processing : " + sourceNode);

            if (sourceNode.vertex == debugNode) {
                System.out.println("\n******SourceNode=" + sourceNode);
            }

            for (int j = 1; j < edges.length; j++) {  // for all edges 'm' of vertex i

                // First compute the new distance by adding source node's min distance
                Edge edge = edges[j];
                int dist1 = sourceNode.minDistanceSoFar + edge.distance;  // this is the new calculated distance

                // Then get the destination edge's distance
                Node edgeNode = graph[edge.vertex];

                int dist2 = edgeNode.minDistanceSoFar;  // the other competing distance already set for this node

                if (edgeNode.vertex == debugNode) {
                    System.out.print("\nS:" + sourceNode);
                    System.out.print(" -----> E:" + edgeNode);
                    System.out.print("EdgeLength:" + edge.distance + ", NewDist: " + dist1 + ", OldEdgeDist: " + dist2);
                }

                // New greedy distance is min of the above two
                if (dist1 < dist2) {
                    edgeNode.minDistanceSoFar = dist1;
                    edgeNode.path += "->" + sourceNode.vertex;
                }

                if (edgeNode.vertex == debugNode && dist1 < dist2) {
                    System.out.print(", FinalEdgeNode: " + edgeNode);
                }

                if (!edgeNode.isVisited) {
                    edgeNode.isVisited = true;
                    Q.add(edgeNode);
                }
            }
        }
        printShortestDistances();
    }

    public void printShortestDistances() {
        for (int i = 0; i < graph.length; i++) {
            if (graph[i] == null) continue;
            System.out.println(graph[i]);
        }
    }

    public void createGraph(List<String> inputList) {

        this.graph = new Node[inputList.size() + 1]; // extra for the first sentinel node

        for (String str : inputList) {
            String[] arr = str.split("\t");
            int sourceVertex = Integer.parseInt(arr[0]);
            Edge[] edges = new Edge[arr.length];

            Node node = new Node(sourceVertex);
            node.vertex = sourceVertex;
            node.edges = edges;
            this.graph[sourceVertex] = node;

            for (int i = 1; i < arr.length; i++) {
                Edge edge = new Edge(Integer.parseInt(arr[i].split(",")[0]), Integer.parseInt(arr[i].split(",")[1]));
                edges[i] = edge;
            }
        }
    }

    /**
     * Print shortest paths for vertices 7,37,59,82,99,115,133,165,188,197
     */
    public void printResult() {
        System.out.println("\n********** RESULTS **********");
        int[] verticesToPrint = {1, 2, 3, 4, 5, 6, 7};
        //int[] verticesToPrint = {7,37,59,82,99,115,133,165,188,197};
        String csv = "";
        for (int v : verticesToPrint) {
            System.out.println(this.graph[v]);
            csv += this.graph[v].minDistanceSoFar + ",";
        }
        System.out.println("CSV ---> " + csv);
    }

    private class Node {
        int vertex;
        int minDistanceSoFar;
        boolean isVisited;
        String path;
        Edge[] edges;

        Node(int vertex) {
            this.vertex = vertex;
            this.minDistanceSoFar = 1000000;  // initialize as unreachable
            this.isVisited = false;
            this.path = "Start";
        }

        @Override
        public String toString() {
            return "Node{" + //visited=" + isVisited +
                    "v=" + vertex +
                    ", minDist=" + minDistanceSoFar + ", path=" + path +
                    '}';
        }
    }

    private class Edge {
        int vertex;
        int distance;

        Edge(int v, int d) {
            vertex = v;
            distance = d;
        }

    }

}

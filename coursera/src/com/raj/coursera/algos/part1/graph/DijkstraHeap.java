/*
 * Copyright (c) Shekhar Raj
 */

package com.raj.coursera.algos.part1.graph;

import com.google.common.collect.Lists;
import com.raj.helper.FileReaderHelper;

import java.util.*;

public class DijkstraHeap {

    private static List<Graph.Edge> graphEdges;  // given 'm' edges

    private static Graph g; // graph built from vertices and neighboring edges

    public static void createGraph(String file) {
        List<String> inputList = FileReaderHelper.read(file, false);
        graphEdges = Lists.newArrayList();
        for (String str : inputList) {
            String[] arr = str.split("\t");
            String sourceVertex = arr[0];
            for (int i = 1; i < arr.length; i++) {
                graphEdges.add(new Graph.Edge(sourceVertex, arr[i].split(",")[0], Integer.parseInt(arr[i].split(",")[1])));
            }
        }
        Graph.Edge[] A = new Graph.Edge[graphEdges.size()];
        A = graphEdges.toArray(A);
        g = new Graph(A);
    }

    public static void main(String[] args) {
        createGraph("E:\\workspace-intellij\\algos\\coursera\\resource\\dijkstraData.txt");
        g.dijkstra("1");  // start vertex
        g.printShortestPath("200");  // end vertex
        int[] verticesToPrint = {7, 37, 59, 82, 99, 115, 133, 165, 188, 197};
        g.printPaths(verticesToPrint);
        g.printAllPaths();
    }
}

class Graph {

    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

    /**
     * Builds a graph G with input list of edges
     *
     * @param edges
     */
    public Graph(Edge[] edges) {
        graph = new HashMap<>(edges.length);

        // one pass to find all vertices
        for (Edge e : edges) {
            if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
            if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
        }

        // another pass to set neighbouring vertices
        for (Edge e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    /**
     * Dijkstra's Algorithm to compute shortest path(s), given a start vertex
     *
     * @param startName
     */
    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        Queue<Vertex> q = new PriorityQueue<>();

        // Initialize vertices with distances
        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;  // start vertex distance is 0
            v.dist = v == source ? 0 : Integer.MAX_VALUE;  // others are unreachable
            q.add(v);   // add them to priority q, will be used to extract min distance vertex
        }

        dijkstra(q);
    }

    /**
     * q(Heap) now has 'n' vertices and we keep removing the min distance vertex as we move forward greedily.
     * Process 'm' edges in O(m) time.
     * <p>
     * We compute the alternate distance, and if it is smaller then we update the neighbor vertex.
     * O(log n) for q operations.
     * <p>
     * Total time = O(m log n)
     *
     * @param q
     */
    private void dijkstra(final Queue<Vertex> q) {
        Vertex u, v;

        while (!q.isEmpty()) {

            u = q.poll(); // vertex with shortest distance (first iteration will return source)

            if (u.dist == Integer.MAX_VALUE)
                break; // we can ignore u (and any other remaining vertices) since they are unreachable

            //look at distances to each neighbour (u -> v*)
            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); //the neighbour in this iteration
                final int alternateDist = u.dist + a.getValue();  // compute a new greedy score
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v);    // remove and update that vertex distance
                    v.dist = alternateDist;
                    v.previous = u;     // for wiring the path
                    q.add(v);   // add back to heap
                }
            }
        }
    }

    /**
     * Prints a path from the source to the specified vertex
     */
    public void printShortestPath(String endName) {
        System.out.println("Shortest Path: ");
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }
        graph.get(endName).printPath();
        System.out.println();
    }

    /**
     * Prints the path from the source to every vertex (output order is not guaranteed)
     */
    public void printAllPaths() {
        System.out.println("\nAll Paths & their distances: ");
        for (Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }

    public void printPaths(int[] verticesToPrint) {
        System.out.println("\nSelected Paths & their distances: ");
        for (Integer v : verticesToPrint) {
            graph.get(v.toString()).printPath();
            System.out.println();
        }
    }

    /**
     * One edge of the graph (only used by Graph constructor)
     */
    public static class Edge {
        public final String v1, v2;
        public final int dist;

        public Edge(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v1='" + v1 + '\'' +
                    ", v2='" + v2 + '\'' +
                    ", dist=" + dist +
                    '}';
        }
    }

    /**
     * One vertex of the graph, complete with mappings to neighbouring vertices
     */
    public static class Vertex implements Comparable<Vertex> {
        public final String name;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();
        public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;  // pointer to previous vertex, will be used to print paths

        public Vertex(String name) {
            this.name = name;
        }

        /**
         * Recursively print path from the prev pointer
         */
        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name);
            } else {
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.dist);
            }
        }

        public int compareTo(Vertex other) {
            return Integer.compare(dist, other.dist);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "name='" + name + '\'' +
                    ", dist=" + dist +
                    '}';
        }
    }
}
package com.raj.tree;

/**
 * @author: sraj1
 * Date:    10/10/12
 */
public class Node {

    public int data;
    public Node left, right;
    public Node parent;
    public boolean isVisited;

    public Node() {

    }

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node left, Node right, Node parent, boolean visited) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
        isVisited = visited;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                /*", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                ", isVisited=" + isVisited + */
                '}';
    }
}

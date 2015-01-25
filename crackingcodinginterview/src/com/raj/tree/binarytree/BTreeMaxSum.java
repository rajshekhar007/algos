package com.raj.tree.binarytree;

import com.raj.tree.Node;

/**
 * @author: sraj1
 * Date:    10/14/12
 */
public class BTreeMaxSum {

    private Node root;
    private Node maxSumNode;
    private int maxSum;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getMaxSumNode() {
        return maxSumNode;
    }

    public void setMaxSumNode(Node maxSumNode) {
        this.maxSumNode = maxSumNode;
    }

    public int getMaxSum() {
        calcMaxSum(root);
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    private int calcMaxSum(Node node) {
        if (node == null) return 0;
        int sum = node.data + calcMaxSum(node.left) + calcMaxSum(node.right);
        int prevMaxSum = maxSum;
        maxSum = Math.max(sum, maxSum);
        if (prevMaxSum != maxSum) maxSumNode = node;
        return sum;
    }

}


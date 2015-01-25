package com.raj.tree.binarytree;

import com.raj.tree.Node;

/**
 * @author: sraj1
 * Date:    10/14/12
 */
public class BTreeHelper {

    Node root;
    int maxSum;
    int endIdx;
    int[] arr = new int[10];

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public int getEndIdx() {
        return endIdx;
    }

    public void setEndIdx(int endIdx) {
        this.endIdx = endIdx;
    }

    public int[] getMaxSumPathArr(Node node) {
        calcMaxSumPath(node, new int[10], 0);
        return arr;
    }

    private void calcMaxSumPath(Node node, int[] A, int depth) {
        if (node == null) return;

        A[depth] = node.data;
        if (node.left == null && node.right == null) {
            int sum = 0;
            for (int i = 0; i <= depth; i++) {
                sum += A[i];
            }
            if (sum > maxSum) {
                maxSum = sum;
                arr = A.clone();
                endIdx = depth;
            }
        }

        calcMaxSumPath(node.left, A, depth + 1);
        calcMaxSumPath(node.right, A, depth + 1);
    }

}

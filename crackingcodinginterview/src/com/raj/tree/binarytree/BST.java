package com.raj.tree.binarytree;

import com.raj.tree.Node;

/**
 * @author: sraj1
 * Date:    10/10/12
 */
public class BST {

    static int cnt = 0; // to count iterations
    /**
     * Given a node, print the max sum of nodes below it
     * <p/>
     * Algo:
     * - null check
     * - calculate sum of all nodes for this node
     * - maxSum is max of this sum or maxSum
     * - do the same for left and right subtrees
     * O(n2) soln
     */

    int maxSum = 0;
    private Node root;

    public static void main(String[] args) {

        /*
               10
              /  \
             5    20
            / \   /
           2  8  15

         */

        BST bst1 = new BST();
        bst1.insert(10);
        bst1.insert(20);
        bst1.insert(5);
        bst1.insert(8);
        bst1.insert(15);
        bst1.insert(2);

        Node root = bst1.getRoot();
        System.out.println(bst1.lookup(root, 5) + "\n");

        bst1.inorder(bst1.getRoot());

        System.out.println("Print paths: ");
        bst1.printPaths(root, 0, new int[10]);

        System.out.println("Print paths with sum: ");
        bst1.printPathSum(root, 23, new int[10], 0);

        System.out.println("\nPrint size: " + bst1.size(root));
        System.out.println("Print height: " + bst1.height(root));
        System.out.println("Print sum: " + bst1.sum(root));

        System.out.println("isBST? " + bst1.isBST(root, Integer.MIN_VALUE));

        System.out.println("Delete Node from BST");
        bst1.delete(root, 5);
        System.out.println("Print paths: ");
        bst1.printPaths(root, 0, new int[10]);

        /*System.out.println("Mirror Tree: ");
        bst1.mirrorTree(root);
        bst1.printPaths(root, 0, new int[10]);*/

        /*
              -10
             /  \
            5    20
           / \   /
          2  8  -15

        */
        Node n1 = new Node(-10);
        Node root1 = n1;
        n1.left = new Node(5);
        n1.left.left = new Node(2);
        n1.left.right = new Node(8);
        n1.right = new Node(20);
        n1.right.left = new Node(-15);
        System.out.println("Print paths: ");
        bst1.printPaths(root1, 0, new int[10]);

        System.out.println("Print max sum: " + bst1.maxSum(root1) + " total iterations=" + cnt);
        System.out.println("Print max sum 2: " + bst1.maxSum2(root1));

        BTreeMaxSum bTreeMaxSum = new BTreeMaxSum();
        bTreeMaxSum.setRoot(root1);
        System.out.println("Print max sum BTree: " + bTreeMaxSum.getMaxSum() + ", Max Sum node is " + bTreeMaxSum.getMaxSumNode());

        BTreeHelper bTreeHelper = new BTreeHelper();
        int[] arr = bTreeHelper.getMaxSumPathArr(root1);
        for (int i = 0; i <= bTreeHelper.getEndIdx(); i++)
            System.out.println(arr[i]);
        System.out.println(bTreeHelper.getMaxSum());
        System.out.println(bst1.sameTree(root, root1));

    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node insert(int data) {
        root = insert(root, data);
        return root;
    }

    public Node insert(Node n, int data) {
        if (n == null)
            return new Node(data);
        if (data < n.data)
            n.left = insert(n.left, data);
        else n.right = insert(n.right, data);

        return n;
    }

    public void inorder(Node n) {
        if (n == null) return;
        inorder(n.left);
        System.out.println(n.data);
        inorder(n.right);
    }

    public void preorder(Node n) {
        if (n == null) return;
        System.out.println(n.data);
        preorder(n.left);
        preorder(n.right);
    }

    public void postorder(Node n) {
        if (n == null) return;
        postorder(n.left);
        postorder(n.right);
        System.out.println(n.data);
    }

    /**
     * O(log n) complexity as it keeps excluding half subtree at each node
     */
    public boolean lookup(Node n, int data) {
        if (n == null) return false;
        if (n.data == data) return true;
        if (data < n.data)
            return lookup(n.left, data);
        else return lookup(n.right, data);
    }

    /**
     * O(n) as it searches all left and right subtrees
     */
    public boolean lookupAnyBTree(Node n, int data) {
        if (n == null) return false;
        if (n.data == data) return true;
        return (lookupAnyBTree(n.left, data) || lookupAnyBTree(n.right, data));
    }

    /**
     * Print all possible paths from root to leafs
     * Algo :
     * - While traversing tree, we need to keep track of nodes traversed. Use either a LL or array simply
     * - if(n==null) return
     * - arrVisitedNodes[depth + 1] = n.data;
     * - {print all arr till depth; return;}
     * - traverse left, right subtrees;
     */
    public void printPaths(Node n, int depth, int[] arr) {
        if (n == null) return;

        arr[depth] = n.data;              // process node
        if (n.left == null && n.right == null) {
            for (int i = 0; i <= depth; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
            return;
        }

        printPaths(n.left, depth + 1, arr);   // left subtree recurse
        printPaths(n.right, depth + 1, arr);  // right subtree recurse

    }

    /**
     * Algo:
     * - null check
     * - return this node size i.e. 1 + size of left subtree + size of right subtree
     */
    public int size(Node n) {
        if (n == null) return 0;

        return 1 + size(n.left) + size(n.right);
    }

    /**
     * Algo:
     * - null check
     * - return 1 + max of (height of left subtree, height of right subtree)
     */
    public int height(Node n) {
        if (n == null) return 0;

        return 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * Print the sum of all nodes below a given node
     * <p/>
     * Algo:
     * - null check
     * - return node's data + sum(left subtree) + sum(right subtree)
     */
    public int sum(Node n) {
        if (n == null) return 0;
        cnt++;
        return n.data + sum(n.left) + sum(n.right);
    }

    /**
     * Delete a node in BST
     * <p/>
     * Algo:
     * ## Cases ##
     * The node to be deleted is:
     * - Leaf, find it and set it to null.
     * - Has just one child, track it's parent and it's left/right as node's left/right.
     * - Has both left & right, find the minValue from right subtree and swap it's value with this node and
     * set minValue node to null.
     * - Keep tracking parent
     */
    public void delete(Node n, int data) {

        if (n == null) return;

        /*if(root.data == data) {

        }*/

        if (n.left != null && n.left.data == data)
            remove(n, n.left);
        if (n.right != null && n.right.data == data)
            remove(n, n.right);

        if (data < n.data)
            delete(n.left, data);
        else delete(n.right, data);
    }

    public void remove(Node parent, Node n) {
        boolean isNodeLeftOfParent = false;
        if (parent.left == n) isNodeLeftOfParent = true;

        // No child, leaf case
        if (n.left == null && n.right == null) {
            if (isNodeLeftOfParent) parent.left = null;
            else parent.right = null;
            return;
        }

        // Has just one child
        if (n.left == null) {
            if (isNodeLeftOfParent) parent.left = n.right;
            else parent.right = n.right;
            return;
        }

        if (n.right == null) {
            if (isNodeLeftOfParent) parent.left = n.left;
            else parent.right = n.left;
            return;
        }

        // Has both child
        Node minNodeRight = minValue(n.right);
        n.data = minNodeRight.data;
        minNodeRight = null;

    }

    public Node minValue(Node n) {
        if (n.left == null) return n;
        else return minValue(n.left);
    }

    public int maxSum(Node n) {
        maxSum1(n);
        return maxSum;
    }

    private void maxSum1(Node n) {
        if (n == null) return;
        int sum = n.data + sum(n.left) + sum(n.right);
        maxSum = Math.max(sum, maxSum);
        maxSum1(n.left);
        maxSum1(n.right);
    }

    /**
     * Better soln for above:
     * - init maxSum global var which stores max sum
     * - for a node, find sum as this node's data + sum of left subtree + sum of right subtree
     * - maxSum = max(sum, maxSum)
     */
    public int maxSum2(Node n) {
        maxSum3(n);
        return maxSum;
    }

    private int maxSum3(Node n) {
        if (n == null) return 0;
        int sum = n.data + maxSum3(n.left) + maxSum3(n.right);
        maxSum = Math.max(sum, maxSum);
        return sum;
    }

    /**
     * Print path which sums up to X.
     */

    public void printPathSum(Node n, int sum, int arr[], int depth) {
        if (n == null) return;
        arr[depth] = n.data;
        int subSum = sum - n.data;

        if (n.left == null && n.right == null && subSum == 0) {
            for (int i = 0; i <= depth; i++) {
                System.out.print(arr[i] + " ");
            }
        }
        printPathSum(n.left, subSum, arr, depth + 1);
        printPathSum(n.right, subSum, arr, depth + 1);
    }

    /**
     * Mirror a tree
     */
    public void mirrorTree(Node n) {
        if (n == null) return;
        // swap left, right
        Node t = n.left;
        n.left = n.right;
        n.right = t;
        mirrorTree(n.left);
        mirrorTree(n.right);
    }

    /**
     * Check if two b-trees are same
     */
    public boolean sameTree(Node n1, Node n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null || n2 == null) return false;
        if (n1.data != n2.data) return false;
        return sameTree(n1.left, n2.left) && sameTree(n1.right, n2.right);
    }

    /**
     * Check if a BTree is a BST.
     * <p/>
     * Algo1A: A BST's inorder traversal should produce sorted list. Traverse, store in list, check if sorted. Uses O(n) extra time/space.
     * Algo1B: While traversing check if the previous node's value was lesser than current node's value. Best soln.
     * Algo2: While traversing, narrow min/max and a node's value should lie within this range.
     */
    public boolean isBST(Node n, int prevData) {
        if (n == null) return true;
        // traverse left
        boolean leftAnswer = isBST(n.left, prevData);
        // process node
        if (prevData > n.data) return false;
        prevData = n.data;
        // traverse right
        return leftAnswer && isBST(n.right, prevData);
    }

    /**
     * LCA of a BST : Easy as it is BST and we can leverage that info. Use comparison of nodes <,=,> and cover all cases
     */
    public Node LCABST(Node n, int a, int b) {
        // Pre-check : both node should exist in BST, else return null
        if (lookup(n, a) && lookup(n, b)) return LCABSTHelper(n, a, b);
        return null;
    }

    private Node LCABSTHelper(Node n, int a, int b) {
        if (n == null) return null;
        if (n.data == a || n.data == b)
            return n;    // one of the nodes is parent of other, but check if the other exists though
        if (a < n.data && b < n.data) return LCABSTHelper(n.left, a, b);  // both a & b are smaller, recurse left
        if (a > n.data && b > n.data) return LCABSTHelper(n.right, a, b); // both a & b are greater, recurse right
        else return n;  // i.e. if(a < n.data && b > n.data) this node is LCA
    }

    /**
     * LCA of a BT : Little tricky, but idea is the same.
     */
    public Node LCA(Node n, int a, int b) {
        // Pre-check : both node should exist in BST, else return null
        if (lookup(n, a) && lookup(n, b)) return LCAHelper(n, a, b);
        return null;
    }

    private Node LCAHelper(Node n, int a, int b) {
        if (n == null) return null;
        if (n.data == a || n.data == b)
            return n;    // one of the nodes is parent of other, but check if the other exists though
        Node left = LCAHelper(n.left, a, b);
        Node right = LCAHelper(n.right, a, b);
        if (left != null) return left;
        else return right;
    }

}

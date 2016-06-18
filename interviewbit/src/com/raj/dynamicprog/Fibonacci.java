package com.raj.dynamicprog;

/**
 * Created by rshekh1 on 6/13/16.
 */
public class Fibonacci {

    /**
     * Dynamic Programming is an algorithmic paradigm that solves a given complex problem by breaking it into subproblems and stores the results of subproblems to avoid computing the same results again. Following are the two main properties of a problem that suggest that the given problem can be solved using Dynamic programming.

     1) Overlapping Subproblems
     2) Optimal Substructure

     1) Overlapping Subproblems:
     Like Divide and Conquer, Dynamic Programming combines solutions to sub-problems. Dynamic Programming is mainly used when solutions of same subproblems are needed again and again. In dynamic programming, computed solutions to subproblems are stored in a table so that these don’t have to recomputed. So Dynamic Programming is not useful when there are no common (overlapping) subproblems because there is no point storing the solutions if they are not needed again. For example, Binary Search doesn’t have common subproblems. If we take example of following recursive program for Fibonacci Numbers, there are many subproblems which are solved again and again.
     Recursion tree for execution of fib(5)

     fib(5)
     /             \
     fib(4)                fib(3)
     /      \                /     \
     fib(3)      fib(2)         fib(2)    fib(1)
     /     \        /    \       /    \
     fib(2)   fib(1)  fib(1) fib(0) fib(1) fib(0)
     /    \
     fib(1) fib(0)
     We can see that the function f(3) is being called 2 times. If we would have stored the value of f(3), then instead of computing it again, we would have reused the old stored value. There are following two different ways to store the values so that these values can be reused.

     a) Memoization (Top Down):
     b) Tabulation (Bottom Up):

     a) Memoization (Top Down): The memoized program for a problem is similar to the recursive version with a small modification that it looks into a lookup table before computing solutions. We initialize a lookup array with all initial values as NIL. Whenever we need solution to a subproblem, we first look into the lookup table. If the precomputed value is there then we return that value, otherwise we calculate the value and put the result in lookup table so that it can be reused later.
     */
    private static Integer[] lookupTable = null;

    public static int fib(int n) {
        if (lookupTable == null) lookupTable = new Integer[n+1];
        if (n==0 || n==1) lookupTable[n] = n;
        if (n > 1 && lookupTable[n] == null) {
            lookupTable[n] = fib(n-1) + fib(n-2);
        }
        return lookupTable[n];
    }

    public static int fib1(int n) {
        int f[] = new int[n+1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    public static void main(String[] args) {
        //System.out.println(Fibonacci.fib(7));
        System.out.println(Fibonacci.fib1(7));
    }
}

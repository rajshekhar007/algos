package com.raj.stackqueues;

import java.util.*;

public class EvalExpression {

    /**
     *
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     Valid operators are +, -, *, /. Each operand may be an integer or another expression.

     ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

     * @param A
     * @return
     */
    public int evalRPN(List<String> A) {
        if (A == null || A.isEmpty()) return 0;
        if (A.size() == 1) return Integer.parseInt(A.get(0));
        Set<String> operands = new HashSet();
        operands.add("+");
        operands.add("-");
        operands.add("*");
        operands.add("/");

        Stack<Integer> stack = new Stack<>();
        Integer result = null;
        for (String s : A) {
            if (operands.contains(s)) {
                int b = stack.pop();
                int a;
                if (result == null) a = stack.pop();
                else a = result;

                switch (s) {
                    case "+" : result = (a+b);
                        break;
                    case "-" : result = (a-b);
                        break;
                    case "/" : result = (a/b);
                        break;
                    case "*" : result = (a*b);
                        break;
                }
                continue;
            }
            stack.push(Integer.parseInt(s));
        }
        return result;
    }

    public static void main(String[] args) {
        EvalExpression e = new EvalExpression();
        System.out.println(e.evalRPN(Arrays.asList("2", "1", "+", "3", "*")));
        System.out.println(e.evalRPN(Arrays.asList("4", "13", "5", "/", "+")));
        System.out.println(e.evalRPN(Arrays.asList("5", "1", "2", "+", "4", "*", "+", "3", "-")));
    }
}

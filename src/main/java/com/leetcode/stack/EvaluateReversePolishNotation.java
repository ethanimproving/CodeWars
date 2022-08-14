package com.leetcode.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        var stack = new Stack<Integer>();
        for (var token : tokens) {
            switch (token) {
                case "+":
                    stack.add(stack.pop() + stack.pop());
                    break;
                case "-":
                    var top = stack.pop();
                    var secondToTop = stack.pop();
                    stack.add(secondToTop - top);
                    break;
                case "*":
                    stack.add(stack.pop() * stack.pop());
                    break;
                case "/":
                    top = stack.pop();
                    secondToTop = stack.pop();
                    stack.add(secondToTop / top);
                    break;
                default:
                    stack.add(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }

    @Test
    void demo() {
        assertEquals(9, evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        assertEquals(6, evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        assertEquals(22, evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}

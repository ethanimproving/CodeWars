package com.leetcode.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidParentheses {
    // ([]{})
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        var stack = new Stack<Character>();
        if (!s.isBlank() && Arrays.asList(')', '}', ']').contains(s.charAt(0))) return false;
        for (int i = 0; i < s.length(); i++) {
            var currentChar = s.charAt(i);
            if (stack.isEmpty()) {
                stack.add(currentChar);
                continue;
            }
            var previousChar = stack.peek();
            if (previousChar == '(' && currentChar == ')')
                stack.pop();
            else if (previousChar == '[' && currentChar == ']')
                stack.pop();
            else if (previousChar == '{' && currentChar == '}')
                stack.pop();
            else
                stack.add(currentChar);
        }
        return stack.isEmpty();
    }

    @Test
    void demo() {
        assertTrue(isValid("()[]{}"));     // standard case
        assertTrue(isValid("([]{})"));      // valid outer
        assertFalse(isValid("(([]{})"));    // odd number
        assertTrue(isValid(""));            // empty
        assertFalse(isValid(")([]{}"));     // closing first char
    }
}

package com.leetcode.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidParentheses {
    // ([]{})
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false; // If length is odd, we know there's an unclosed parenthese
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() && (s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']'))
                return false; // If first character is a closing parenthese, there can't be an opening
            else {
                if (!stack.isEmpty()) {
                    if (stack.peek() == '(' && s.charAt(i) == ')')
                        stack.pop();
                    else if (stack.peek() == '{' && s.charAt(i) == '}')
                        stack.pop();
                    else if (stack.peek() == '[' && s.charAt(i) == ']')
                        stack.pop();
                    else
                        stack.add(s.charAt(i)); // If current char doesn't close an unclosed char in the stack, add it to the stack
                } else stack.add(s.charAt(i)); // Add unclosed chars to stack
            }
        }
        return stack.isEmpty();
    }

    @Test
    void demo() {
        boolean result = isValid("([]{})");
        assertTrue(result);
    }
}

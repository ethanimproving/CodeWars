package com.leetcode.dynamicprogramming1d;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClimbingStairs {
    public int climbStairs(int n) {
        int bottom = 1;
        int secondToLast = 1;
        int thirdToLast;

        for (int i = 0; i < n - 1; i++) {
            thirdToLast = bottom + secondToLast;
            bottom = secondToLast;
            secondToLast = thirdToLast;
        }
        return secondToLast;
    }

    @Test
    void demo() {
        assertEquals(5, climbStairs(4));
        assertEquals(8, climbStairs(5));
        assertEquals(55, climbStairs(9));
    }
}

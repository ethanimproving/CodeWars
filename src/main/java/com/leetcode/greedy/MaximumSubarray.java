package com.leetcode.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];

        var sum = 0;
        var max = Integer.MIN_VALUE;

        for (var num : nums) {
            sum += num;
            max = Math.max(max, sum);

            if (sum < 0) sum = 0; // Ignore all the numbers to the left and restart from the next index.
        }
        return max;
    }

    @Test
    void demo() {
        assertEquals(6, maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}

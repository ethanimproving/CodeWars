package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/product-of-array-except-self/">Product of Array Except Self</a>
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] arr = new int[nums.length];

        /* Right and left bounds should be neutral by default. */
        int right = 1, left = 1;

        /* In first pass calculate the left product. */
        for (int i = 0; i < nums.length; i++) {
            arr[i] = left;
            left *= nums[i];
        }

        /* In second pass calculate the right product. Left product * right product accounts for all numbers excluding self. */
        for (int i = nums.length - 1; i >= 0; i--) {
            arr[i] *= right;
            right *= nums[i];
        }
        return arr;
    }

    @Test
    void demo() {
        assertArrayEquals(new int[]{24, 12, 8, 6}, productExceptSelf(new int[]{1, 2, 3, 4}));
    }
}

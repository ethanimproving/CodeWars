package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/two-sum/">Two Sum</a>
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        var differenceToIndex = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];
            var diff = target - num;

            if (differenceToIndex.containsKey(num)) {
                return new int[]{differenceToIndex.get(num), i};
            }

            differenceToIndex.put(diff, i);
        }

        return new int[0];
    }

    @Test
    void demo() {
        assertArrayEquals(new int[]{0, 1}, twoSum(new int[]{2, 7, 11, 15}, 9));
        assertArrayEquals(new int[]{1, 2}, twoSum(new int[]{3, 2, 4}, 6));
    }
}

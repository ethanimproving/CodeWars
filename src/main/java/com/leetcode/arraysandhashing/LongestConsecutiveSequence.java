package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/longest-consecutive-sequence/">Longest Consecutive Sequence</a>
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        var set = new HashSet<Integer>();
        for (int num : nums) set.add(num);

        int longestStreak = 1;
        for (int num : nums) {
            if (!set.contains(num - 1)) { // Look for left neighbor - if none exists, find sequence
                int count = 1;
                while (set.contains(++num)) { // If right neighbor exists, increment count and look for next right neighbor
                    count++;
                }
                longestStreak = Math.max(count, longestStreak);
            }
        }
        return longestStreak;
    }

    @Test
    void demo() {
        assertEquals(4, longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        assertEquals(9, longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        assertEquals(0, longestConsecutive(new int[0]));
    }
}
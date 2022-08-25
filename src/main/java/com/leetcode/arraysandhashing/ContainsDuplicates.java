package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/contains-duplicate/">Contains Duplicate</a>
 */
public class ContainsDuplicates {
    public boolean containsDuplicate(int[] nums) {
        var hashSet = new HashSet<Integer>();
        for (var num : nums) {
            if (hashSet.contains(num))
                return true;
            else
                hashSet.add(num);
        }
        return false;
    }

    @Test
    void demo() {
        assertTrue(containsDuplicate(new int[]{1, 2, 3, 1}));
        assertFalse(containsDuplicate(new int[]{1, 2, 3, 4}));
    }
}

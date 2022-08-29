package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/top-k-frequent-elements/">Top K Frequent Elements</a>
 */
public class TopKFrequentElements {
    /**
     * Time Complexity: O(nlog(k))
     * Space Complexity: O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        var numToNumOfAppearances = new HashMap<Integer, Integer>();
        /* Store map of unique numbers with the value as how many instances that number appears in nums. */
        for (int num : nums) numToNumOfAppearances.put(num, numToNumOfAppearances.getOrDefault(num, 0) + 1);

        /* Sort elements by number of appearances and return the top 2. */
        return numToNumOfAppearances.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).limit(k).mapToInt(Map.Entry::getKey).toArray();
    }

    @Test
    void demo() {
        assertArrayEquals(new int[]{1, 2}, topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        assertArrayEquals(new int[]{1}, topKFrequent(new int[]{1}, 1));
        assertArrayEquals(new int[]{-1}, topKFrequent(new int[]{-1, -1}, 1));
    }
}

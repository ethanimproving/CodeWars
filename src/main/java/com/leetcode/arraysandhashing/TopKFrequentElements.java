package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

    /**
     * Time Complexity: O(nlog(k))
     * Space Complexity: O(n)
     */
    public int[] topKFrequentWithPriority(int[] nums, int k) {
        var numToNumOfAppearances = new HashMap<Integer, Integer>();
        /* Store map of unique numbers with the value as how many instances that number appears in nums. */
        for (int num : nums) numToNumOfAppearances.put(num, numToNumOfAppearances.getOrDefault(num, 0) + 1);

        /* Objects are processed according to priority, based on natural order of comparator provided at construction.
         * In this case we are ordering by the number that each number appears in nums. */
        var pq = new PriorityQueue<Map.Entry<Integer, Integer>>(Comparator.comparingInt(Map.Entry::getValue));

        for (var it : numToNumOfAppearances.entrySet()) {
            pq.add(it);
            /* If the number of elements in pq is greater than k, remove the head element. Because the priority queue
             * is ordered by number of appearances, the head will be the smallest member of the set, which we want
             * to disregard. */
            if (pq.size() > k) pq.poll();
        }

        /* Add remaining nums to returning array in order of most to least. */
        int[] arr = new int[k];
        int i = k;
        while (!pq.isEmpty()) {
            arr[--i] = pq.poll().getKey();
        }
        return arr;
    }

    @Test
    void demo() {
        assertArrayEquals(new int[]{1, 2}, topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        assertArrayEquals(new int[]{1}, topKFrequent(new int[]{1}, 1));
        assertArrayEquals(new int[]{-1}, topKFrequent(new int[]{-1, -1}, 1));
        assertArrayEquals(new int[]{1, 2}, topKFrequentWithPriority(new int[]{1, 1, 1, 2, 2, 3}, 2));
    }
}

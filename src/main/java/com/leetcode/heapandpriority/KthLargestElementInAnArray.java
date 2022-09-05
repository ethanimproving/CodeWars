package com.leetcode.heapandpriority;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">Kth Largest Element in an Array</a>
 */
public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        /* Create a minHeap. */
        var minHeap = new PriorityQueue<Integer>();

        /* Add the k biggest numbers to MaxHeap */
        for (int n : nums) {
            minHeap.add(n);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        /* Finally, minHeap has k largest elements left with root as the kth largest element. */
        return minHeap.peek();
    }

    @Test
    void demo() {
        assertEquals(5, findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        assertEquals(4, findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}

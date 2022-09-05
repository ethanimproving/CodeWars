package com.leetcode.heapandpriority;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-a-stream/">Kth Largest Element in a Stream</a>
 */
public class KthLargestElementInAStream {
    static class KthLargest {

        final PriorityQueue<Integer> heap = new PriorityQueue<>();
        final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int num : nums) add(num);
        }

        public int add(int val) {
            if (heap.size() < k) heap.offer(val); // Only add a value if there aren't k elements in the array yet.
            else if (val > heap.peek()) { // If new value is bigger than smallest value in heap,
                heap.poll(); // Remove the top element,
                heap.add(val); // And add the new element.
            }
            return heap.peek(); // Return smallest of value in heap.
        }
    }

    @Test
    void demo() {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        assertEquals(4, kthLargest.add(3));
        assertEquals(5, kthLargest.add(5));
        assertEquals(5, kthLargest.add(10));
        assertEquals(8, kthLargest.add(9));
        assertEquals(8, kthLargest.add(4));
    }
}

package com.leetcode.heapandpriority;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/last-stone-weight/">Last Stone Weight</a>
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        var maxHeap = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue).reversed()); // Create Max Heap by reversing natural Min Heap order
        for (int stone : stones) maxHeap.add(stone); // Add each number to Max Heap
        while (maxHeap.size() > 1) { // While there are more than 1 elements, battle the two greatest stones
            int stone1 = maxHeap.remove();
            int stone2 = maxHeap.remove();
            if (stone1 != stone2)
                maxHeap.add(stone1 - stone2); // If stones are not equal, add damaged stone back into the heap
        }
        return maxHeap.size() != 0 ? (maxHeap.remove()) : 0; // Return the last element
    }

    @Test
    void demo() {
        assertEquals(1, lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        assertEquals(1, lastStoneWeight(new int[]{1}));
    }
}

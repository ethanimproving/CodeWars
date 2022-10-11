package com.leetcode.heapandpriority;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/find-median-from-data-stream/">Find Median from Data Stream</a>
 */
public class MedianFinder {

    private Queue<Integer> smallHeap = new PriorityQueue<>(Collections.reverseOrder());
    private Queue<Integer> largeHeap = new PriorityQueue<>();
    private boolean even = true;

    public double findMedian() { // O(1)
        if (even) return (smallHeap.peek() + largeHeap.peek()) / 2.0;
        else return smallHeap.peek();
    }

    public void addNum(int num) { // O(log n)
        if (even) { // If even, add to largeHeap and poll smallest number to smallHeap
            largeHeap.offer(num);
            smallHeap.offer(largeHeap.poll());
        } else {
            smallHeap.offer(num); // Otherwise add to smallHeap and move largest number to largeHeap
            largeHeap.offer(smallHeap.poll());
        }
        even = !even;
    }

    @Test
    void demo() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        assertEquals(1.5, medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        assertEquals(2.0, medianFinder.findMedian()); // return 2.0
    }
}

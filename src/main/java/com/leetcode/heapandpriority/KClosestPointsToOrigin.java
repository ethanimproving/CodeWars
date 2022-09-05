package com.leetcode.heapandpriority;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/k-closest-points-to-origin/">K Closest Points to Origin</a>
 */
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        /* Sort by Euclidean distance (√(x1 - x2)2 + (y1 - y2)2). */
        var maxHeap = new PriorityQueue<int[]>((a, b) -> Integer.compare((b[0] * b[0] + b[1] * b[1]), (a[0] * a[0] + a[1] * a[1])));

        /* Add the k biggest numbers to MaxHeap */
        for (int[] point : points) {
            maxHeap.add(point);

            if (maxHeap.size() > k) {
                maxHeap.remove(); // Remove the head (farthest away).
            }
        }

        return maxHeap.toArray(new int[][]{});
    }

    @Test
    void demo() {
        assertArrayEquals(new int[][]{{-2, 2}}, kClosest(new int[][]{{1, 3}, {-2, 2}}, 1));
        assertArrayEquals(new int[][]{{-2, 4}, {3, 3}}, kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2));
    }
}

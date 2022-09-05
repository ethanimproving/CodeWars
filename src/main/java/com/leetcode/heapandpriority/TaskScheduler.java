package com.leetcode.heapandpriority;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/task-scheduler/">Task Scheduler</a>
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        /* If no wait time is needed, each task can be processed sequentially. */
        if (n == 0) return tasks.length;

        /* Create Max Heap by reversing natural Min Heap order to get most frequent elements first. */
        var maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());


        /* Queue to store how many of each task is left, and at what time they can be executed.
         * The time a task of the same type can be executed will be current time + n. */
        var waitingQueue = new LinkedList<Pair<Integer, Integer>>();


        /* Group tasks by how many occurrences there are, then add to priority queue. */
        maxHeap.addAll(new String(tasks).chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(Function.identity(), Collectors.collectingAndThen(Collectors.counting(), Long::intValue))).values());

        int time = 0;
        /* If either queue has any elements, keep processing. */
        while ((!maxHeap.isEmpty() || !waitingQueue.isEmpty())) {
            time++;
            /* If maxHeap is not empty, remove the head and decrement by 1, then add to waiting queue and process the next task. */
            if (!maxHeap.isEmpty()) {
                int val = maxHeap.poll();
                val--;
                if (val > 0)
                    waitingQueue.add(new Pair<>(val, time + n));
            }

            /* Check the oldest item in the waiting queue, if it can be processed, put back in maxHeap. */
            if (!waitingQueue.isEmpty() && waitingQueue.peek().getValue1() == time)
                maxHeap.add(waitingQueue.poll().getValue0());
        }
        return time;
    }

    @Test
    void demo() {
        assertEquals(8, leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        assertEquals(6, leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
        assertEquals(16, leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }
}

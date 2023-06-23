package com.leetcode.twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://www.algoexpert.io/questions/sorted-squared-array">Sorted Squared Arrays</a>
 */
public class SortedSquaredArrays {

public int[] sortedSquaredArray(int[] array) {

    /* Define two pointers */
    int[] sortedSquares = new int[array.length];
    int start = 0;
    int end = array.length - 1;

    /* Insert squares from largest to smallest */
    for( int idx = array.length - 1; idx >= 0; idx-- ) {

        int startValue = array[start];
        int endValue = array[end];

        /* If start is greater than end, insert as the greatest
        *  value into resulting array and increment p1 */
        if (Math.abs(startValue) > Math.abs(endValue)) {
            sortedSquares[idx] = startValue * startValue;
            start++;
        } else {
            sortedSquares[idx] = endValue * endValue;
            end--;
        }
    }

    return sortedSquares;
}

    @Test
    void demo(){
            var input = new int[] {1, 2, 3, 5, 6, 8, 9};
            var expected = new int[] {1, 4, 9, 25, 36, 64, 81};
            var actual = new SortedSquaredArrays().sortedSquaredArray(input);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], actual[i]);
        }
    }
}

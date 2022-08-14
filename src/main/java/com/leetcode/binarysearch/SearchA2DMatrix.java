package com.leetcode.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/search-a-2d-matrix/">Search a 2D Matrix</a>
 */
public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, column = matrix[0].length - 1;
        while (row < matrix.length && column >= 0) {
            if (matrix[row][column] == target)
                return true;
            else if (matrix[row][column] > target)
                column--;
            else
                row++;
        }
        return false;
    }

    @Test
    void demo() {
        assertTrue(searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        assertTrue(searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}, {62, 78, 83, 84}}, 34));
    }

}

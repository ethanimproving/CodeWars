package com.leetcode.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/set-matrix-zeroes/">Set Matrix Zeroes</a>
 */
public class SetMatrixZeroes {

    // Time complexity: O(n * m)
    // Space complexity: O(1)
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRow = false;

        /* Add markers to first row and first column. */
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                if (matrix[row][column] == 0) {
                    matrix[0][column] = 0; // Set column marker in first row (treating row 1 as external array to save space)
                    if (row == 0) { // the first column doesn't have a spot for matrix[0][0] since it overlaps the first row, so we're using a variable to indicate the first row.
                        firstRow = true;
                    } else {
                        matrix[row][0] = 0; // Set the row marker in first column
                    }
                }
            }
        }

        for (int row = 1; row < rows; row++) {
            for (int column = 1; column < cols; column++) {
                if (matrix[0][column] == 0 || matrix[row][0] == 0) { // If this point intersects a row or column marker, change it to zero
                    matrix[row][column] = 0;
                }
            }
        }

        /* Check if first column needs to be set to zeroes. (We can't do it above because we are dependent on the first column's markers to set the other rows.) */
        if (matrix[0][0] == 0) {
            for (int row = 0; row < rows; row++) {
                matrix[row][0] = 0;
            }
        }

        /* Check if first row needs to be set to zeroes. */
        if (firstRow) {
            for (int column = 0; column < cols; column++) {
                matrix[0][column] = 0;
            }
        }
    }

    @Test
    void demo() {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(matrix);
        assertArrayEquals(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, matrix);
        matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(matrix);
        assertArrayEquals(new int[][]{{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}}, matrix);
    }
}

package com.leetcode.math;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/spiral-matrix/">Spiral Matrix</a>
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int row = 0;
        int rowBottomBoundary = matrix.length - 1;
        int column = 0;
        int columnRightBoundary = matrix[0].length - 1;

        while (row <= rowBottomBoundary && column <= columnRightBoundary) {
            // Move right
            for (int changingColumn = column; changingColumn <= columnRightBoundary; changingColumn++) {
                list.add(matrix[row][changingColumn]);
            }
            row++;

            // Move down
            for (int changingRow = row; changingRow <= rowBottomBoundary; changingRow++) {
                list.add(matrix[changingRow][columnRightBoundary]);
            }
            columnRightBoundary--;

            // move left
            if (row <= rowBottomBoundary) {
                for (int changingColumn = columnRightBoundary; changingColumn >= column; changingColumn--) {
                    list.add(matrix[rowBottomBoundary][changingColumn]);
                }
            }
            rowBottomBoundary--;

            // move up
            if (column <= columnRightBoundary) {
                for (int changingRow = rowBottomBoundary; changingRow >= row; changingRow--) {
                    list.add(matrix[changingRow][column]);
                }
            }
            column++;
        }

        return list;
    }

    @Test
    void demo() {
        assertArrayEquals(new int[]{1, 2, 3, 6, 9, 8, 7, 4, 5}, spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}).stream().mapToInt(i -> i).toArray());
    }
}

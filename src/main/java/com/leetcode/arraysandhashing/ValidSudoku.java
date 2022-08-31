package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/valid-sudoku/">Valid Sudoku</a>
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        /* Temporary sets for each iteration to check if multiple of the same number exist. */
        Set<Character> rowSet;
        Set<Character> colSet;

        /* Check for rows. */
        for (int row = 0; row < rows; row++) {
            rowSet = new HashSet<>(); // This is reset for each row
            for (int col = 0; col < cols; col++) {
                char cell = board[row][col];
                if (cell == '.') {
                    continue;
                }
                if (rowSet.contains(cell)) {
                    return false;
                }
                rowSet.add(cell);
            }
        }

        /* Check for columns. */
        for (int col = 0; col < cols; col++) {
            colSet = new HashSet<>(); // This is reset for each column
            for (int row = 0; row < rows; row++) {
                char cell = board[row][col];
                if (cell == '.') {
                    continue;
                }
                if (colSet.contains(cell)) {
                    return false;
                }

                colSet.add(cell);
            }
        }


        /* Check each block. */
        /* Increment i by 3 each iteration to check each 3 x 3 sub-box. */
        for (int rowStart = 0; rowStart < rows; rowStart += 3) {
            for (int columnStart = 0; columnStart < cols; columnStart += 3) {
                if (!checkBlock(rowStart, columnStart, board)) {
                    return false;
                }
            }
        }

        return true;

    }

    public boolean checkBlock(int rowStart, int columnStart, char[][] board) {
        Set<Character> blockSet = new HashSet<>(); // This is reset for each 3 x 3 block
        int rowBound = rowStart + 3; // Row boundary for sub-box.
        int columnBound = columnStart + 3;
        /* Check each cell in the 3 x 3 box. */
        for (int row = rowStart; row < rowBound; row++) {
            for (int col = columnStart; col < columnBound; col++) {
                var cell = board[row][col];
                if (cell == '.') {
                    continue;
                }

                if (blockSet.contains(cell)) {
                    return false;
                }

                blockSet.add(cell);
            }
        }

        return true;
    }

    @Test
    void demo() {
        var board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        assertTrue(isValidSudoku(board));
    }
}

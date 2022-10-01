package com.leetcode.graphs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/number-of-islands/">Number of Islands</a>
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    dfs(grid, row, col); // Change all neighbors to 0
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Depth First Search Algorithm: https://www.youtube.com/watch?v=7fujbpJ0LB4
     */
    public void dfs(char[][] grid, int row, int col) {
        if (
                row < 0 || // Check top boundary
                        col < 0 || // Check left boundary
                        row >= grid.length || // Check bottom boundary
                        col >= grid[0].length || // Check right boundary
                        grid[row][col] == '0' // Check if node was already visited
        ) {
            return;
        }
        grid[row][col] = '0'; // Mark node as already visited
        dfs(grid, row + 1, col); // Up
        dfs(grid, row, col + 1); // Right
        dfs(grid, row - 1, col); // Down
        dfs(grid, row, col - 1); // Left
    }

    @Test
    void demo() {
        assertEquals(1, numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));
        assertEquals(3, numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));

    }
}

package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BestTimeToBuyAndSellStockTest {



    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxProfit = 0;
        int windowStart = 0;

        for (int windowEnd = 1; windowEnd < prices.length; windowEnd++) {
            if (prices[windowStart] < prices[windowEnd]) {
                int currentProfit = prices[windowEnd] - prices[windowStart];
                maxProfit = Math.max(maxProfit, currentProfit);

            } else {
                windowStart = windowEnd;
            }
        }

        return maxProfit;
    }

    @Test
    void demo() {
        // Test cases
        int[][] testCases = {
                {7, 1, 5, 3, 6, 4},  // Example 1
                {7, 6, 4, 3, 1},      // Example 2
                {},                   // Empty array
                {1},                  // Single element array
                {1, 2},               // Increasing prices
                {2, 1}                // Decreasing prices
        };

        // Expected outputs
        int[] expected = {5, 0, 0, 0, 1, 0};

        for (int i = 0; i < testCases.length; i++) {
            int result = maxProfit(testCases[i]);
            assertEquals(expected[i], result, "Test case " + (i + 1));
        }
    }
}

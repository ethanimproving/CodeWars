package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductExceptSelfTest {

    public int[] productExceptSelfHard(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Set to 1 because there are no elements before the first element
        answer[0] = 1;

        // Calculate prefix products and store in the answer array
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Calculate postfix products and update the answer array
        int postfixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= postfixProduct;
            postfixProduct *= nums[i];
        }

        return answer;
    }


    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // Initialize arrays to store prefix and suffix products
        int[] prefixProducts = new int[n];
        int[] suffixProducts = new int[n];

        // Set to 1 because there are no elements before the first element
        // Also shifts elements to the right so prefix is at the same index as num
        prefixProducts[0] = 1;

        // Calculate prefix products
        for (int i = 1; i < n; i++) {
            prefixProducts[i] = prefixProducts[i - 1] * nums[i - 1];
        }

        // Calculate suffix products
        suffixProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffixProducts[i] = suffixProducts[i + 1] * nums[i + 1];
        }

        // Calculate answer array by multiplying prefix and suffix products
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = prefixProducts[i] * suffixProducts[i];
        }

        return answer;
    }

    @Test
    public void testProductExceptSelf() {

        // Test case 1
        int[] nums1 = {1, 2, 3, 4};
        int[] expected1 = {24, 12, 8, 6};
        assertArrayEquals(expected1, productExceptSelf(nums1));

        // Test case 2
        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] expected2 = {0, 0, 9, 0, 0};
        assertArrayEquals(expected2, productExceptSelf(nums2));
    }
}

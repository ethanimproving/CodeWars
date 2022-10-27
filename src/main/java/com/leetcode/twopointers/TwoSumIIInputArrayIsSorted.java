package com.leetcode.twopointers;

public class TwoSumIIInputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        int leftPointer = 0;
        int rightPointer = numbers.length - 1;
        int leftValue, rightValue;

        while (leftPointer < rightPointer) {
            leftValue = numbers[leftPointer];
            rightValue = numbers[rightPointer];

            if (leftValue + rightValue == target) break;

            if (leftValue + rightValue < target) {
                leftPointer++;
                continue;
            }

            rightPointer--;
        }

        return new int[]{leftPointer + 1, rightPointer + 1};
    }

}

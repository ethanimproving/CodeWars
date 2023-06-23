package com.leetcode.twopointers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidSubsequence {

    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {

        /* Initialize pointers */
        int sequeceStart = 0;

        /* For each matching number, increment the subsequence
        *  until there are no more elements in the array */
        for (var number : array) {
            if (sequeceStart == sequence.size()) {
                return true;
            }
            if (number.equals(sequence.get(sequeceStart))) {
                sequeceStart++;
            }
        }

        return sequeceStart == sequence.size();
    }

    @Test
    public void TestCase1() {
        var array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        var sequence = Arrays.asList(1, 6, -1, 10);
        assertTrue(ValidSubsequence.isValidSubsequence(array, sequence));
    }

    @Test
    public void TestCase2() {
        var array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        var sequence = Arrays.asList(1, 6, 94, 10);
        assertFalse(ValidSubsequence.isValidSubsequence(array, sequence));
    }

    @Test
    public void TestCase3() {
        var array = Arrays.asList(5, 1, 22, 25, 6, -1, 94, 10, 67, 10);
        var sequence = Arrays.asList(1, 6, 94, 10);
        assertTrue(ValidSubsequence.isValidSubsequence(array, sequence));
    }
}

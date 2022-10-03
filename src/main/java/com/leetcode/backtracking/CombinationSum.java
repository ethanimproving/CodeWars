package com.leetcode.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @see <a href="https://leetcode.com/problems/combination-sum/">Combination Sum</a>
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        var listOfCombinationsThatSumToTarget = new ArrayList<List<Integer>>();
        var currentSetBeingEvaluated = new ArrayList<Integer>();
        backtrack(candidates, target, listOfCombinationsThatSumToTarget, currentSetBeingEvaluated, 0);
        return listOfCombinationsThatSumToTarget;
    }

    public void backtrack(int[] candidates, int target, List<List<Integer>> ans, List<Integer> currentSetBeingEvaluated, int index) {
        if (target == 0) { // Edge case
            ans.add(new ArrayList<>(currentSetBeingEvaluated));
        } else if (target < 0 || index >= candidates.length) {
            return; // If target is less than 0, or if the index is out of bounds, backtrack to the previous node and continue searching.
        } else {
            /* FIRST DECISION IN DECISION TREE. Duplicate candidate. */
            currentSetBeingEvaluated.add(candidates[index]); // Add the current index to set
            backtrack(candidates, target - candidates[index], ans, currentSetBeingEvaluated, index); // Update target minus sum of current set

            /* SECOND DECISION IN DECISION TREE. Remove candidates. */
            currentSetBeingEvaluated.remove(currentSetBeingEvaluated.get(currentSetBeingEvaluated.size() - 1)); // Remove last element that was added to ensure no duplicates on this side of the decision tree
            backtrack(candidates, target, ans, currentSetBeingEvaluated, index + 1); // Shift index to next number in candidates
        }
    }

    @Test
    void demo() {
        assertIterableEquals(of(of(2, 2, 3), of(7)), combinationSum(new int[]{2, 3, 6, 7}, 7));
        assertIterableEquals(of(of(2, 2, 2, 2), of(2, 3, 3), of(3, 5)), combinationSum(new int[]{2, 3, 5}, 8));
    }
}

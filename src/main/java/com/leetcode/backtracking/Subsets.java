package com.leetcode.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @see <a href="https://leetcode.com/problems/subsets/">Subsets</a>
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        var ans = new ArrayList<List<Integer>>();
        var subset = new ArrayList<Integer>();
        helper(ans, 0, nums, subset);
        return ans;
    }

    public void helper(List<List<Integer>> ans, int callStack, int[] nums, List<Integer> subset) {
        int callStackBoundary = nums.length;
        if (callStack >= callStackBoundary) {
            /* Add subset to result. Then eliminate the branch and go back to the level before. */
            ans.add(new ArrayList<>(subset));
        } else {
            /* Add the element and start the recursive call.
             * All of these will be called nums.length times before ever reaching
             * the backtracking calls, adding all the elements one at a time.
             * Once it reaches the top of the call stack, it will add the subset,
             * remove the last element, and add all the following backtracking subsets. */
            subset.add(nums[callStack]); // Add the index of whichever call stack you are on.
            helper(ans, callStack + 1, nums, subset);
            /* Remove the last element and do the backtracking call. */
            subset.remove(subset.size() - 1);
            helper(ans, callStack + 1, nums, subset);
        }
    }

    @Test
    void demo() {
        var expected = List.of(
                List.of(1, 2, 3), // First it does the recursive calls 3 times until it's at the top of the call stack and adds all 3 elements.
                List.of(1, 2), // Then it takes away 3
                List.of(1, 3), // Then it takes away 2 and adds 3
                List.of(1), // Then it takes away 2
                List.of(2, 3), // Then it takes away 1 and adds 2 and 3
                List.of(2), // Then it takes away 3
                List.of(3), // Then it takes away 2 and adds 3
                List.of() // Then it takes away 3
        );
        assertIterableEquals(expected, subsets(new int[]{1, 2, 3}));
    }
}

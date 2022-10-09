package com.leetcode.tree;

import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleEntry;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/balanced-binary-tree/">Balanced Binary Tree</a>
 */
public class BalancedBinaryTree {

    public static boolean isBalanced(TreeNode root) {
        return dfs(root).getKey();
    }

    /**
     * @return Boolean indicating if the left and right side of the node is balanced,
     * and a height indicating the maximum number of child nodes on the left or right.
     */
    private static SimpleEntry<Boolean, Integer> dfs(TreeNode root) {
        if (root == null) {
            return new SimpleEntry<>(true, 0);
        }

        var left = dfs(root.left);
        var right = dfs(root.right);

        var balanced =
                left.getKey() &&
                        right.getKey() &&
                        (Math.abs(left.getValue() - right.getValue()) <= 1);

        return new SimpleEntry<>(
                balanced,
                1 + Math.max(left.getValue(), right.getValue())
        );
    }

    @Test
    void demo() {
        var one = new TreeNode(1);
        var two = new TreeNode(2);
        var three = new TreeNode(3);
        var four = new TreeNode(4);
        var five = new TreeNode(5);
        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;
        assertTrue(isBalanced(one));
    }
}

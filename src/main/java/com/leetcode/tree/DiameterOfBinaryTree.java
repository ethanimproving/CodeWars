package com.leetcode.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/diameter-of-binary-tree/">Diameter of Binary Tree</a>
 */
public class DiameterOfBinaryTree {

    int diameter = -1;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode current) {
        if (current == null) {
            return -1; // To offset the 1 + dfs back to 0 if no path is found
        }
        int left = 1 + dfs(current.left);
        int right = 1 + dfs(current.right);
        diameter = Math.max(diameter, (left + right));
        return Math.max(left, right); // So that the previous node only gets the maximum right or left path explored, not the full path explored
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
        two.left = four;
        two.right = five;
        assertEquals(3, diameterOfBinaryTree(one));
    }
}

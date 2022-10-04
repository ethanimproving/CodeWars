package com.leetcode.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        var node = new TreeNode(root.val);
        node.left = invertTree(root.right);
        node.right = invertTree(root.left);
        return node;
    }

    @Test
    void demo() {
        var expected = new TreeNode(5);
        var result = new TreeNode(5);
        var right = new TreeNode(1);
        var left = new TreeNode(2);
        expected.left = left;
        expected.right = right;
        result.left = right;
        result.right = left;
        assertEquals(expected, invertTree(result));
    }
}

package com.leetcode.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">Maximum Depth of Binary Tree</a>
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right)); // Furthest node returns 1 + Max(0,0) to previous node, which then returns 1 + Max(0,1) to previous node, which returns 1 + Max(0,2) = 3
    }


    @Test
    void demo() {
        // 3,9,20,null,null,15,7
        var seven = new TreeNode(7);
        var fifteen = new TreeNode(15);
        var twenty = new TreeNode(20);
        var nine = new TreeNode(9);
        var three = new TreeNode(3);
        twenty.left = fifteen;
        twenty.right = seven;
        three.left = nine;
        three.right = twenty;
        assertEquals(3, maxDepth(three));
        var one = new TreeNode(1);
        var two = new TreeNode(2);
        one.left = two;
        assertEquals(2, maxDepth(one));
    }
}

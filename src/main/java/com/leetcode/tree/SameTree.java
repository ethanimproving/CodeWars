package com.leetcode.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/same-tree/">Same Tree</a>
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);

        return left && right;
    }

    @Test
    void demo() {
        var one = new TreeNode(1);
        var two = new TreeNode(2);
        var three = new TreeNode(3);
        var one2 = new TreeNode(1);
        var two2 = new TreeNode(2);
        var three2 = new TreeNode(3);
        one.left = two;
        one.right = three;
        one2.left = two2;
        one2.right = three2;
        assertTrue(isSameTree(one, one2));
        assertFalse(isSameTree(new TreeNode(1), new TreeNode(null)));
    }
}

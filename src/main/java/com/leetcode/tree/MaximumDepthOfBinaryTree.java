package com.leetcode.tree;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">Maximum Depth of Binary Tree</a>
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right)); // Furthest node returns 1 + Max(0,0) to previous node, which then returns 1 + Max(0,1) to previous node, which returns 1 + Max(0,2) = 3
    }

    public int iterativeBfs(TreeNode root) {
        int depth = 0;
        if (root == null) return depth;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int parents = queue.size();
            depth++;
            while (parents > 0) {
                TreeNode temp = queue.poll();
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
                parents--;
            }
        }
        return depth;
    }

    public int iterativeDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depth = new Stack<>();
        stack.push(root);
        depth.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int currentDepth = depth.pop();
            max = Math.max(currentDepth, max);
            if (node.left != null) {
                stack.push(node.left);
                depth.push(currentDepth + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                depth.push(currentDepth + 1);
            }
        }
        return max;
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

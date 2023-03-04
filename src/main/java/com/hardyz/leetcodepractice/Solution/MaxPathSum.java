package com.hardyz.leetcodepractice.Solution;

import com.hardyz.leetcodepractice.entity.TreeNode;

public class MaxPathSum {
    static int max;
    public static int maxPathSum(TreeNode root) {
        max = root.val;
        maxPathThroughRoot(root);
        return max;
    }
    public static int maxPathThroughRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftPath = maxPathThroughRoot(root.left);
        int rightPath = maxPathThroughRoot(root.right);
        int sum = root.val;
        int sumMax = sum;
        if (leftPath > 0) {
            sum += leftPath;
        }
        if (rightPath > 0) {
            sum += rightPath;
        }
        if (leftPath > 0 && rightPath > 0) {
            sumMax = root.val + Math.max(leftPath, rightPath);
        } else {
            sumMax = sum;
        }
        max = Math.max(max, sum);
        return sumMax;
    }
}

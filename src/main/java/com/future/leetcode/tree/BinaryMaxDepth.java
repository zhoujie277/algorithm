package com.future.leetcode.tree;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明:叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * . 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度3 。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xoh1zg/
 */
@SuppressWarnings("all")
public class BinaryMaxDepth {

    public int maxDepthTopDown(TreeNode root) {
        return maxDepthTopDown(root, 0);
    }

    public int maxDepthTopDown(TreeNode root, int depth) {
        if (root == null) return depth;
        int left = maxDepthTopDown(root.left, depth + 1);
        int right = maxDepthTopDown(root.right, depth + 1);
        return Math.max(left, right);
    }

    /**
     * 自底向上的递归
     */
    public int maxDepthBottomUp(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepthBottomUp(root.left);
        int right = maxDepthBottomUp(root.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        TreeNode left = new TreeNode();
        TreeNode right = new TreeNode();
        root.left = left;
        root.right = right;
        BinaryMaxDepth binaryMaxDepth = new BinaryMaxDepth();
        System.out.println(binaryMaxDepth.maxDepthBottomUp(root));
    }
}

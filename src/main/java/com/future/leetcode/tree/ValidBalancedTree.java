package com.future.leetcode.tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class ValidBalancedTree {

    /**
     * 自底向上的递归
     * 时间复杂度：O(n)
     * 空间复杂度：
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return false;
        return getHeight(root) != -1;
    }

    public int getHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        ValidBalancedTree tree = new ValidBalancedTree();
//        tree.isBalanced(root);
    }
}

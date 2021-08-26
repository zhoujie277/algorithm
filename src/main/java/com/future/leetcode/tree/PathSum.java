package com.future.leetcode.tree;

/**
 * 路径总会
 * <p>
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和targetSum 。
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xo566j/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && targetSum == root.val) return true;
        if (!hasPathSum(root.left, targetSum - root.val)) {
            return hasPathSum(root.right, targetSum - root.val);
        }
        return true;
    }
}

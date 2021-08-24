package com.future.leetcode.tree;

/**
 * 最近的二叉搜索树值
 * 给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的数值。
 * <p>
 * 注意：
 * 给定的目标值 target 是一个浮点数
 * 题目保证在该二叉搜索树中只会存在一个最接近目标值的数
 * 示例：
 * <p>
 * 输入: root = [4,2,5,1,3]，目标值 target = 3.714286
 * <p>
 * *     4
 * *    / \
 * *   2   5
 * *  / \
 * * 1   3
 * <p>
 * 输出: 4
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xedyk5/
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class ClosestValueInBST {
    public int closestValue(TreeNode root, double target) {
        if (root == null) return -1;
        int closest = root.val;
        TreeNode node = root;
        while (node != null) {
            if (Math.abs(target - node.val) < Math.abs(closest - target)) {
                closest = node.val;
            }
            node = target > node.val ? node.right : node.left;
        }
        return closest;
    }

    public static void main(String[] args) {

    }
}

package com.future.leetcode.tree;

/**
 * 将有序数组转换为BST
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * 链接：https://leetcode-cn.com/leetbook/read/introduction-to-data-structure-binary-search-tree/xm5go5/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class SortedArrayToBST {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(logn)
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, l, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, r);
        return root;
    }

    public static void main(String[] args) {
        //输入：nums = [-10,-3,0,5,9]
        //输出：[0,-3,9,-10,null,5]
        //解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        System.out.println(sortedArrayToBST.sortedArrayToBST(nums));
    }
}

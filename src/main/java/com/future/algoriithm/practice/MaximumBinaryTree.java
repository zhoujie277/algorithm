package com.future.algoriithm.practice;

import com.future.utils.PrintUtils;

import java.util.LinkedList;

/**
 * 最大二叉树
 * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 * <p>
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 * <p>
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 * *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 * *         - 空数组，无子节点。
 * *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 * *             - 空数组，无子节点。
 * *             - 只有一个元素，所以子节点是一个值为 1 的节点。
 * *    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 * *       - 只有一个元素，所以子节点是一个值为 0 的节点。
 * *      - 空数组，无子节点。
 * *
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 *
 * @author jayzhou
 */
public class MaximumBinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 时间复杂度：最坏情况下：O(n2)。construct方法一共被调用n次。每次递归寻找根结点时，需要遍历当前范围内所有元素找出最大值。
     * 一般情况下，每次遍历的复杂度为O(logN), 总复杂度为O(NlogN)，最坏情况下，数组nums有序，总的复杂度为O(n2).
     * 空间复杂度：O(n)。最坏情况下：递归调用深度为n，平均情况下，长度为n的数组递归调用深度为n(logN)
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return construct(nums, 0, nums.length);
    }

    private TreeNode construct(int[] nums, int l, int r) {
        if (l == r) return null;
        int max = l;
        for (int i = l + 1; i < r; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        TreeNode node = new TreeNode(nums[max]);
        node.left = construct(nums, l, max);
        node.right = construct(nums, max + 1, r);
        return node;
    }

    /**
     * 题目变种
     * 返回一个数组，数组里面存着每个节点的父节点的索引(如果没有父节点，就存-1)
     * 示例：
     * 输入：{3, 2, 1, 6, 0, 5}
     * 输出：{3, 0, 1, -1, 5, 3}
     * 解法：单调栈，寻找左右两边第一个比它大的值，最后左右值两者取较小值便是它父结点。
     */
    public int[] parentIndexes(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] left = new int[nums.length]; //左边第一个比nums[i]大的值
        int[] right = new int[nums.length]; //右边第一个比nums[j]大的值
        for (int i = 0; i < nums.length; i++) {
            left[i] = -1;
            right[i] = -1;
        }
        // Java的Stack不好用，用LinkedList代替使用
        LinkedList<Integer> stack = new LinkedList<>();
        Integer peek;
        for (int i = 0; i < nums.length; i++) {
            while ((peek = stack.peek()) != null && nums[i] > nums[peek]) {
                stack.pop();
                right[peek] = i;
            }
            if (peek != null && nums[peek] > nums[i]) {
                left[i] = peek;
            }
            stack.push(i);
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (left[i] >= 0 && right[i] >= 0) {
                result[i] = Math.min(nums[left[i]], nums[right[i]]);
            } else if (left[i] >= 0) {
                result[i] = left[i];
            } else if (right[i] >= 0) {
                result[i] = right[i];
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        TreeNode treeNode = new MaximumBinaryTree().constructMaximumBinaryTree(nums);
//        System.out.println(treeNode);
        int[] construct = new MaximumBinaryTree().parentIndexes(nums);
        PrintUtils.println(construct);
    }
}

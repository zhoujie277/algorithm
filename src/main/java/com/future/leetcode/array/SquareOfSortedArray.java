package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class SquareOfSortedArray {

    /**
     * 从两个有序数组的尾部合并两个有序数组
     */
    public int[] sortedSquares(int[] nums) {
        int[] array = new int[nums.length];
        int index = array.length - 1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int lv = nums[left] * nums[left];
            int rv = nums[right] * nums[right];
            if (lv > rv) {
                array[index--] = lv;
                left++;
            } else {
                array[index--] = rv;
                right--;
            }
        }
        return array;
    }

    /**
     * 合并两个有序数组的变种题
     */
    public int[] sortedSquaresMerge(int[] nums) {
        int negPoint = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negPoint = i;
            }
            nums[i] = nums[i] * nums[i];
        }
        if (negPoint < 0) return nums;
        int[] array = new int[nums.length];
        int left = negPoint, right = negPoint + 1;
        int index = 0;
        while (left >= 0 && right < nums.length) {
            array[index++] = nums[left] > nums[right] ? nums[right++] : nums[left--];
        }
        while (left >= 0) array[index++] = nums[left--];
        while (right < nums.length) array[index++] = nums[right++];
        return array;
    }

    public static void main(String[] args) {
        //输入：nums = [-4,-1,0,3,10]
        //输出：[0,1,9,16,100]
        //解释：平方后，数组变为 [16,1,0,9,100]
        //排序后，数组变为 [0,1,9,16,100]
        SquareOfSortedArray array = new SquareOfSortedArray();
        int[] nums = new int[]{-4, -1, 0, 3, 10};
//        System.out.println(Arrays.toString(array.sortedSquares(nums)));
        int[] nums1 = new int[]{-5, -3, -2, -1};
        System.out.println(Arrays.toString(array.sortedSquares(nums1)));
    }
}

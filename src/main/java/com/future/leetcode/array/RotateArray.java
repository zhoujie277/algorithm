package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 旋转数组
 * <p>
 * 给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。
 * <p>
 * 进阶：
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为O(1) 的原地算法解决这个问题吗？
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * <p>
 * 链接：https://leetcode-cn.com/problems/rotate-array
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class RotateArray {

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public void copy(int[] nums, int k) {
        int[] p = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            p[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < p.length; i++) {
            nums[i] = p[i];
        }
    }

    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] nums2 = new int[]{-1, -100, 3, 99};
        int[] nums3 = new int[]{1, 2};
//        rotateArray.copy(nums, 3);
        rotateArray.rotate(nums2, 2);
        rotateArray.rotate(nums, 3);
        rotateArray.rotate(nums3, 3);
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums3));
    }
}

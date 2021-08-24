package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 颜色分类
 * <p>
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，
 * 并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * 进阶：
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x9wv2h/
 *
 * @author jayzhou
 */
public class ColorSorting {

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public void sortColors(int[] nums) {
        int zero = 0, left = 0, right = nums.length - 1;
        int pivot = 1;
        while (true) {
            for (; left <= right && nums[left] <= pivot; left++) {
                if (nums[left] < pivot) {
                    swap(nums, left, zero++);
                }
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left > right) break;
            swap(nums, left, right--);
        }
    }

    public static void main(String[] args) {
        ColorSorting colorSorting = new ColorSorting();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        colorSorting.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}

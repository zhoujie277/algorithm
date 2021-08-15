package com.future.algoriithm.practice;

import com.future.utils.PrintUtils;

/**
 * 合并两个有序数组
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化nums1和nums2的元素数量分别为m和n 。你可以假设nums1的空间大小等于m + n，这样它就有足够的空间保存来自nums2的元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 *
 * @author jayzhou
 */
public class MergeSortedArray {

    /**
     * 时间复杂度：O(m+n)
     * 空间复杂度: O(1)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int li = m - 1;
        int ri = n - 1;
        while (li >= 0 && ri >= 0) {
            nums1[index--] = (nums1[li] < nums2[ri]) ? nums2[ri--] : nums1[li--];
        }

        while (ri >= 0) {
            nums1[index--] = nums2[ri--];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
//        int[] nums1 = new int[]{ 2, 0};
//        int[] nums2 = new int[]{1};
        new MergeSortedArray().merge(nums1, 3, nums2, 3);
        PrintUtils.print(nums1);
    }
}

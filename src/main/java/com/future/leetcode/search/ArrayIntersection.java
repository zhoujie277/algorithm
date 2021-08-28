package com.future.leetcode.search;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class ArrayIntersection {

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    private void quicksort(int[] nums, int left, int right) {
        if (left >= right) return;
        int l = left, r = right - 1;
        while (true) {
            while (l <= r && nums[l] <= nums[right]) {
                l++;
            }
            while (l <= r && nums[r] > nums[right]) {
                r--;
            }
            if (l > r) break;
            swap(nums, l, r);
        }
        swap(nums, right, l);
        quicksort(nums, left, l - 1);
        quicksort(nums, l + 1, right);
    }

    /**
     * 这个算法实际测试比下面的排序+二叉查找优秀
     * 时间复杂度：MLogM + NLogN
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return nums1;
        quicksort(nums1, 0, nums1.length - 1);
        quicksort(nums2, 0, nums2.length - 1);
        int lastElement = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int len = Math.min(nums1.length, nums2.length);
        int[] result = new int[len];
        int index = 0;
        while (i < nums1.length && j < nums2.length) {
            while (i < nums1.length && nums1[i] < nums2[j]) {
                i++;
            }
            if (i >= nums1.length) break;
            while (j < nums2.length && nums2[j] < nums1[i]) {
                j++;
            }
            if (j >= nums2.length) break;
            if (nums1[i] == nums2[j]) {
                if (lastElement != nums1[i]) {
                    lastElement = nums1[i];
                    result[index++] = lastElement;
                }
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }

    /**
     * 时间复杂度：MLogM + NLogM
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return nums1;
        HashSet<Integer> hashSet = new HashSet<>();
        quicksort(nums1, 0, nums1.length - 1);
        for (int target : nums2) {
            int left = 0, right = nums1.length - 1;
            do {
                int mid = (left + right) >>> 1;
                if (target == nums1[mid]) {
                    hashSet.add(target);
                    break;
                } else if (target > nums1[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } while (left <= right);
        }
        int[] result = new int[hashSet.size()];
        int index = 0;
        for (Integer integer : hashSet) {
            result[index++] = integer;
        }
        return result;
    }

    public int[] brute(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return nums1;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int k : nums1) {
            for (int i : nums2) {
                if (k == i) {
                    hashSet.add(k);
                }
            }
        }
        int[] result = new int[hashSet.size()];
        int index = 0;
        for (Integer integer : hashSet) {
            result[index++] = integer;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] num1 = new int[]{8, 0, 3};
        int[] num2 = new int[]{0};
        ArrayIntersection intersection = new ArrayIntersection();
        System.out.println(Arrays.toString(intersection.intersection(num1, num2)));
    }
}

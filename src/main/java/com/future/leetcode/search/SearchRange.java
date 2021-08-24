package com.future.leetcode.search;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置。
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * 进阶：
 * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9<= nums[i]<= 10^9
 * nums是一个非递减数组
 * -10^9<= target<= 10^9
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xenp13/
 *
 * @author jayzhou
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        if (nums.length == 1 && nums[0] == target) return new int[]{0, 0};
        int[] result = new int[2];
        int left = 0, right = nums.length - 1;
        boolean find = false;
        do {
            int mid = (left + right) >>> 1;
            if (target >= nums[mid]) {
                if (target == nums[mid]) {
                    find = true;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left <= right);
        result[1] = find ? right : -1;
        left = 0;
        right = nums.length - 1;
        do {
            int mid = (left + right) >>> 1;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left <= right);
        result[0] = find ? left : -1;
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        SearchRange searchRange = new SearchRange();
        System.out.println(Arrays.toString(searchRange.searchRange(nums, 6)));
    }
}

package com.future.leetcode.search;

/**
 * 搜索旋转排序数组
 * <p>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1.
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xeog5j/
 *
 * @author jayzhou
 */
public class FindRotateSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        if (nums[0] == target) return 0;
        int left = 0, right = nums.length - 1;
        do {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) return mid;
            if (nums[left] > nums[mid]) {
                // (mid - nums.length) is sorted.
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        } while (left <= right);
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int first = nums[0];
        if (target == first) return 0;
        if (nums.length == 1) return -1;
        int left = 1, right = nums.length - 1;
        do {
            int mid = (left + right) >>> 1;
            if (nums[mid] > first) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left <= right);
        int minIndex = (left == nums.length) ? 0 : left;
        if (minIndex == 0) {
            left = 0;
            right = nums.length - 1;
        } else if (target < first) {
            left = minIndex;
            right = nums.length - 1;
        } else {
            left = 0;
            right = minIndex - 1;
        }
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) return mid;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
//        System.out.println("left=" + left + ", right=" + right);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
//        int[] nums = new int[]{1, 3};
        FindRotateSortedArray find = new FindRotateSortedArray();
        System.out.println(find.search(nums, 7));
    }
}

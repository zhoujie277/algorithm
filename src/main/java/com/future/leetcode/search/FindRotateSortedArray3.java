package com.future.leetcode.search;

/**
 * 搜索旋转排序数组2
 * <p>
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你旋转后的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * <p>
 * 示例1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 * 进阶：
 * 这是 搜索旋转排序数组的延伸题目，本题中的nums 可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 * @author jayzhou
 */
public class FindRotateSortedArray3 {

    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        do {
            int mid = (left + right) >>> 1;
            if (target == nums[mid]) return true;
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        } while (left <= right);
        return false;
    }

    public static void main(String[] args) {
        //[1,1,1,1,1,1,1,1,1,13,1,1,1,1,1,1,1,1,1,1,1,1]
        int[] nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 13, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] nums1 = new int[]{1, 1, 1, 1, 13};
        int[] nums2 = new int[]{3, 1};
        int[] nums3 = new int[]{0, 1, 1};
        int[] nums4 = new int[]{5, 1, 3};
        int[] nums5 = new int[]{3, 1, 2, 2, 2};
        FindRotateSortedArray3 sortedArray3 = new FindRotateSortedArray3();
//        System.out.println(sortedArray3.search(nums, 13));
//        System.out.println(sortedArray3.search(nums1, 13));
//        System.out.println(sortedArray3.search(nums2, 1));
//        System.out.println(sortedArray3.search(nums3, 0));
//        System.out.println(sortedArray3.search(nums4, 3));
        System.out.println(sortedArray3.search(nums5, 1));
    }
}

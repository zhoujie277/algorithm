package com.future.leetcode.array;

/**
 * 长度最小的子数组
 * <p>
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 提示：
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * <p>
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x9gogt/
 *
 * @author jayzhou
 */
public class LongestSubArray {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return 1;
        }
        int minLen = Integer.MAX_VALUE;
        int sum = nums[0];
        for (int slow = 0, fast = 1; fast < nums.length; fast++) {
            sum += nums[fast];
            while (sum >= target) {
                minLen = Math.min(minLen, fast - slow + 1);
                sum -= nums[slow++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int[] nums1 = new int[]{1, 4, 4};
        int[] nums2 = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        int[] nums3 = new int[]{1, 2, 3, 4, 5};
        LongestSubArray subArray = new LongestSubArray();
        System.out.println(subArray.minSubArrayLen(7, nums));
        System.out.println(subArray.minSubArrayLen(4, nums1));
        System.out.println(subArray.minSubArrayLen(11, nums2));
        System.out.println(subArray.minSubArrayLen(15, nums3));
    }
}

package com.future.leetcode.array;

/**
 * 长度最小的子数组
 * <p>
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/c0w4r/
 *
 * @author jayzhou
 */
public class ShortestSubArray {

//    /**
//     * 前缀和 + 二分查找方法
//     */
//    public int minSubArrayLenBinarySearch(int target, int[] nums) {
//        if (nums == null || nums.length == 0) return 0;
//        int[] prefixSums = new int[nums.length];
//        prefixSums[0] = nums[0];
//        int min = 0x3f3f3f3f;
//        for (int i = 1; i < nums.length; i++) {
//            prefixSums[i] = prefixSums[i - 1] + nums[i];
//        }
//        // todo: 该算法有问题
//        System.out.println(Arrays.toString(prefixSums));
//        for (int i = 0; i < prefixSums.length; i++) {
//            int s = target + prefixSums[i];
//            int left = 0, right = i;
//            do {
//                int mid = (left + right) >>> 1;
//                if (s <= prefixSums[mid]) {
//                    right = mid - 1;
//                } else {
//                    left = mid + 1;
//                }
//            } while (left <= right);
//            min = Math.min(left - i, min);
//        }
//        if (min >= 0x3f3f3f3f) return 0;
//        return min;
//    }

    /**
     * 双指针解法。
     * 如下方式使用，也叫做滑动窗口解法
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int fast = 0, slow = 0; fast < nums.length; fast++) {
            sum += nums[fast];
            while (sum >= target) {
                min = Math.min(min, fast - slow + 1);
                sum -= nums[slow++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
//        int[] ints = new int[]{2, 3, 1, 2, 4, 3};
        int[] ints = new int[]{10, 5, 13, 4, 8, 4, 5, 11, 14, 9, 16, 10, 20, 8};
        ShortestSubArray subArray = new ShortestSubArray();
        System.out.println(subArray.minSubArrayLen(80, ints));
//        System.out.println(subArray.minSubArrayLenBinarySearch(80, ints));
    }
}

package com.future.leetcode.array;

/**
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 * 说明：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/c3ki5/
 *
 * @author jayzhou
 */
public class FindMinFromRotateArray {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int target = nums[0];
        int left = 1, right = nums.length - 1;
        do {
            int mid = (left + right) >>> 1;
//            System.out.println("left=" + left + ", right=" + right);
            if (target > nums[mid]) {
                // 左边查找。跟平时二叉查找相反，查找最小值
                right = mid - 1;
            } else {
                // 往右边找
                left = mid + 1;
            }
        } while (left <= right);
        // 找到了最右边，说明位置0是最小值。
        return left == nums.length ? nums[0] : nums[left];
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 4, 5, 1, 2};
//        int[] nums = new int[]{1, 2, 3, 4, 5};
        int[] nums = new int[]{5, 1, 2, 3, 4};
        FindMinFromRotateArray array = new FindMinFromRotateArray();
        System.out.println(array.findMin(nums));
    }
}

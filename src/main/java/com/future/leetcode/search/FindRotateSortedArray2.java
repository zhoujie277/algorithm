package com.future.leetcode.search;

/**
 * 搜索旋转排序数组2
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
 * 请你找出并返回数组中的 最小元素 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * <p>
 * 进阶：
 * <p>
 * 这道题是寻找旋转排序数组中的最小值的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xetw7g/
 *
 * @author jayzhou
 */
public class FindRotateSortedArray2 {

    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.min(nums[0], nums[1]);
        int left = 0, right = nums.length - 1;
        do {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[right]) {
                // The midValue must be to the left of th minvalue
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // The midValue must be to the right of the minvalue
                // The mid must be keep in search range,
                // but the binary search structure still is left closed and right closed.
                right = mid;
            } else {
                // nums[mid] == nums[right],此时不能判断左右区间哪个部分该舍去。但是可以去掉重复值。
                right -= 1;
            }
        } while (left <= right);
        return nums[left];
    }

    /**
     * 最坏时间复杂度：当数组中所有元素都是重复的时候，O(N)
     * 平均时间复杂度：O(logN)
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.min(nums[0], nums[1]);
        int first = nums[0];
        int left = 1, right = nums.length - 1;
        while (left < nums.length && nums[left] == first) left++;
        while (right >= 0 && nums[right] == first) right--;
        if (left > right) return first;
        if (left == right) return Math.min(first, nums[left]);
        do {
            int mid = (left + right) >>> 1;
            if (first > nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } while (left <= right);
        return left == nums.length ? nums[0] : nums[left];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 3};
        FindRotateSortedArray2 array = new FindRotateSortedArray2();
        System.out.println(array.findMin(nums));
        System.out.println(array.findMin2(nums));
    }
}

package com.future.leetcode.introduction;

/**
 * 学习计划：算法入门
 *
 * @author jayzhou
 */
public class Introduction {

    /**
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，
     * 如果目标值存在返回下标，否则返回 -1。
     * <p>
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * <p>
     * 链接：https://leetcode-cn.com/problems/binary-search
     */
    public int search(int[] nums, int target) {
        // 左闭右开
        int l = 0, r = nums.length;
        do {
            int mid = (l + r) >>> 1;
            if (target == nums[mid]) return mid;
            if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        } while (l < r);
        return -1;
    }

    /**
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * <p>
     * 你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 5, bad = 4
     * 输出：4
     * 解释：
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5)-> true
     * 调用 isBadVersion(4)-> true
     * 所以，4 是第一个错误的版本。
     * 示例 2：
     * <p>
     * 输入：n = 1, bad = 1
     * 输出：1
     * <p>
     * 链接：https://leetcode-cn.com/problems/first-bad-version
     */
    public int firstBadVersion(int n) {
        // 左闭右闭
        int start = 1, end = n;
        do {
            int mid = (start + end) >>> 1;
            if (isBadVersion(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        } while (start <= end);
        return start;
    }

    public boolean isBadVersion(int v) {
        if (v > 3) return true;
        return false;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * <p>
     * 示例 1:
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 示例2:
     * <p>
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     * 示例 3:
     * <p>
     * 输入: nums = [1], target = 0
     * 输出: 0
     * <p>
     * 提示:
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 为无重复元素的升序排列数组
     * -104 <= target <= 104
     * <p>
     * 链接：https://leetcode-cn.com/problems/search-insert-position
     */
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        do {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        } while (l <= r);
        return l;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        Introduction introduction = new Introduction();
        System.out.println(introduction.search(nums, 9));
        int[] nums2 = new int[]{1, 3, 5, 6};
        System.out.println(introduction.searchInsert(nums2, 5));
    }
}

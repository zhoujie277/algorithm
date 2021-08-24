package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 移除元素
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。
 * 例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cwuyj/
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class RemoveElement {

    /**
     * 双指针同时移动的算法演示
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (true) {
            while (left <= right && nums[left] != val) {
                left++;
            }
            while (left <= right && nums[right] == val) {
                right--;
            }
            if (left > right) break;
            // 左指针停下来的是要删除的元素，右指针停下来的是要向前移动的元素
            nums[left++] = nums[right--];
        }
        return left;
    }

    /**
     * 左右双指针一端移动的算法
     */
    public int removeElement1(int[] nums, int val) {
        // 作闭右开，确保右边指针指向的元素是已经遍历过的元素
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[--right];
            } else {
                left++;
            }
        }
        return left;
    }

    /**
     * 快慢指针的算法演示
     */
    public int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        RemoveElement instance = new RemoveElement();
        int len = instance.removeElement2(nums, 3);
//        int i = instance.removeElement(nums, 3);
        System.out.println(Arrays.toString(nums));
        System.out.println("len = " + len);
    }
}

package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 删除数组元素2
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -10^4 <= nums[i] <= 10^4
 * nums 已按升序排列
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x9nivs/
 *
 * @author jayzhou
 */
class RemoveElements2 {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length <= 2) return nums.length;
        int left = 0;
        boolean repeat = false;
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] != nums[left]) {
                nums[++left] = nums[right];
                repeat = false;
            } else if (!repeat) {
                repeat = true;
                nums[++left] = nums[right];
            }
        }
        return left + 1;
    }

    public static void main(String[] args) {
        RemoveElements2 elements = new RemoveElements2();
        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
//        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 6, 7};
//        int[] nums = new int[]{1, 2, 2};
//        int[] nums = new int[]{1, 1, 1, 1, 2, 2, 3};
        System.out.println(elements.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}

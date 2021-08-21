package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/c6u02/
 *
 * @author jayzhou
 */
class MoveZeros {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                if (slow != fast) {
                    nums[slow] = nums[fast];
                    nums[fast] = 0;
                }
                slow++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 3, 12};
        MoveZeros zeros = new MoveZeros();
        zeros.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}

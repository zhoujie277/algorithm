package com.future.algoriithm.practice;

import java.util.Arrays;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 *
 * @author jayzhou
 */
public class MoveZeros {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            if (i != j) {
                nums[j] = nums[i];
                nums[i] = 0;
            }
            j++;
        }
    }

    /**
     * 性能低。比较次数太多。
     */
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int i = 0, j, len = nums.length;
        while (true) {
            while (i < len && nums[i] != 0) i++;
            j = i;
            while (j < len && nums[j] == 0) j++;
            if (i >= j || j >= len) return;
            nums[i] = nums[j];
            nums[j] = 0;
        }
    }

    /**
     * 该算法没有保证相邻元素的相对顺序，不稳定排序
     */
    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int first = 0, last = nums.length - 1;
        do {
            while (first < last && nums[first] != 0) {
                first++;
            }
            while (first < last && nums[last] == 0) {
                last--;
            }
            if (first >= last) break;
            int tmp = nums[first];
            nums[first] = nums[last];
            nums[last] = tmp;
        } while (true);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        new MoveZeros().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}

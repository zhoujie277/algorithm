package com.future.algoriithm.practice;

import com.future.utils.PrintUtils;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共n个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 * @author jayzhou
 */
public class ColourSorting {

    /**
     * 代码简洁，但某种情况下交换次数多。
     */
    public void sortColors(int[] nums) {
        int pivot = 1;
        int li = 0, ri = nums.length - 1;
        for (int i = 0; i <= ri; i++) {
            while (i < ri && nums[i] > pivot) {
                swap(nums, i, ri--);
            }
            if (nums[i] < pivot) {
                swap(nums, li++, i);
            }
        }
    }

    public void sortColors2(int[] nums) {
        int li = 0, ri = nums.length - 1, i = 0;
        int pivot = 1;
        while (i < ri) {
            while (li <= ri && nums[li] < pivot) {
                li++;
            }
            i = li; // 这里需要拉回来，以保证区间[li,i)全部大于li
            while (i <= ri && nums[i] == pivot) {
                i++;
            }
            while (i <= ri && nums[ri] > pivot) {
                ri--;
            }
            if (i > ri) break;
            // nums[i] > pivot, nums[ri] <= pivot
            if (nums[ri] == pivot) {
                swap(nums, i, ri);
            } else {
                //num[ri] < pivot
                swap(nums, li++, ri);
            }
        }
    }

    public void sortColors1(int[] nums) {
        int li = 0, ri = nums.length - 1, i = 0;
        int pivot = 1;
        while (i < ri) {
            // i > li 则说明 nums[li] == pivot
            i = li;
            while (i <= ri && nums[i] <= pivot) {
                if (nums[i] < pivot) {
                    swap(nums, li++, i);
                }
                i++;
            }

            // nums[i] > pivot
            while (i <= ri && nums[ri] > pivot) {
                ri--;
            }
            if (i > ri) break;
            // nums[i] > pivot, nums[ri] <= pivot
            if (nums[ri] == pivot) {
                swap(nums, i, ri);
            } else {
                //num[ri] < pivot
                swap(nums, li++, ri);
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        if (l == r) return;
        nums[l] = nums[l] ^ nums[r];
        nums[r] = nums[l] ^ nums[r];
        nums[l] = nums[l] ^ nums[r];
    }

    public static void main(String[] args) {

//        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
//        int[] nums = new int[]{2, 0, 1};
        int[] nums = new int[]{1, 0, 1};
//        int[] nums = new int[]{1, 2, 0, 0};
        new ColourSorting().sortColors(nums);
        PrintUtils.println(nums);
    }
}

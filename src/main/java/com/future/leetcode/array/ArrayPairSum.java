package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 数组拆分
 * 给定长度为2n的整数数组 nums ，你的任务是将这些数分成n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
 * 使得从 1 到 n 的 min(ai, bi) 总和最大。
 * <p>
 * 返回该 最大总和 。
 * <p>
 * 示例 2：
 * 输入：nums = [6,2,6,5,1,2]
 * 输出：9
 * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
 * <p>
 * 提示：
 * 1 <= n <= 104
 * nums.length == 2 * n
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/c24he/
 *
 * @author jayzhou
 */
public class ArrayPairSum {

    private void mergesort(int[] nums, int l, int r, int[] tmp) {
        if (l == r) return;
        int mid = (l + r) >>> 1;
        mergesort(nums, l, mid, tmp);
        mergesort(nums, mid + 1, r, tmp);
        int ti = l, li = l, ri = mid + 1;
        while (li <= mid && ri <= r)
            tmp[ti++] = nums[li] < nums[ri] ? nums[li++] : nums[ri++];
        while (li <= mid) tmp[ti++] = nums[li++];
        while (ri <= r) tmp[ti++] = nums[ri++];
        while (l <= r) {
            nums[l] = tmp[l];
            l++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    private void quicksort(int[] nums, int l, int r) {
        if (l >= r) return;
        int slow = l, fast = l;
        for (; fast < r; fast++) {
            if (nums[fast] > nums[r]) continue;
            swap(nums, slow++, fast);
        }
        swap(nums, slow, r);
        quicksort(nums, l, slow - 1);
        quicksort(nums, slow + 1, r);
    }

    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
//        int[] tmp = new int[nums.length];
//        mergesort(nums, 0, nums.length - 1, tmp);
        quicksort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{6, 2, 6, 5, 1, 2};
        System.out.println(new ArrayPairSum().arrayPairSum(nums));
    }
}

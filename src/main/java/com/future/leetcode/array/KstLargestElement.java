package com.future.leetcode.array;

import java.util.Arrays;
import java.util.Random;

/**
 * 第k个最大的元素
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 提示：
 * 1 <= k <= nums.length <= 10^4
 * -10^4<= nums[i] <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x90rpm/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
class KstLargestElement {
    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        return threeWayQuicksort(nums, 0, nums.length - 1, nums.length - k);
    }

    public int threeWayQuicksort(int[] nums, int left, int right, int kth) {
        int index = randomPartion(left, right);
        swap(nums, index, right);
        int pivot = right;
        int l = left, p = l, r = right - 1;
        while (true) {
            while (l <= r && nums[l] <= nums[pivot]) {
                if (nums[l] < nums[pivot]) {
                    swap(nums, l, p++);
                }
                l++;
            }
            while (l <= r && nums[r] > nums[pivot]) {
                r--;
            }
            if (l > r) break;
            swap(nums, l, r);
        }
        swap(nums, l, pivot);
        if (kth < p)
            return threeWayQuicksort(nums, left, p - 1, kth);
        if (kth <= l)
            return nums[l];
        return threeWayQuicksort(nums, l + 1, right, kth);
    }

    public int quicksort(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        quicksort(nums, 0, nums.length - 1, k);
        return nums[nums.length - k];
    }

    public int randomPartion(int start, int end) {
        return random.nextInt(end - start + 1) + start;
    }

    public void quicksort(int[] nums, int left, int right, int k) {
        if (left >= right) return;
        int index = randomPartion(left, right);
        swap(nums, index, right);
        int pivot = right;
        int l = left, r = right - 1;
        while (true) {
            while (l <= r && nums[l] <= nums[pivot]) {
                l++;
            }
            while (l <= r && nums[r] > nums[pivot]) {
                r--;
            }
            if (l > r) break;
            swap(nums, l, r);
        }
        swap(nums, l, pivot);
        quicksort(nums, left, l - 1, k);
        quicksort(nums, l + 1, right, k);
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
//        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6};
        KstLargestElement element = new KstLargestElement();
        System.out.println(element.findKthLargest(nums, 2));
        System.out.println(Arrays.toString(nums));
    }
}

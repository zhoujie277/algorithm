package com.future.algoriithm.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？
 * 请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 提示：
 * 0 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * @author jayzhou
 */
public class ThreeSum {

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    private void quicksort(int[] nums, int l, int r) {
        if (l >= r) return;
        int end = r;
        int pivot = l++;
        while (l <= r) {
            while (l <= r && nums[r] > nums[pivot]) {
                r--;
            }
            while (l <= r && nums[l] <= nums[pivot]) {
                l++;
            }
            if (l > r) break;
            swap(nums, l, r);
        }
        swap(nums, pivot, r);
        quicksort(nums, pivot, r - 1);
        quicksort(nums, r + 1, end);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int l = 0, len = nums.length, r = len - 1;
//        quicksort(nums, l, r);
        Arrays.sort(nums);
        int lastIdx = nums.length - 3;
        for (int i = 0; i <= lastIdx; i++) {
            l = i + 1;
            r = len - 1;
            //此处去重，不用循环判断是因为数组已经被排序过。
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            while (l < r) {
                int res = nums[l] + nums[r] + nums[i];
                if (res > 0) {
                    r--;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (res < 0) {
                    l++;
                    while (l < r && nums[l] == nums[l - 1]) l++;
                } else {
                    result.add(Arrays.asList(nums[l++], nums[r--], nums[i]));
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = new ThreeSum().threeSum(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(lists);
    }
}

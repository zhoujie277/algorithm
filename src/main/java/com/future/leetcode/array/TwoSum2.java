package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 两数之和：输入有序数组
 * 给定一个已按照 升序排列的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 * <p>
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
 * 所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * <p>
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * <p>
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * <p>
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers 按 递增顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 *
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cnkjg/
 *
 * @author jayzhou
 */
public class TwoSum2 {

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) return numbers;
        for (int i = 0, j = numbers.length - 1; i < numbers.length; ) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) return new int[]{i + 1, j + 1};
            if (sum > target) j--;
            else i++;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        System.out.println(Arrays.toString(new TwoSum2().twoSum(nums, 9)));
    }
}

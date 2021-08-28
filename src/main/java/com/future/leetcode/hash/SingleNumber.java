package com.future.leetcode.hash;

import java.util.HashSet;

/**
 * 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/hash-table/xhsyr2/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class SingleNumber {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int sum = 0;
        for (int num : nums) {
            sum = sum ^ num;
        }
        return sum;
    }

    public int singleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                hashSet.remove(nums[i]);
            } else {
                hashSet.add(nums[i]);
            }
        }
        for (Integer integer : hashSet) {
            return integer;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 1};
        System.out.println(new SingleNumber().singleNumber(nums));
    }
}

package com.future.leetcode.hash;

import java.util.HashSet;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false
 *
 * @author jayzhou
 */
public class RepeatNumber1 {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            if (!hashSet.add(num)) {
                return true;
            }
        }
        return false;
    }
}

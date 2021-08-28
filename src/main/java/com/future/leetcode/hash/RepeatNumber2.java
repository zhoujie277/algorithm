package com.future.leetcode.hash;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 存在重复元素2
 * <p>
 * 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，
 * 使得 nums[i] = nums[j]，并且 i 和 j的差的绝对值至多为 k。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/hash-table/xx5bzh/
 */
public class RepeatNumber2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer integer = hashMap.get(nums[i]);
            if (integer != null) {
                return true;
            }
            hashMap.put(nums[i], i);
            if (i >= k) {
                hashMap.remove(nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer integer = treeMap.get(nums[i]);
            if (integer != null) {
                return true;
            }
            treeMap.put(nums[i], i);
            if (i >= k) {
                treeMap.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //输入: nums = [1,2,3,1,2,3], k = 2
        //输出: false
        int[] nums = new int[]{1, 2, 3, 1, 2, 3};
        RepeatNumber2 repeatNumber2 = new RepeatNumber2();
        System.out.println(repeatNumber2.containsNearbyDuplicate(nums, 2));
    }
}

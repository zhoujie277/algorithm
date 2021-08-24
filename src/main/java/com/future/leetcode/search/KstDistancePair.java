package com.future.leetcode.search;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 找出第k小的距离对
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * <p>
 * 示例 1:
 * 输入：
 * nums = [1,3,1]
 * k = 1
 * 输出：0
 * 解释：
 * 所有数对如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 提示:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xekymd/
 *
 * @author jayzhou
 */
public class KstDistancePair {

    public int smallestDistancePair(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 1; i < nums.length; i++) {
            queue.add(nums[i] - nums[i - 1]);
            queue.add(nums[i] - nums[i - 1]);
        }
        int res = 0;
        int index = 0;
        while (index < k && !queue.isEmpty()) {
            res = queue.poll();
            System.out.println("res=" + res);
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 10, 7, 10, 6, 1, 5, 4, 9, 8};
        KstDistancePair pa = new KstDistancePair();
        System.out.println(pa.smallestDistancePair(nums, 18));
    }
}

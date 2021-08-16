package com.future.algoriithm.practice;

import com.future.utils.PrintUtils;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 滑动窗口
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 *
 * @author jayzhou
 */
public class MaxSlidingWindow {

    public static int[] product(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) return nums;
        int len = nums.length;
        int[] dp = new int[len - k + 1];
        return nums;
    }

    /**
     * 单调队列法
     */
    @SuppressWarnings("all")
    public static int[] beta(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) return nums;
        int len = nums.length;
        int[] result = new int[len - k + 1];
        //1, -9, 8, -6, 6, 4, 0, 5
        LinkedList<Integer> list = new LinkedList<>();
        for (int ri = 0; ri < len; ri++) {
            while (!list.isEmpty() && nums[list.peekLast()] < nums[ri]) {
                list.pollLast();
            }
            list.addLast(ri);
            int li = ri - k + 1;
            if (li < 0) continue;
            if (list.peekFirst() < li) {
                list.pollFirst();
            }
            result[li] = nums[list.peekFirst()];
        }
        return result;
    }

    /**
     * 优先队列法
     * 时间复杂度：O(nlogN)
     * 空间复杂度：O(n)
     * 在单调递增队列中，空间复杂度达到O(n)，时间复杂度达到O(nlogN)
     */
    public static int[] alpha2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) return nums;
        int[] result = new int[nums.length - k + 1];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] != o1[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            queue.add(new int[]{nums[i], i});
        }
        result[0] = queue.peek()[0];
        int[] peek;
        for (int ri = k; ri < nums.length; ri++) {
            int li = ri - k + 1;
            while ((peek = queue.peek()) != null && peek[1] < li) {
                queue.poll();
            }
            queue.add(new int[]{nums[ri], ri});
            if ((peek = queue.peek()) != null) {
                result[li] = peek[0];
            }
        }
        return result;
    }

    /**
     * 朴素法的优化
     */
    public static int[] alpha(int[] nums, int k) {
        if (nums == null || nums.length == 0) return nums;
        int[] result = new int[nums.length - k + 1];
        int lastMaxIdx = -1;
        for (int i = result.length - 1; i >= 0; i--) {
            int ri = i + k - 1;
            if (lastMaxIdx != -1 && lastMaxIdx <= ri) {
                if (nums[i] > nums[lastMaxIdx]) {
                    result[i] = nums[i];
                    lastMaxIdx = i;
                } else {
                    result[i] = nums[lastMaxIdx];
                }
                continue;
            }
            int max = Integer.MIN_VALUE;
            for (int j = i; j <= ri; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                    lastMaxIdx = j;
                }
                max = Math.max(nums[j], max);
            }
            result[i] = max;
        }
        return result;
    }

    /**
     * 朴素法
     * 时间复杂度O(nk)
     */
    public static int[] dev(int[] nums, int k) {
        if (nums == null || nums.length == 0) return nums;
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < result.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(nums[j], max);
            }
            result[i] = max;
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums = new int[]{1, -9, 8, -6, 6, 4, 0, 5};
        //4
        int k = 4;
        int[] dev = dev(nums, k);
        PrintUtils.println(dev);
        PrintUtils.println(alpha(nums, k));
        PrintUtils.println(alpha2(nums, k));
        PrintUtils.println(beta(nums, k));
    }
}















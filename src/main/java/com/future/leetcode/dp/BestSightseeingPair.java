package com.future.leetcode.dp;

/**
 * 最佳观光组合
 * <p>
 * 给你一个正整数数组 values，其中 values[i]表示第 i 个观光景点的评分，并且两个景点i 和j之间的 距离 为j - i。
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和减去它们两者之间的距离。
 * 返回一对观光景点能取得的最高分。
 * <p>
 * 示例 1：
 * 输入：values = [8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 * 示例 2：
 * 输入：values = [1,2]
 * 输出：2
 * 提示：
 * 2 <= values.length <= 5 * 10^4
 * 1 <= values[i] <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
 *
 * @author jayzhou
 */
class BestSightseeingPair {
    /**
     * values[i] + i 或者 values[j] - j 本身是一个整体，故原题求：values[i] + i + values[j] - j
     * 即变成了 两数值 a 和 b的和的最大值。
     */
    public int maxScoreSightseeingPair(int[] values) {
        // vi 表示 values[i] + i。
        int vi = values[0];
        int answer = vi;
        for (int j = 1; j < values.length; j++) {
            int sightValue = vi + values[j] - j;
            if (sightValue > answer) {
                answer = sightValue;
            }
            if (values[j] + j > vi) {
                vi = values[j] + j;
            }
        }
        return answer;
    }

    /**
     * 朴素法：
     * 时间复杂度：O(n^2)
     * 结论；超出时间限制
     */
    public int brute(int[] values) {
        int n = values.length;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = values[i] + values[j] + i - j;
                maxLen = Math.max(len, maxLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        BestSightseeingPair pair = new BestSightseeingPair();
        int[] nums = new int[]{8, 1, 5, 2, 6};
        int[] nums2 = new int[]{2, 2, 2};
        int[] nums3 = new int[]{9, 7, 6, 7, 6, 9};
        int[] nums4 = new int[]{1, 2};
        System.out.println(pair.brute(nums));
        System.out.println(pair.maxScoreSightseeingPair(nums));
        System.out.println("-----");
        System.out.println(pair.brute(nums2));
        System.out.println(pair.maxScoreSightseeingPair(nums2));
        System.out.println("-----");
        System.out.println(pair.brute(nums3));
        System.out.println(pair.maxScoreSightseeingPair(nums3));
        System.out.println("-----");
        System.out.println(pair.brute(nums4));
        System.out.println(pair.maxScoreSightseeingPair(nums4));
    }
}

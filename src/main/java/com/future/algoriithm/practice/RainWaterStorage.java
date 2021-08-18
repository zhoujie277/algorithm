package com.future.algoriithm.practice;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * n == height.length
 * 0 <= n <= 3 * 10^4
 * 0 <= height[i] <= 10^5
 * <p>
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 *
 * @author jayzhou
 */
public class RainWaterStorage {

    public int trap(int[] height) {
        int l = 0, r = height.length - 1, lowerMax = 0, result = 0;
        do {
            int lower = height[l] <= height[r] ? height[l++] : height[r--];
            if (lowerMax > lower) {
                result += lowerMax - lower;
            } else {
                lowerMax = lower;
            }
        } while (l < r);
        return result;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new RainWaterStorage().trap(heights));
    }
}

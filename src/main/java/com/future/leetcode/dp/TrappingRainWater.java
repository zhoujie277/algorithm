package com.future.leetcode.dp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 接雨水
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）
 * <p>
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 *
 * @author jayzhou
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        int lowerMax = 0;
        int left = 0, right = height.length - 1;
        int water = 0;
        while (left < right) {
            int lower = height[left] < height[right] ? height[left++] : height[right--];
            if (lowerMax > lower) {
                water += lowerMax - lower;
            } else {
                lowerMax = lower;
            }
        }
        return water;
    }

    /**
     * 单调栈做法
     */
    public int monotoneStack(int[] height) {
        Deque<Integer> deque = new LinkedList<>();
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            Integer peekLast;
            while ((peekLast = deque.peekLast()) != null && height[peekLast] < height[i]) {
                int top = deque.pollLast();
                if (deque.isEmpty()) break;
                int left = deque.peekLast();
                int cWidth = i - left - 1;
                int cHeight = Math.min(height[left], height[i]) - height[top];
                water += cWidth * cHeight;
            }
            deque.addLast(i);
        }
        return water;
    }

    /**
     * 双指针
     * 优化动态规划的空间复杂度
     */
    public int doublePointer(int[] height) {
        int len = height.length;
        if (len == 0) return 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = len - 1;
        int water = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                water += leftMax - height[left];
                left++;
            } else {
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }

    /**
     * 动态规划方法
     * 空间复杂度：O(n)
     */
    public int dynamicProgramming(int[] height) {
        int len = height.length;
        if (len == 0) return 0;
        int[] rights = new int[len];
        rights[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rights[i] = Math.max(rights[i + 1], height[i]);
        }
        int[] lefts = new int[len];
        lefts[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            lefts[i] = Math.max(lefts[i - 1], height[i]);
        }
        int water = 0;
        for (int i = 0; i < len; i++) {
            water += Math.min(lefts[i], rights[i]) - height[i];
        }
        return water;
    }

    /**
     * 朴素法再优化
     * 开辟一个空间记录每个索引右边的最大值。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int bruteOptAgain(int[] height) {
        int len = height.length;
        int[] rights = new int[len];
        int rightMax = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax = Math.max(rightMax, height[i]);
            rights[i] = rightMax;
        }
        int water = 0;
        int leftMax = 0;
        for (int i = 0; i < height.length - 1; i++) {
            rightMax = rights[i];
            int lowerHeight = Math.min(leftMax, rightMax);
            water += Math.max(0, lowerHeight - height[i]);
            leftMax = Math.max(leftMax, height[i]);
        }
        return water;
    }


    /**
     * 朴素法的优化版本
     * 因为考虑到左边已经枚举过了，所以可以不需要再枚举。
     * 时间复杂度：O(n^2)
     */
    public int bruteOpt(int[] height) {
        int water = 0;
        int leftMax = 0;
        for (int i = 0; i < height.length - 1; i++) {
            int rightMax = 0;
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            int lowerHeight = Math.min(leftMax, rightMax);
            water += Math.max(0, lowerHeight - height[i]);
            leftMax = Math.max(leftMax, height[i]);
        }
        return water;
    }

    /**
     * 朴素法
     * 时间复杂度：O(n^2)
     */
    public int brute(int[] height) {
        int water = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax = 0;
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            int lowerHeight = Math.min(leftMax, rightMax);
            water += Math.max(0, lowerHeight - height[i]);
        }
        return water;
    }

    public static void main(String[] args) {
        TrappingRainWater rainWater = new TrappingRainWater();
        int[] heights = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] heights1 = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(rainWater.trap(heights));
        System.out.println(rainWater.brute(heights));
        System.out.println(rainWater.bruteOpt(heights));
        System.out.println(rainWater.bruteOptAgain(heights));
        System.out.println(rainWater.dynamicProgramming(heights));
        System.out.println(rainWater.doublePointer(heights));
        System.out.println(rainWater.monotoneStack(heights1));
        System.out.println(rainWater.trap(heights));
    }
}

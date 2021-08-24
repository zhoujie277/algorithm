package com.future.leetcode.array;

/**
 * 盛最多水的容器
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x96n4v/
 */
class MostContainerWater {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        int h;
        while (left < right) {
            int w = right - left;
            if (height[left] <= height[right]) {
                h = height[left++];
                while (h > height[left]) left++;
            } else {
                h = height[right--];
                while (h > height[right]) right--;
            }
            max = Math.max(max, h * w);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new MostContainerWater().maxArea(nums));
    }
}

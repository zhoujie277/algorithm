package com.future.algoriithm.practice;

/**
 * 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49
 * <p>
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 *
 * @author jayzhou
 */
public class MostWaterContainer {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int area = 0, h;
        do {
            int width = r - l;
            if (height[l] <= height[r]) {
                h = height[l++];
                area = Math.max(area, width * h);
                while (l < r && height[l] < h) l++;
            } else {
                h = height[r--];
                area = Math.max(area, width * h);
                while (l < r && height[r] < h) r--;
            }
        } while (l < r);
        return area;
    }

    /**
     * 面积 S = h * w。
     * 故先求最宽的情况： w = (0, height.length - 1), 求出S。
     * 再缩小宽度求s，缩小宽度的时候，从高比较短一边往里缩紧，并且找到更高的位置。试求最大值与之比较。
     */
    @SuppressWarnings("unused")
    public int maxArea2(int[] height) {
        int l = 0, r = height.length - 1;
        int area = 0;
        do {
            int width = r - l;
            int h = height[l] <= height[r] ? height[l++] : height[r--];
            area = Math.max(area, width * h);
        } while (l < r);
        return area;
    }

    public static void main(String[] args) {
        int[] areas = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new MostWaterContainer().maxArea(areas));
    }
}

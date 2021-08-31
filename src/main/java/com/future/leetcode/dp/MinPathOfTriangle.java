package com.future.leetcode.dp;

import java.util.*;

/**
 * 三角形最小路径和
 * <p>
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点这里指的是下标与上一层结点下标相同或者等于上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * *    2
 * *   3 4
 * *  6 5 7
 * * 4 1 8 3
 * 自顶向下的最小路径和为11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 * <p>
 * 提示：
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 * <p>
 * 进阶：
 * 你可以只使用 O(n)的额外空间（n 为三角形的总行数）来解决这个问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/triangle
 *
 * @author jayzhou
 */
public class MinPathOfTriangle {

    /**
     * 设 f[i][j] 表示 到达第 i 层第 j 个位置的最小路径和。
     * 初始状态和边界条件：到达第0层为0；f[0][0] = triangle[0][0]；
     * 状态转移：f[i][j] = triangle[i][j] + Math.min(f[i - 1][j] + f[i - 1][j - 1])
     * 返回值取最后一层的 最小值。
     * 时间复杂度：O(M*N)
     * 空间复杂度：O(M*N)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int layer = triangle.size();
        int[][] f = new int[layer][layer];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < layer; i++) {
            List<Integer> currentLayer = triangle.get(i);
            for (int j = 0; j < currentLayer.size(); j++) {
                int min = 0;
                if (j == 0) {
                    min = f[i - 1][j];
                } else if (j == currentLayer.size() - 1) {
                    min = f[i - 1][j - 1];
                } else {
                    min = Math.min(f[i - 1][j - 1], f[i - 1][j]);
                }
                f[i][j] = currentLayer.get(j) + min;
            }
        }
        int finalMin = Integer.MAX_VALUE;
        for (int j = 0; j < layer; j++) {
            finalMin = Math.min(f[layer - 1][j], finalMin);
        }
        return finalMin;
    }

    /**
     * 优化空间复杂度
     */
    public int minimumTotalOpt(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int layer = triangle.size();
        int[] f = new int[layer];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < layer; i++) {
            List<Integer> currentLayer = triangle.get(i);
            for (int j = currentLayer.size() - 1; j >= 0; j--) {
                int min = 0;
                if (j == 0) {
                    min = f[j];
                } else if (j == currentLayer.size() - 1) {
                    min = f[j - 1];
                } else {
                    min = Math.min(f[j - 1], f[j]);
                }
                f[j] = currentLayer.get(j) + min;
            }
        }
        int finalMin = Integer.MAX_VALUE;
        for (int j = 0; j < layer; j++) {
            finalMin = Math.min(f[j], finalMin);
        }
        return finalMin;
    }

    public static void main(String[] args) {
        MinPathOfTriangle triangle = new MinPathOfTriangle();
        //[[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> list = new ArrayList<>();
        list.add(Collections.singletonList(2));
        list.add(Arrays.asList(3, 4));
        list.add(Arrays.asList(6, 5, 7));
        list.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(triangle.minimumTotal(list));
        System.out.println(triangle.minimumTotalOpt(list));
    }

}

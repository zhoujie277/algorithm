package com.future.leetcode.dfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 腐烂的橘子
 * <p>
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * 值0代表空单元格；
 * 值1代表新鲜橘子；
 * 值2代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * <p>
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回-1。
 * <p>
 * 链接：https://leetcode-cn.com/problems/rotting-oranges
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class RottingOranges {

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     */
    public int orangesRotting(int[][] grid) {
        int minLen = 0;
        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    deque.addLast(new int[]{i, j});
                }
            }
        }
        int len = -1;
        while (!deque.isEmpty()) {
            len++;
            int size = deque.size();
            for (int k = 0; k < size; k++) {
                int[] ints = deque.pollFirst();
                freshAndRotting(grid, ints[0] + 1, ints[1], deque);
                freshAndRotting(grid, ints[0] - 1, ints[1], deque);
                freshAndRotting(grid, ints[0], ints[1] + 1, deque);
                freshAndRotting(grid, ints[0], ints[1] - 1, deque);
            }
        }
        minLen = Math.max(minLen, len);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        return minLen;
    }

    private void freshAndRotting(int[][] grid, int i, int j, Deque<int[]> deque) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 2;
            deque.addLast(new int[]{i, j});
        }
    }

    public static void main(String[] args) {
        //输入：[[2,1,1],[1,1,0],[0,1,1]]
        //输出：4
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        RottingOranges rottingOranges = new RottingOranges();
        System.out.println(rottingOranges.orangesRotting(grid));
    }
}

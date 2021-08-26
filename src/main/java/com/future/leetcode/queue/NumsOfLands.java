package com.future.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 岛屿数量
 * <p>
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/kbcqv/
 */

@SuppressWarnings("all")
public class NumsOfLands {

    public int numIslandsStack(char[][] grid) {
        if (grid == null) return 0;
        int len = 0;

        Deque<Pair> deque = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int land = grid[i][j];
                if (land == '1') {
                    len++;
                    grid[i][j] = '0';
                    deque.addLast(new Pair(i, j));
                    while (!deque.isEmpty()) {
                        Pair pair = deque.pollLast();
                        if (markLand(grid, pair.i + 1, pair.j)) {
                            deque.addLast(new Pair(pair.i + 1, pair.j));
                        }
                        if (markLand(grid, pair.i - 1, pair.j)) {
                            deque.addLast(new Pair(pair.i - 1, pair.j));
                        }
                        if (markLand(grid, pair.i, pair.j + 1)) {
                            deque.addLast(new Pair(pair.i, pair.j + 1));
                        }
                        if (markLand(grid, pair.i, pair.j - 1)) {
                            deque.addLast(new Pair(pair.i, pair.j - 1));
                        }
                    }
                }
            }
        }
        return len;
    }

    private boolean markLand(char[][] grid, int i, int j) {
        boolean ret = i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1';
        if (ret) grid[i][j] = '2';
        return ret;
    }

    /**
     * 时间复杂度：O(MN)
     */
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int len = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    len++;
                    dfs(grid, i, j);
                }
            }
        }
        return len;
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j] = 0;
        changeZero(grid, i - 1, j);
        changeZero(grid, i + 1, j);
        changeZero(grid, i, j - 1);
        changeZero(grid, i, j + 1);
    }

    private void changeZero(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if (grid[i][j] == '1') {
            dfs(grid, i, j);
        }
    }

    private boolean changeZero2(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return false;
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            return true;
        }
        return false;
    }

    public int numIslandsBFS(char[][] grid) {
        if (grid == null) return 0;
        int len = 0;
        Deque<Pair> q = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    len++;
                    q.add(new Pair(i, j));
                    while (!q.isEmpty()) {
                        Pair poll = q.poll();
                        if (changeZero2(grid, poll.i - 1, poll.j)) {
                            q.push(new Pair(poll.i - 1, poll.j));
                        }
                        if (changeZero2(grid, poll.i + 1, poll.j)) {
                            q.push(new Pair(poll.i + 1, poll.j));
                        }
                        if (changeZero2(grid, poll.i, poll.j - 1)) {
                            q.push(new Pair(poll.i, poll.j - 1));
                        }
                        if (changeZero2(grid, poll.i, poll.j + 1)) {
                            q.push(new Pair(poll.i, poll.j + 1));
                        }
                    }
                }
            }
        }
        return len;
    }

    private static class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        char[][] nums = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        char[][] num = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}
        };
        NumsOfLands numsOfLands = new NumsOfLands();
//        System.out.println(numsOfLands.numIslands(nums));
//        System.out.println(numsOfLands.numIslandsBFS(nums));
        System.out.println(numsOfLands.numIslandsStack(num));
    }
}

package com.future.leetcode.dfs;

import java.util.Arrays;

/**
 * 墙与门
 * 您将获得一个使用这三个可能值初始化的 m×n 2D 网格。
 * -1 - 墙壁或障碍物。
 * 0 - 门。
 * INF - Infinity是一个空房间。我们使用值 2 ^ 31 - 1 = 2147483647 来表示INF，您可以假设到门的距离小于 2147483647。
 * 在代表每个空房间的网格中填入到距离最近门的距离。如果不可能到达门口，则应填入 INF。
 *
 * @author jayzhou
 */
public class WallAndGates {

    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                int room = rooms[i][j];
                if (room == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] grid, int i, int j, int distance) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == -1) return;
        if (grid[i][j] > distance || distance == 0) {
            grid[i][j] = distance;
            dfs(grid, i - 1, j, distance + 1);
            dfs(grid, i + 1, j, distance + 1);
            dfs(grid, i, j + 1, distance + 1);
            dfs(grid, i, j - 1, distance + 1);
        }
    }

    public static void main(String[] args) {
//        int[][] grid = new int[][]{{0, -1}, {2147483647, 2147483647}};
        int[][] grid = new int[][]{{2147483647, -1, 0, 2147483647}, {-1, 2147483647, -1, -1}, {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}};

        WallAndGates wallAndGates = new WallAndGates();
        wallAndGates.wallsAndGates(grid);
        System.out.println(Arrays.deepToString(grid));
    }
}

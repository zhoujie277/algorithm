package com.future.algoriithm.question;

import com.future.algoriithm.PrintUtils;
import com.future.algoriithm.Printable;

/**
 * 简单迷宫问题，用递归办法解决
 * 该迷宫是6*6的围墙迷宫，从入口（左上角）移动到出口（右下角）
 * 1为障碍物，0为可移动区域
 */
public class SimpleMaze implements Printable {
    private int[][] maze;

    private int exitX = 0;
    private int exitY = 0;

    public SimpleMaze(int row, int column) {
        row = row + 2;
        column = column + 2;
        maze = new int[row][column];
        // 把周围给堵起来
        for (int i = 0; i < column; i++) {
            maze[0][i] = 1;
            maze[row - 1][i] = 1;
        }
        for (int i = 0; i < row; i++) {
            maze[i][0] = 1;
            maze[i][column - 1] = 1;
        }
    }

    public void addObstacle(int x, int y) {
        maze[toMap(x)][toMap(y)] = 1;
    }

    public void setExit(int x, int y) {
        this.exitX = toMap(x);
        this.exitY = toMap(y);
    }

    public void startFind(int x, int y) {
        findExit(toMap(x), toMap(y));
    }

    private int toMap(int x) {
        return x + 1;
    }

    @Override
    public void println() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                PrintUtils.print(maze[i][j] + "\t");
            }
            PrintUtils.println();
        }
        PrintUtils.println("Exit point is " + exitX + "," + exitY);
    }

    /**
     * 目标是走出迷宫
     * recursion
     * 1 表示障碍物
     * 2 表示已经走过的路
     * 3 表示顺畅的路
     * 走出策略：下 - 左 - 上 - 右
     */
    private boolean findExit(int x, int y) {
        if (maze[x][y] == 1) return false;
        if (x == exitX && y == exitY) {
            PrintUtils.println("findExit!");
            maze[x][y] = 3;
            return true;
        }
        if (maze[x][y] != 0) return false;
        maze[x][y] = 3;
        if (findExit(x + 1, y)) return true;
        if (findExit(x, y + 1)) return true;
        if (findExit(x - 1, y)) return true;
        if (findExit(x, y - 1)) return true;
        maze[x][y] = 2;
        return false;
    }

    public static void main(String[] args) {
        SimpleMaze maze = new SimpleMaze(6, 5);
        maze.addObstacle(2, 0);
        maze.addObstacle(2, 1);
        maze.addObstacle(0,1);
        maze.addObstacle(1,1);
        maze.setExit(5, 4);
        maze.println();
        maze.startFind(0, 0);
        maze.println();
    }


}

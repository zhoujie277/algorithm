package com.future.leetcode.dfs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 01 矩阵
 * <p>
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * <p>
 * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0,0],[0,1,0],[0,0,0]]
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/g7pyt/
 *
 * @author jayzhou
 */
public class Matrix01 {

    private static class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int[][] updateMatrix(int[][] mat) {
        Deque<Pair> deque = new LinkedList<>();
        int[][] other = new int[mat.length][mat[0].length];
        boolean[][] seen = new boolean[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    deque.add(new Pair(i, j));
                    seen[i][j] = true;
                }
            }
        }
        int len = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int k = 0; k < size; k++) {
                Pair pair = deque.pollFirst();
                other[pair.i][pair.j] = len;
                Pair top = getAdjacencyPair(mat, pair.i - 1, pair.j, seen);
                Pair bottom = getAdjacencyPair(mat, pair.i + 1, pair.j, seen);
                Pair right = getAdjacencyPair(mat, pair.i, pair.j + 1, seen);
                Pair left = getAdjacencyPair(mat, pair.i, pair.j - 1, seen);
                addPair(deque, top);
                addPair(deque, bottom);
                addPair(deque, right);
                addPair(deque, left);
            }
            len++;
        }
        return other;
    }

    private void addPair(Deque<Pair> deque, Pair pair) {
        if (pair != null) deque.addLast(pair);
    }

    private Pair getAdjacencyPair(int[][] mat, int i, int j, boolean[][] seen) {
        if (i >= 0 && i < mat.length && j >= 0 && j < mat[0].length && !seen[i][j]) {
            seen[i][j] = true;
            return new Pair(i, j);
        }
        return null;
    }

    public static void main(String[] args) {
        //[[0,0,0],[0,1,0],[0,0,0]]
        int[][] mat = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] mat1 = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        Matrix01 matrix01 = new Matrix01();
        System.out.println(Arrays.deepToString(matrix01.updateMatrix(mat)));
        System.out.println(Arrays.deepToString(matrix01.updateMatrix(mat1)));
    }

}

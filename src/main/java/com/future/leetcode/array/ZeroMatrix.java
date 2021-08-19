package com.future.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 零矩阵
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [
 * *   [1,1,1],
 * *   [1,0,1],
 * *   [1,1,1]
 * ]
 * 输出：
 * [
 * *  [1,0,1],
 * *  [0,0,0],
 * *  [1,0,1]
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/ciekh/
 *
 * @author jayzhou
 */
public class ZeroMatrix {

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(m)
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean rowZero = false, colZero = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colZero = true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                rowZero = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (colZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (rowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(m+n)
     */
    public void setZeroes3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        HashSet<Integer> columnSet = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            boolean rowZero = false;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowZero = true;
                    columnSet.add(j);
                    for (int k = 0; k < i; k++) {
                        matrix[k][j] = 0;
                    }
                } else if (columnSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
            if (rowZero) {
                for (int k = 0; k < matrix[0].length; k++) {
                    matrix[i][k] = 0;
                }
            }
        }
    }

    public void setZeroes1(int[][] matrix) {
        HashSet<Integer> rowSet = new HashSet<>();
        HashSet<Integer> columnSet = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    columnSet.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            boolean zeroRow = rowSet.contains(i);
            for (int j = 0; j < matrix[0].length; j++) {
                if (zeroRow) {
                    matrix[i][j] = 0;
                } else if (columnSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3,}, {4, 0, 6}, {7, 8, 9}
        };
        System.out.println(Arrays.deepToString(matrix));
        new ZeroMatrix().setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}

package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * <p>
 * 不占用额外内存空间能否做到？
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * *   [1,2,3],
 * *   [4,5,6],
 * *   [7,8,9]
 * * ],
 * *
 * * 原地旋转输入矩阵，使其变为:
 * * [
 * *   [7,4,1],
 * *   [8,5,2],
 * *   [9,6,3]
 * * ]
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/clpgd/
 *
 * @author jayzhou
 */
public class RotateMatrix {

    public void rotate(int[][] matrix) {
        int row = matrix.length;
        int column = matrix.length;
        // 对角线翻转
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < column; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }

        int half = column >> 1;
        // 水平翻转(镜像翻转)
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < half; j++) {
                int c = column - j - 1;
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][c];
                matrix[i][c] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3,}, {4, 5, 6}, {7, 8, 9}
        };

        System.out.println(Arrays.deepToString(matrix));
        new RotateMatrix().rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}

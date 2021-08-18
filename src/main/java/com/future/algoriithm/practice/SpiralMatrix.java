package com.future.algoriithm.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * @author jayzhou
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return null;
        List<Integer> list = new ArrayList<>();
        int left = 0, top = 0;
        int right = matrix.length - 1, bottom = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;
            if (top > bottom) break;
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            right--;
            if (left > right) break;
            for (int i = right; i >= left; i--) {
                list.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i++) {
                list.add(matrix[i][left]);
            }
            left++;
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        spiralMatrix.spiralOrder(matrix);
    }
}

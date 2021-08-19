package com.future.leetcode.array;

import java.util.Arrays;

/**
 * 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * *  [ 1, 2, 3 ],
 * *  [ 4, 5, 6 ],
 * *  [ 7, 8, 9 ]
 * * ]
 * <p>
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * <p>
 * 给定矩阵中的元素总数不会超过 100000 。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cuxq3/
 *
 * @author jayzhou
 */
public class DiagonalTraversal {

    public int[] findDiagonalOrder(int[][] matrix) {
        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        // Variables to track the size of the matrix
        int N = matrix.length;
        int M = matrix[0].length;

        // Incides that will help us progress through
        // the matrix, one element at a time.
        int row = 0, column = 0;

        // As explained in the article, this is the variable
        // that helps us keep track of what direction we are
        // processing the current diaonal
        int direction = 1;

        // The final result array
        int[] result = new int[N * M];
        int r = 0;

        // The uber while loop which will help us iterate over all
        // the elements in the array.
        while (row < N && column < M) {

            // First and foremost, add the current element to
            // the result matrix.
            result[r++] = matrix[row][column];

            // Move along in the current diagonal depending upon
            // the current direction.[i, j] -> [i - 1, j + 1] if
            // going up and [i, j] -> [i + 1][j - 1] if going down.
            int new_row = row + (direction == 1 ? -1 : 1);
            int new_column = column + (direction == 1 ? 1 : -1);

            // Checking if the next element in the diagonal is within the
            // bounds of the matrix or not. If it's not within the bounds,
            // we have to find the next head.
            if (new_row < 0 || new_row == N || new_column < 0 || new_column == M) {

                // If the current diagonal was going in the upwards
                // direction.
                if (direction == 1) {

                    // For an upwards going diagonal having [i, j] as its tail
                    // If [i, j + 1] is within bounds, then it becomes
                    // the next head. Otherwise, the element directly below
                    // i.e. the element [i + 1, j] becomes the next head
                    row += (column == M - 1 ? 1 : 0);
                    column += (column < M - 1 ? 1 : 0);

                } else {

                    // For a downwards going diagonal having [i, j] as its tail
                    // if [i + 1, j] is within bounds, then it becomes
                    // the next head. Otherwise, the element directly below
                    // i.e. the element [i, j + 1] becomes the next head
                    column += (row == N - 1 ? 1 : 0);
                    row += (row < N - 1 ? 1 : 0);
                }

                // Flip the direction
                direction = 1 - direction;

            } else {

                row = new_row;
                column = new_column;
            }
        }
        return result;
    }


    // 一次对角线遍历是以 (p, q) -> (q, p)结束。
    // 该算法是以每条对角线遍历，从0行到第m行。打印每一行的对角线，特殊之处，在于要不要翻转，用一个reverse变量控制就可以了
    // 先按照行，画完，最后再画列，列从1开始。
    public int[] findDiagonalOrder2(int[][] mat) {
        if (mat == null) return null;
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int idx = 0;
        if (n == 1 || m == 1) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    result[idx++] = mat[i][j];
                }
            }
            return result;
        }
        boolean ascOrder = true;
        int diagonalCount = m + n - 1;
        for (int ci = 0; ci < diagonalCount; ci++) {
            // 定理：i + j == ci;
            // 遍历行
            if (ci < m) {
                int e = Math.min(ci, n - 1);
                if (ascOrder) {
                    // 一个for循环画一条对角线,画对角线的时候，i是列。
                    for (int i = 0; i <= e; i++) {
                        result[idx++] = mat[ci - i][i];
                    }
                } else {
                    for (int i = e; i >= 0; i--) {
                        result[idx++] = mat[ci - i][i];
                    }
                }
            } else {
                int s = ci - m + 1;
                s = Math.min(s, n - 1);
                // 遍历列
                if (ascOrder) {
                    // 一个for循环画一条对角线
                    for (int i = s; i <= n - 1; i++) {
                        result[idx++] = mat[ci - i][i];
                    }
                } else {
                    for (int i = n - 1; i >= s; i--) {
                        result[idx++] = mat[ci - i][i];
                    }
                }
            }
            ascOrder = !ascOrder;
        }
        return result;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{
//                {1, 2, 3,}, {4, 5, 6}, {7, 8, 9}
//        };

//        int[][] matrix = new int[][]{
//                {2, 5}, {8, 4}, {0, -1}
//        };
//        int[][] matrix = new int[][]{
//                {2, 5, 8}, {4, 0, -1}
//        };

        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {11, 12, 13, 14, 15, 16, 17, 18, 19, 20}
        };
        System.out.println(Arrays.deepToString(matrix));
        int[] diagonalOrder = new DiagonalTraversal().findDiagonalOrder(matrix);
        System.out.println(Arrays.toString(diagonalOrder));
    }

}

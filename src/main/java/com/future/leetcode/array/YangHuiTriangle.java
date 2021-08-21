package com.future.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * 示例2:
 * 输入: numRows = 1
 * 输出: [[1]]
 * 提示:
 * 1 <= numRows <= 30
 *
 * @author jayzhou
 */
public class YangHuiTriangle {

    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) return null;
        List<List<Integer>> yangHui = new ArrayList<>(numRows);
        List<Integer> first = new ArrayList<>(1);
        first.add(1);
        yangHui.add(first);
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = yangHui.get(i - 1);
            List<Integer> row = new ArrayList<>(i + 1);
            row.add(lastRow.get(0));
            for (int j = 1; j < lastRow.size(); j++) {
                int leftTop = lastRow.get(j - 1);
                int top = lastRow.get(j);
                row.add(leftTop + top);
            }
            row.add(lastRow.get(lastRow.size() - 1));
            yangHui.add(row);
        }
        return yangHui;
    }

    /**
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和.
     * 输入: rowIndex = 3
     * 输出: [1,3,3,1]
     * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) return null;
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            // 上一行最后一个数字
            int last = row.get(i - 1);
            for (int j = i - 1; j >= 1; j--) {
                int leftTop = row.get(j - 1);
                int top = row.get(j);
                row.set(j, leftTop + top);
            }
            row.add(last);
        }
        return row;
    }

    public static void main(String[] args) {
        YangHuiTriangle triangle = new YangHuiTriangle();
        System.out.println(triangle.generate(5));
        System.out.println(triangle.getRow(5));
    }
}

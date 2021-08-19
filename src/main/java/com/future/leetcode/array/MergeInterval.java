package com.future.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/c5tv3/
 *
 * @author jayzhou
 */
public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return intervals;
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> merged = new ArrayList<>();
        if (intervals.length == 1) {
            merged.add(intervals[0]);
        } else {
            int l = intervals[0][0];
            int r = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] <= r) {
                    r = Math.max(r, intervals[i][1]);
                    l = Math.min(l ,intervals[i][0]);
                } else {
                    merged.add(new int[]{l, r});
                    l = intervals[i][0];
                    r = intervals[i][1];
                }
                if (i == intervals.length - 1) {
                    merged.add(new int[]{l, r});
                }
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 3}};
        System.out.println(Arrays.deepToString(new MergeInterval().merge(nums)));
    }

}

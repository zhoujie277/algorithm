package com.future.leetcode.array;

import java.util.HashMap;

/**
 * 回旋镖的数量
 * <p>
 * 给定平面上n 对 互不相同 的点points ，其中 points[i] = [xi, yi] 。
 * 回旋镖 是由点(i, j, k) 表示的元组 ，其中i和j之间的距离和i和k之间的距离相等（需要考虑元组的顺序）。
 * 返回平面上所有回旋镖的数量。
 * <p>
 * 示例 1：
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 * 输入：points = [[1,1]]
 * 输出：0
 * <p>
 * 提示：
 * n ==points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * 所有点都 互不相同
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-lockup-table/xhp45m/
 *
 * @author jayzhou
 */
public class NumberOfBoomerangs {

    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int dis = dx * dx + dy * dy;
                hashMap.put(dis, hashMap.getOrDefault(dis, 0) + 1);
            }
            for (Integer dis : hashMap.keySet()) {
                Integer val = hashMap.get(dis);
                if (val > 1) {
                    count += val * (val - 1);
                }
            }
        }
        return count;
    }
}

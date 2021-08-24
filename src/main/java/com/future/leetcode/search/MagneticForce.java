package com.future.leetcode.search;

import java.util.Arrays;

/**
 * 两球之间的磁力
 * <p>
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。
 * Rick 有n个空的篮子，第i个篮子的位置在position[i]，Morty想把m个球放到这些篮子里，使得任意两球间最小磁力最大。
 * <p>
 * 已知两个球如果分别位于x和y，那么它们之间的磁力为|x - y|。
 * <p>
 * 给你一个整数数组position和一个整数m，请你返回最大化的最小磁力。
 * <p>
 * 示例 1：
 * 输入：position = [1,2,3,4,7], m = 3
 * 输出：3
 * 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。
 * 我们没办法让最小磁力大于 3 。
 * 示例 2：
 * 输入：position = [5,4,3,2,1,1000000000], m = 2
 * 输出：999999999
 * 解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
 * <p>
 * 提示：
 * n == position.length
 * 2 <= n <= 10^5
 * 1 <= position[i] <= 10^9
 * 所有position中的整数 互不相同。
 * 2 <= m <= position.length
 * <p>
 * 链接：https://leetcode-cn.com/problems/magnetic-force-between-two-balls
 *
 * @author jayzhou
 */
public class MagneticForce {

    /**
     * 依题意：两元素构成一个数对，两元素的磁力即是这两元素的绝对差值。即原问题可转换为问题：
     * 在一个数组中选 m 个元素，使得它们构成的数对在所有的划分方式中的差值最大；
     * 并在构成最大差值的这些数对中选取最小的一对。这个就叫做"最小差值"。
     * 这道题的题眼在于最后"最小差值"。
     * 首先，第一个要做的便是排序。因为在有序数组中，相邻的两元素差值最小。
     * 假设已经选定了m个元素，求它们彼此间构成数对的差值的最小值，那只用遍历一次有序数组，比较相邻的两元素差值，然后取最小。
     * 并且，数组有序后，也能得到"最小差值"的取值范围，即上限和下限：
     * 取值下限：为有序数组的所有相邻元素的差最小的那一对。为了方便计算，简单取值为1也行。因为要查找相邻最小，还得先遍历一次全数组。
     * 取值上限：m个元素构成 m - 1 个间隔，最大元素与最小元素的差为d。
     * *         假设平均划分，则每个相邻的元素差均为：d / (m - 1)。此时这 m 个元素构成的最小差值也是 d / (m - 1)
     * *         再假设不平均划分，就以三个元素两个间隔为例，要不往左偏一点，要不往右偏一点，但无论往哪个方向偏，
     * *         最后这 m 个元素构成的数对的"最小差值"一定小于 d / (m - 1)。（因为要选最小的那一对嘛）
     * 界定好了"最小差值"的取值上限和下限；便可以尝试采用二分法的思想求得答案：猜数字
     * 每次从取值范围中猜一个"最小差值"的数字，然后用它去反推和验证position数组的划分方式。
     * 验证逻辑如下：
     * 因为猜的值是最小的，所以采取计数的办法，假设猜的值是 x；
     * 遍历数组，并采用贪心策略，找到尽可能多的position。具体策略查看check方法说明。
     * 对前后两个元素的差大于等于x的计数累加记作 count。遍历到结尾便会有两种情况：
     * 1、count ≥ m。说明用这个 x 反推构成的数对数量大于等于m，符合题意。但构成的数对未必是最大数对；
     * *            所以可以再把 x 猜大一点，尝试能不能找到同样满足条件但差值更大的数对。
     * 2、count < m。说明用这个 x 反推构成的数对数量小于 m，不符题意，那说明 x 的值大了。应该再尝试往值小于x的方向猜。
     */
    public int maxDistance(int[] position, int m) {
        if (position == null || position.length == 0) return 0;
        Arrays.sort(position);
        int min = position[0], max = position[position.length - 1];
        int left = 1, right = (max - min) / (m - 1);
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (check(mid, position, m)) {
                left = mid + 1;
//                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 我们希望能在pos数组中找到尽量多的位置去放置小球，所以验证的时候采用贪心策略。
     * 即，优先将小球放在position最小的篮子当中，然后向后查找，一直找到大于等于power的第2个position。
     * 最后接着往下找，看能找到多少个大于等于power的position。
     * 备注：如果开始往第二个position或者更后面的position个，最终的count一定 ≤ 从position[0]开始计算的count。
     */
    private boolean check(int power, int[] pos, int m) {
        int count = 1;
        int prePos = pos[0];
        for (int i = 1; i < pos.length; i++) {
            if (pos[i] - prePos >= power) {
                prePos = pos[i];
                count++;
            }
        }
        return count >= m;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 7};
        MagneticForce mf = new MagneticForce();
        System.out.println(mf.maxDistance(nums, 3));
        int[] p1 = new int[]{5, 4, 3, 2, 1, 1000000000};
        System.out.println(mf.maxDistance(p1, 2));
        int[] p2 = new int[]{79, 74, 57, 22};
        System.out.println(mf.maxDistance(p2, 4));
    }
}

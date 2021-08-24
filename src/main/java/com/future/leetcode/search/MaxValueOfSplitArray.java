package com.future.leetcode.search;

import java.util.Arrays;

/**
 * 分割数组的最大值
 * 给定一个非负整数数组 nums 和一个整数m ，你需要将这个数组分成m个非空的连续子数组。
 * <p>
 * 设计一个算法使得这m个子数组各自和的最大值最小。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [7,2,5,10,8], m = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4,5], m = 2
 * 输出：9
 * 示例 3：
 * <p>
 * 输入：nums = [1,4,4], m = 3
 * 输出：4
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^6
 * 1 <= m <= min(50, nums.length)
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xegsph/
 *
 * @author jayzhou
 */
public class MaxValueOfSplitArray {

    /**
     * 根据题意，需要将原数组划分成 m 个连续的子数组，那么就会有多种划分方式，
     * 并且求得每种划分方式的解：也就是这 m 个子数组中的和的最大值 b，称这个最大值 b 是这个划分方式的解。
     * 那么原题也就是在求在所有的划分方式中最小的解。
     * <p>
     * 所以也可以反过来这么考虑问题。
     * 猜想一个最大值 b。
     * 用这个 b 去划分子数组，使得每个子数字的和尽量往b值靠拢，且都小于等于 b，以尽量减小count数量。（贪心策略）
     * 假设这么划分之后的子数组个数为 count，那么就会有以下两种情况：
     * 1、count > m. 说明这个b值小了，不符题意，应该尝试更大的数字继续猜想。
     * 2、count ≤ m. 说明这个b值符合题意，当这个b值可能可以更小。应该尝试更小的数字继续猜想。
     * <p>
     * 以上思路，便可采用二分答案的方法求得我们需要的解。
     * 要用二分法解决问题，便需要确定猜想值 b 的取值范围。
     * 不难得出结论：
     * 下限取值范围为：数组中最大元素的值。(每个元素独立为一个子数组）
     * 上限取值范围为：原数组所有元素累加的和。（m = 1 的情况）
     * 并根据题意，求的是连续子数组的和，所以不可修改原数组，也就不可排序。
     * <p>
     * 时间复杂度：O(n) + O(NLog(sum-low);
     * 空间复杂度：O(1)
     */
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0, low = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            low = Math.max(low, nums[i]);
        }
        if (m == 1) return sum;
        int left = low, right = sum;
        do {
            int mid = (left + right) >>> 1;
            if (check(nums, m, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } while (left <= right);
        return left;
    }

    /**
     * 贪心策略：将尽可能多的数字归到一个子数组内，以使得子数组元素更多，最后的count数最小。
     * count值代表最后划分的子数字数量。起始值应该为1。
     */
    private boolean check(int[] nums, int m, int max) {
        int count = 1, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > max) {
                sum = nums[i];
                count++;
            } else {
                sum += nums[i];
            }
        }
        return count <= m;
    }

    /**
     * 动态规划解法
     * 时间复杂度：O(M*N*N)
     * 空间复杂度: O(M*N)
     */
    private int splitArrayByDp(int[] nums, int m) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (j > i) continue;
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], prefixSum[i] - prefixSum[k]));
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        MaxValueOfSplitArray array = new MaxValueOfSplitArray();
        int[] nums1 = new int[]{7, 2, 5, 10, 8};
        int[] nums2 = new int[]{1, 2, 3, 4, 5};
        int[] nums3 = new int[]{1, 4, 4};
        System.out.println(array.splitArray(nums1, 2));
        System.out.println(array.splitArray(nums2, 2));
        System.out.println(array.splitArray(nums3, 3));
        System.out.println(array.splitArrayByDp(nums1, 2));
    }
}

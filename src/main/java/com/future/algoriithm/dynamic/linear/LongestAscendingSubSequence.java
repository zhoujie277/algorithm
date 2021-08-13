package com.future.algoriithm.dynamic.linear;

/**
 * 经典线性模型
 * 最长上升子序列
 * <p>
 * 问题：从n个数中找出目前最长上升子序列
 * 阶段：f[i]表示以a[i]结尾的最长上升子序列，从a1，a2...ai。称为第i个阶段。
 * 状态：子序列中最后一个数的大小
 * 决策：取不取最后一个数a[i]为结尾的作为上升子序列
 * 最优策略：设f[j] = {f[1], f[2], ... f[j]}, 拿出前面满足a[i] > a[j]的最大上升子序列+1，然后选出最大值作为新的f[i]的状态
 * 状态转移方程：f[i] = array[i] > array[i -1] ? f[i-1] + 1: f[i-1]
 *
 * @author jayzhou
 */
public class LongestAscendingSubSequence {

    /**
     * 更新dp[i]的条件：array[i] < array[j] && 1 ≤ j && j < i
     * 这个其实就是"决策允许集合"
     */
    public static int alpha(int[] array) {
        int n = array.length;
        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                // todo:此处有重复计算，可用树状数组优化
                if (array[i] > array[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        return max;
    }

    /**
     * 有序数组法(牌堆法)
     * 时间复杂度：O(n^2)
     */
    public static int orderArray(int[] array) {
        int[] buckets = new int[array.length];
        int len = 0;
        for (int i = 0, j; i < array.length; i++) {
            for (j = 0; j < len; j++) {
                if (array[i] <= buckets[j]) {
                    buckets[j] = array[i];
                    break;
                }
            }
            if (j == len)
                buckets[len++] = array[i];
        }
        return len;
    }

    /**
     * 二分查找插入法
     * 时间复杂度 0(NlogN)
     */
    public static int binarySearch(int[] array) {
        int[] bits = new int[array.length];
        int len = 0;
        for (int j : array) {
            int left = 0, right = len;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (j >= bits[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            bits[left] = j;
            if (left == len) len++;
        }
        return len;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 7, 1, 5, 6, 4, 3, 8, 9};
        int alpha = alpha(array);
        System.out.println("alpha=" + alpha);
        int deck = orderArray(array);
        System.out.println("deck=" + deck);
        int binarySearch = binarySearch(array);
        System.out.println("binarySearch=" + binarySearch);
    }

}

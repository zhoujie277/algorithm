package com.future.leetcode.hash;

import java.util.HashMap;

/**
 * 四数相加
 * <p>
 * 给定四个包含整数的数组列表A , B , C , D ,计算有多少个元组 (i, j, k, l)，使得A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过2^31 - 1 。
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 *
 * @author jayzhou
 */
public class AddFourNumbers {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int k : nums1) {
            for (int j : nums2) {
                int s = k + j;
                hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
            }
        }
        int count = 0;
        for (int k : nums3) {
            for (int i : nums4) {
                int s = k + i;
                Integer orDefault = hashMap.getOrDefault(-s, 0);
                if (orDefault > 0) {
                    count += orDefault;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{-1, -1};
        int[] num2 = new int[]{-1, 1};
        int[] num3 = new int[]{-1, 1};
        int[] num4 = new int[]{1, -1};
        AddFourNumbers fourNumbers = new AddFourNumbers();
        System.out.println(fourNumbers.fourSumCount(num1, num2, num3, num4));
    }

}

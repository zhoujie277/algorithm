package com.future.leetcode.search;

/**
 * 传送包裹
 * <p>
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * 传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。
 * 我们装载的重量不会超过船的最大运载重量。
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 示例 1：
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * <p>
 * 示例 2：
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * <p>
 * 示例 3：
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 * <p>
 * 提示：
 * 1 <= D <= weights.length <= 5 * 10^4
 * 1 <= weights[i] <= 500
 * <p>
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 *
 * @author jayzhou
 */
public class TransferPackage {

    /**
     * 时间复杂度：O(NLog( ∑weights)
     * 二分范围：最小左区间：货物中的最大值。最大右区间：货物重量之和
     * 空间复杂度：O(1)
     */
    public int shipWithinDays(int[] weights, int days) {
        if (weights == null || weights.length == 0) return 0;
        int upCapacity = 0, lowCapacity = 0;
        for (int i = 0; i < weights.length; i++) {
            upCapacity += weights[i];
            lowCapacity = Math.max(weights[i], lowCapacity);
        }
        do {
            int mid = (upCapacity + lowCapacity) >>> 1;
            if (check(mid, weights, days)) {
                upCapacity = mid - 1;
            } else {
                lowCapacity = mid + 1;
            }
        } while (lowCapacity <= upCapacity);
        return lowCapacity;
    }

    /**
     * 二分答案最佳实践
     * 由于题意要求在D天内完成货物传送。
     * 首先根据题意，货物最小运载能力的最小值必须能装下货物当中的某个最大重量。
     * 假设在货物运载一天之内完成所有货物的运载，此时货物重量和就是最小运载能力的最大值。
     * 那么猜一个值，先试着去求这个运载能力能不能在D天内完成货物传送。
     * 如果能，试着往左区间猜更小值；如果不能，试着往右区间猜更大值。
     */
    public boolean check(int capacity, int[] weights, int D) {
        int sum = 0, days = 1;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > capacity) {
                sum = weights[i];
                days += 1;
            }
        }
        return days <= D;
    }

    public static void main(String[] args) {
        int[] weights = new int[]{1, 2, 3, 1, 1};
        TransferPackage transferPackage = new TransferPackage();
        System.out.println(transferPackage.shipWithinDays(weights, 4));
        int[] ws = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(transferPackage.shipWithinDays(ws, 5));
        int[] ws2 = new int[]{10, 50, 100, 100, 50, 100, 100, 100};
        System.out.println(transferPackage.shipWithinDays(ws2, 5));
    }

}

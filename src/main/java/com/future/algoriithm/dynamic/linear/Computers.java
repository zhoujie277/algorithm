package com.future.algoriithm.dynamic.linear;

/**
 * POJ--3486
 * 你想保证N年中都有一台电脑，一开始你有一台，
 * 如果你在第 y（1≤y≤n）年购买了一台电脑，那么你需要花费c的代价。
 * 如果你这台电脑一直用到了第z年，在第z年你又买了一台新的，你需要支付的维修费用为m(y,z)
 * 给定n，c，数组m，求最小花费。
 * 数据举例：
 * 花费c固定：3
 * 输入n行，n=3
 * 第1行：5， 7， 10
 * 第2行：6，8
 * 第3行：10
 * 第一年购买电脑花费3，之后只维护一年m(1,1)=5再花费5.第二年购买电脑花费3，从第二年维护到第三年花费m(2,3)=8
 * 输入代码示例：
 * *         scanf("%d", &n);
 * *         memset(m, 0x3f, sizeof(m));
 * *
 * *        for (int i = 1; i <= n; i++) {
 * *            for(int j = i; j <= n; j++) {
 * *                scanf("%d", &m[i][j]);
 * *            }
 * *        }
 * <p>
 * 首先划分阶段。
 * 每一年可以划分为一个阶段。
 * f[i]表示直到第i年f[0],f[1]...f[i-1].你手里都有一台电脑的最小花费
 * f[i]需要从某个时间转移过来，如何转移？（枚举上一次买电脑是哪一年！）
 * <p>
 * 假设上一次买电脑是第j年。
 * 那么1～j-1年就是一个子问题，我们已经算出了f[i-1]是满足这个子问题的最优解，后面我们就不用考虑前j-1年的情况，且它不会影响我们后面的决策。
 * 第j年到第i年的维修费用是m(j,i),花费是c。
 * 因此可以用 f[j - 1] + m(j, i) + c 来更新 f[i]
 * f[i] = min{f[j-1] + m(j, i) + c | 1 ≤ j ≤ i}
 * <p>
 * 状态就按每年的时间划分，那么状态就很明确了dp[i]表示在前i年拥有电脑的最小花费。那么接下来就要思考如何转移？
 * 阶段: 电脑用到了第i年。
 * 状态：第i年电脑产生的费用
 * 决策：换不换新电脑,换新电脑产生的费用m(,i
 * 最优策略：买，则产生m(
 * 状态转移方程：
 *
 * @author jayzhou
 */
public class Computers {

    /**
     * 花费固定c
     * 维修费用数组m
     */
    public static int alpha(int[][] m, int c) {
        int n = m.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[j - 1] + m[j][i] + c);
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int c = 3;
        int[][] m = new int[][]{{5, 7, 50}, {0, 6, 8}, {0, 0, 10}};// data of first 3 years
        int alpha = alpha(m, c);
        System.out.println("alpha=" + alpha);
    }


}

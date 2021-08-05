package com.future.algoriithm.dynamic;

/**
 * 零钱兑换问题
 * 现有面值1分、5分、20分、25分的硬币，请问，用怎样的兑换方式可使得41分的硬币个数最少。
 * <p>
 * 主旨思想：
 * 设 f(n) 是兑换n个硬币的最优解，那么f(n-1)是兑换n-1个硬币的最优解
 * 其主要解决的问题有两个：
 * 1、f(n)与f(n-1)的关系，
 * 2、边界值f(1)的值
 * <p>
 * 在兑换硬币的方案中
 * f(1)的最优解是1，f(2)、f(3)、f(4)的最优解分别是2、3、4、
 * f(5)的最优解是1，f(6)、f(7)、f(8)、f(9)的最优解分别是f(5+1), f(5+2),f (5+3), f(5+4)
 * f(10)的最优解是f(5+5), f(11)的最优解是f(5+6)
 * 以此类推
 * 由上可知，设硬币数组为 S(k) = {k1, k2, k3, k4……}
 * 则已知条件可转化为 f(k1) = f(k2) = f(k3) = f(k4) = f(k..) = 1;
 * 则以 k1 兑换零钱的个数为
 * f(n - k1) = f(n - k1) + f(k1), 其中 k1 ∋ S(K)
 * 以此类推
 * f(n - k2) = f(n - k2) + f(k2)
 * f(n - k3) = f(n - k3) + f(k3)
 * <p>
 * 而最少兑换零钱方案为：
 * min { f(n-k1), f(n-k2), f(n-k3), f(n-k4)...}
 *
 * @author jayzhou
 */
public class CoinChange {

    /**
     * beta方案
     * 真正意义上的动态规划方案
     * 体现了动态规划的思想
     * <p>
     * 输入：需要兑换零钱的币数n；可兑换的面值数组faces
     * 输出：返回最少兑换的硬币个数。
     * dp[n]：表示兑换n个硬币所需要的最少硬币数
     */
    public static int beta(int n, int... faces) {
        if (n < 1) return 0;
        // dp(n) 表示兑换n个硬币所需要最少的硬币个数
        int[] dp = new int[n + 1];
//        dp[0] = 0; 只是为了辅助计算，在Java中，数组默认值本身就为0，故在此可不需要
        for (int coin = 1; coin < dp.length; coin++) {
            int min = Integer.MAX_VALUE;
            // dp[n] = min { dp[n - face[0]], dp[n - face[1]], dp[n - face[2]], dp[n - face[3]]...}
            for (int face : faces) {
                if (coin < face) continue;
                // dp(coin) = dp(coin - face) + dp(face) 且 dp(face) = 1;
                min = Math.min(min, dp[coin - face] + 1);
            }
            dp[coin] = min;
        }
        return dp[n];
    }

    /**
     * **********************************************************************
     * ****************** 以下方案是动态规划思想的演进方案 **********************
     * **********************************************************************
     *
     *
     *
     * <p>
     * 1、暴力递归方案;
     * 自顶向下，出现了重叠子问题
     * 比较兑换的所有可能性中选取一个最小值
     */
    private static int recursive(int n) {
        if (n < 0) return Integer.MAX_VALUE;
        if (n == 25 || n == 20 || n == 5 || n == 1) return 1;
        int min1 = Math.min(recursive(n - 25), recursive(n - 20));
        int min2 = Math.min(recursive(n - 5), recursive(n - 1));
        return Math.min(min1, min2) + 1;
    }

    /**
     * 递归优化方案
     * 规避大量重复计算
     * 记忆化搜索（自顶向下）
     */
    private static int recursiveOpt(int n) {
        int[] array = new int[n + 1];
        return recursiveOpt(n, array);
    }

    private static int recursiveOpt(int n, int[] array) {
        if (n < 0) return Integer.MAX_VALUE;
        if (n == 25 || n == 20 || n == 5 || n == 1) return 1;
        if (array[n] == 0) {
            int min1 = Math.min(recursive(n - 25), recursive(n - 20));
            int min2 = Math.min(recursive(n - 5), recursive(n - 1));
            array[n] = Math.min(min1, min2) + 1;
        }
        return array[n];
    }

    /**
     * 迭代方案(递推+自底向上)
     * 规避递归的函数栈调用开销
     */
    private static int iteration(int n) {
        if (n < 1) return 0;
        int[] array = new int[n + 1];
        array[1] = 1;
        if (n >= 5) array[5] = 1;
        if (n >= 20) array[20] = 1;
        if (n >= 25) array[25] = 1;
        for (int i = 2; i <= n; i++) {
            int min = array[i];
            if (min == 0) {
                min = Integer.MAX_VALUE;
            }
            if (min > array[i - 1]) {
                min = array[i - 1] + 1;
            }
            if (i > 5 && min > array[i - 5]) {
                min = array[i - 5] + 1;
            }
            if (i > 20 && min > array[i - 20]) {
                min = array[i - 20] + 1;
            }
            if (i > 25 && min > array[i - 25]) {
                min = array[i - 25] + 1;
            }
            array[i] = min;
        }
        return array[n];
    }

    /**
     * 迭代优化方案，支持动态传入硬币面值的数组
     */
    private static int iterationOpt(int n, int... coins) {
        int[] array = new int[n + 1];
        for (int coin : coins) {
            if (coin < array.length)
                array[coin] = 1;
        }
        for (int i = 1; i < array.length; i++) {
            int min = array[i];
            if (min == 0) min = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i > c && min > array[i - c]) {
                    min = array[i - c] + 1;
                }
            }
            array[i] = min;
        }
        return array[n];
    }

    /**
     * alpha方案
     * 和迭代优化方案相同，不同的是多输出了具体的兑换面值
     */
    private static int alpha(int n, int... coins) {
        // dp(n) 表示兑换n个硬币所需要最少的硬币个数
        int[] dp = new int[n + 1];
        // faces是兑换币种的面值数组，其数组最后一个值表示最后兑换的面值，第一个值表示第一次兑换的面值
        int[] faces = new int[dp.length];
        for (int coin : coins) {
            if (coin < dp.length) {
                dp[coin] = 1;
                faces[coin] = coin;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            int min = dp[i];
            if (min == 0) min = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i > c && min > dp[i - c]) {
                    min = dp[i - c] + 1;
                    faces[i] = c;
                }
            }
            dp[i] = min;
        }
        int k = n;
        while (k > 0) {
            System.out.println("k=" + k + ", faces[k]=" + faces[k]);
            k = k - faces[k];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 41;
        int coinCount = recursive(n);
        System.out.println("recursive=" + coinCount);

        coinCount = recursiveOpt(n);
        System.out.println("recursiveOpt=" + coinCount);

        coinCount = iteration(n);
        System.out.println("iteration=" + coinCount);

        coinCount = iterationOpt(n, 1, 5, 20, 25);
        System.out.println("iterationOpt=" + coinCount);

        coinCount = alpha(n, 1, 5, 20, 25);
        System.out.println("alpha=" + coinCount);

        coinCount = beta(n, 1, 5, 20, 25);
        System.out.println("beta=" + coinCount);
    }
}

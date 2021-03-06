package com.future.algoriithm.dynamic;

/**
 * 动态规划
 * 动态规划中的"动态"可以理解为是"会变化的状态"
 * 1、定义状态（状态是原问题的解、子问题的解）
 * 比如定义dp(i)的含义
 * 2、设置初始状态(边界)
 * 比如设置dp(0)的值
 * 3、确定状态转移方程
 * 比如确定dp(i)和dp(i-1)的关系
 * <p>
 * 动态规划解决问题的过程可大致概括为
 * 1、将复杂的愿问题拆解称若干个较为简单的子问题
 * 2、每个子问题仅仅解决一次，并保存它们的解
 * 3、最后推导出原问题的解
 * <p>
 * 适合用动态规划解决的问题，通常具备2个特点：
 * 1、最优子结构（最优化原理）：通过求解子问题的最优解，可以获得愿问题的最优解
 * 2、无后效性。
 * a、某阶段的状态一旦确定，则此后过程的演变不再受此前各状态及决策的影响（未来和过去无关）
 * b、在推导后面阶段的状态时，只关心前面阶段的具体状态值，不关心这个状态是怎么一步一步推导过来的。
 *
 * @author jayzhou
 */
public interface DynamicProgramming {

    static void printDp(int[][] dp) {
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}

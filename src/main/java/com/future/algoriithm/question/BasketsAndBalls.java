package com.future.algoriithm.question;

import com.future.algoriithm.utils.PrintUtils;
import com.future.algoriithm.utils.Printable;

/**
 * ou have several identical balls that you wish to place in several baskets. Each basket has the same maximum capacity.
 * You are given an int baskets, the number of baskets you have. You are given an int capacity, the maximum capacity of each basket.
 * Finally you are given an int balls, the number of balls to sort into baskets. Return the number of ways you can divide the balls into baskets.
 * If this cannot be done without exceeding the capacity of the baskets, return 0.
 * Each basket is distinct, but all balls are identical. Thus, if you have two balls to place into two baskets, you could have (0, 2), (1, 1), or (2, 0),
 * so there would be three ways to do this.
 * 你有几个同样的球，你希望把它放到几个篮子里。每个篮子有相同的容量。给出int 型的baskets,代表篮子的数量。给出int型的 capacity，代表每个篮子的最大容量。给出int型的balls,
 * 表示归类到篮子里的球的数量。求：球归类到篮子里的方式的数量。如果不能完全存放到篮子中，无法划分，返回0。
 * <p>
 * Method: countWays Parameters: int, int, int Returns: int
 * Method signature:
 * int countWays(int baskets, int capacity, int balls)
 * (be sure your method is public)
 * Constraints
 * <p>
 * baskets will be between 1 and 5, inclusive.
 * 篮子数量在1到5之间
 * -
 * capacity will be between 1 and 20, inclusive.
 * 容量在1到20之间
 * -
 * balls will be between 1 and 100, inclusive.
 * 球在1到100之间
 * Examples
 * 例子：
 * 0) 2 20 2 Returns: 3
 * The example from the problem statement.
 * 该例子同问题描述。
 * 1) 3 20 1 Returns: 3
 * We have only 1 ball, so we must choose which of the three baskets to place it in.
 * 只有1个球，我们必须3选一
 * 2) 3 20 2 Returns: 6
 * We can place both balls in the same basket (3 ways to do this), or one ball in each of two baskets (3 ways to do this).
 * 可以把两个球放在相同的篮子里（有三种方式），或者两个篮子里各放1球（有三种方法）
 * 3) 1 5 10 Returns: 0
 * We have more balls than our basket can hold.
 * 球的数量大于篮子的容量
 * 4) 4 5 10 Returns: 146
 */
public class BasketsAndBalls {

    /**
     * 该解决方案的思想如下：
     * 数据结构：一维数组：baskets[basketNo] = balls;
     * baskets数组表示篮子个数。no：表示篮子编号，balls表示每个篮子装的球的个数
     */
    private static class Solution1 implements Printable {

        private final int basketLength;
        private final int capacity;
        private final int[] baskets;
        private final int balls;
        private int countWays = 0;

        Solution1(int baskets, int capacity, int balls) {
            this.basketLength = baskets;
            this.capacity = capacity;
            this.balls = balls;
            this.baskets = new int[baskets];
        }

        public int getSortedBalls(int basketNo) {
            int sortedBalls = 0;
            for (int i = 0; i < basketNo; i++) {
                sortedBalls += baskets[i];
            }
            return sortedBalls;
        }

        public void put(int basketNo) {
            int sortedBalls = getSortedBalls(basketNo);
            int remainBalls = balls - sortedBalls;
            if (remainBalls <= 0 )  {
                countWays++;
                return;
            }
            // 表示最后一个篮子
            if (basketNo == basketLength - 1) {
                if (capacity >= remainBalls) {
                    countWays++;
                }
                return;
            }
            int maxBalls = Math.min(remainBalls, capacity);
            for (int i = 0; i <= maxBalls; i++) {
                baskets[basketNo] = i;
                put(basketNo + 1);
            }
        }

        public int getCountWays() {
            return countWays;
        }

        @Override
        public void println() {

        }
    }

    public static int countWays(int baskets, int capacity, int balls) {
        Solution1 solution1 = new Solution1(baskets, capacity, balls);
        solution1.put(0);
        return solution1.getCountWays();
    }

    public static void test() {
        int result = countWays(2, 20, 2);
        if (result != 3) {
            PrintUtils.error("The program was wrong, expect should be 3, but real is " + result);
        } else {
            PrintUtils.printf("Congratulations! The Result is %d \n", result);
        }
        result = countWays(3, 20, 1);
        if (result != 3) {
            PrintUtils.error("The program was wrong, expect should be 3, but real is " + result);
        } else {
            PrintUtils.printf("Congratulations! The Result is %d \n", result);
        }

        result = countWays(3, 20, 2);
        if (result != 6) {
            PrintUtils.error("The program was wrong, expect should be 6, but real is " + result);
        } else {
            PrintUtils.printf("Congratulations! The Result is %d \n", result);
        }

        result = countWays(1, 5, 10);
        if (result != 0) {
            PrintUtils.error("The program was wrong, expect should be 0, but real is " + result);
        } else {
            PrintUtils.printf("Congratulations! The Result is %d \n", result);
        }

        result = countWays(4, 5, 10);
        if (result != 146) {
            PrintUtils.error("The program was wrong, expect should be 146, but real is " + result);
        } else {
            PrintUtils.printf("Conguratulations! The Result is %d \n", result);
        }
    }

    public static void main(String[] args) {
        test();
    }
}

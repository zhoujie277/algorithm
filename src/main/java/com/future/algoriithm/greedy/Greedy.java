package com.future.algoriithm.greedy;

import com.future.datastruct.heap.PriorityHeap;
import com.future.datastruct.list.DynamicArray;
import com.future.utils.PrintUtils;

/**
 * 贪心算法
 * <p>
 * 贪婪算法主体思想：每一步都采取当前状态下的局部最优解，从而希望得到全局最优解
 * 值得注意的是：这个"局部最优解"指的是当前状态(眼前)最优解，和动态规划获取子问题的最优解大不相同
 *
 * 优点：简单、高效，不需要穷举所有可能，通常作为其它算法的辅助算法使用
 * 缺点：不一定能得到全局最优解
 * <p>
 * 贪心算法的典型应用：
 * 哈夫曼树、最小生成树（Prim、Kruskal算法）、Dijkstra最短路径算法
 *
 * @author jayzhou
 */
public class Greedy {

    /**
     * 算法举例：
     * 加勒比海盗问题
     * 问题描述：有一天，海盗们截获了一艘装满各种各样古董的货船，每一件古董都价值连城。
     * 海盗船的载重量为W，每一件古董的重量为 Wi，问：如何才能使得海盗船装最多的古董，从而价值最大。
     * 设：海盗船载重W=30；Wi分别为3、5、4、10、7、14、2、11.
     * 函数返回选取的古董
     */
    public static Integer[] shipment() {
        Integer[] w = new Integer[]{3, 5, 4, 10, 7, 14, 2, 11};
        PriorityHeap<Integer> queue = new PriorityHeap<>(w);
        int sum = 0;
        DynamicArray<Integer> antique = new DynamicArray<>();
        while (!queue.isEmpty()) {
            Integer min = queue.delMin();
            if (sum + min > 30) break;
            antique.add(min);
            sum += min;
        }
        Integer[] resultArr = new Integer[antique.size()];
        antique.toArray(resultArr);
        return resultArr;
    }

    /**
     * 假设有25分、10分、5分、1分的硬币，现要找给客户41分零钱，如何使硬币个数最少
     */
    public static Integer[] coinChange(int money) {
        Integer[] coins = new Integer[]{25, 10, 5, 1};
        DynamicArray<Integer> result = new DynamicArray<>();
        PriorityHeap<Integer> queue = new PriorityHeap<>(coins);
        while (!queue.isEmpty()) {
            Integer max = queue.delMax();
            while (money >= max) {
                result.add(max);
                money -= max;
            }
            if (money <= 0) break;
        }
        Integer[] resultArr = new Integer[result.size()];
        result.toArray(resultArr);
        return resultArr;
    }

    public static void main(String[] args) {
        // shipment
        Integer[] shipment = shipment();
        PrintUtils.println(shipment);

        //coinChange
        Integer[] coinChange = coinChange(19);
        PrintUtils.println(coinChange);
    }
}

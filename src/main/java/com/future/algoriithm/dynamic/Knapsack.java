package com.future.algoriithm.dynamic;

import com.future.datastruct.list.DynamicArray;

/**
 * 0-1背包
 * <p>
 * 有n件物品和一个最大承重为W的背包，每件物品的重量是Wi，价值是Vi；
 * 在保证总重量不超过W的前提下，将哪几件物品装入背包，可使得背包总价值最大。
 * 注意：每个物品只有1件，也就是每个物品只能选择0件或1件，因此称为0-1背包问题。
 *
 * @author jayzhou
 */
public class Knapsack {

    static class Goods {
        int weight;
        int value;
        int valueDensity;

        public Goods(int weight, int value) {
            this.weight = weight;
            this.value = value;
            valueDensity = value / weight;
        }
    }

    private final int totalWeight;

    private final DynamicArray<Goods> goodsList = new DynamicArray<>();

    public Knapsack(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void addGoods(Goods goods) {
        goodsList.add(goods);
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    /**
     * 设背包总重量为150，7个物品如下初始化
     */
    public void initData() {
        Knapsack knapsack = new Knapsack(150);
        knapsack.addGoods(new Goods(35, 10));
        knapsack.addGoods(new Goods(30, 40));
        knapsack.addGoods(new Goods(60, 30));
        knapsack.addGoods(new Goods(50, 50));
        knapsack.addGoods(new Goods(40, 35));
        knapsack.addGoods(new Goods(10, 40));
        knapsack.addGoods(new Goods(25, 30));
    }
}

package com.future.algoriithm.greedy;

import com.future.datastruct.heap.PriorityHeap;
import com.future.datastruct.list.DynamicArray;
import com.future.utils.PrintUtils;

import java.util.Comparator;

/**
 * 0-1背包
 * <p>
 * 有n件物品和一个最大承重为W的背包，每件物品的重量是Wi，价值是Vi；
 * 在保证总重量不超过W的前提下，将哪几件物品装入背包，可使得背包总价值最大。
 * 注意：每个物品只有1件，也就是每个物品只能选择0件或1件，因此称为0-1背包问题。
 *
 * @author jayzhou
 */
public class Knapsack01 {

    static class Goods {
        int weight;
        int value;
        float valueDensity;
        int id;

        public Goods(int id, int weight, int value) {
            this.id = id;
            this.weight = weight;
            this.value = value;
            valueDensity = value * 1f / weight;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "id=" + id +
                    ", weight=" + weight +
                    ", value=" + value +
                    ", valueDensity=" + valueDensity +
                    '}';
        }
    }

    private final int totalWeight;

    private final DynamicArray<Goods> goodsList = new DynamicArray<>();

    public Knapsack01(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void addGoods(Goods goods) {
        goodsList.add(goods);
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public Goods[] getGoods() {
        Goods[] array = new Goods[goodsList.size()];
        return goodsList.toArray(array);
    }

    /**
     * 设背包总重量为150，7个物品如下初始化
     */
    public static void initData(Knapsack01 knapsack) {
        knapsack.addGoods(new Goods(1, 35, 10));
        knapsack.addGoods(new Goods(2, 30, 40));
        knapsack.addGoods(new Goods(3, 60, 30));
        knapsack.addGoods(new Goods(4, 50, 50));
        knapsack.addGoods(new Goods(5, 40, 35));
        knapsack.addGoods(new Goods(6, 10, 40));
        knapsack.addGoods(new Goods(7, 25, 30));
    }

    public static DynamicArray<Goods> greedy(Knapsack01 knapsack, Comparator<Goods> comparator) {
        PriorityHeap<Goods> heap = new PriorityHeap<>(knapsack.getGoods(), comparator);
        DynamicArray<Goods> result = new DynamicArray<>();
        int weight = 0;
        do {
            Goods goods = heap.delMax();
            if (weight + goods.weight > knapsack.getTotalWeight()) {
                continue;
            }
            weight += goods.weight;
            result.add(goods);
        } while (!heap.isEmpty() && weight < knapsack.getTotalWeight());
        return result;
    }

    public static void main(String[] args) {
        Knapsack01 knapsack = new Knapsack01(150);
        initData(knapsack);
        PrintUtils.println("----------------Value--------------------");
        DynamicArray<Goods> result = greedy(knapsack, Comparator.comparingInt(o -> o.value));
        for (int i = 0; i < result.size(); i++) {
            PrintUtils.println(result.get(i));
        }
        PrintUtils.println("----------------Weight--------------------");
        result = greedy(knapsack, (o1, o2) -> o2.weight - o1.weight);
        for (int i = 0; i < result.size(); i++) {
            PrintUtils.println(result.get(i));
        }

        PrintUtils.println("---------------ValueDensity---------------------");
        result = greedy(knapsack, Comparator.comparingDouble(o -> o.valueDensity));
        for (int i = 0; i < result.size(); i++) {
            PrintUtils.println(result.get(i));
        }
    }
}

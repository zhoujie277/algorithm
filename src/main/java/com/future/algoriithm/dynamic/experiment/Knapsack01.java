package com.future.algoriithm.dynamic.experiment;

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
public class Knapsack01 {

    static class Goods {
        int weight;
        int value;

        public Goods(int weight, int value) {
            this.weight = weight;
            this.value = value;
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

    /**
     * 设背包总重量为150，7个物品如下初始化
     */
    public static void initData(Knapsack01 knapsack) {
        knapsack.addGoods(new Goods(35, 10));
        knapsack.addGoods(new Goods(30, 40));
        knapsack.addGoods(new Goods(60, 30));
        knapsack.addGoods(new Goods(50, 50));
        knapsack.addGoods(new Goods(40, 35));
        knapsack.addGoods(new Goods(10, 40));
        knapsack.addGoods(new Goods(25, 30));
    }

    private static class Bag {
        int capacity = 0;
        Goods[] goods;
        int value;
        int weight;
        int lastIndex = -1;

        public Bag(int size, int capacity) {
            this.capacity = capacity;
            goods = new Goods[size];
        }

        public void push(int index, Goods g) {
            this.goods[index] = g;
            if (index > lastIndex)
                lastIndex = index;
            value += g.value;
            weight += g.weight;
        }

        public int getEnd() {
            return lastIndex;
        }

        public Goods pop(int index) {
            if (lastIndex == -1 || index == -1) return null;
            Goods good = goods[index];
            value -= good.value;
            weight -= good.weight;
            goods[index] = null;
            updateLastIndex();
            return good;
        }

        private void updateLastIndex() {
            for (int i = lastIndex; i >= 0; i--) {
                if (goods[i] != null) {
                    lastIndex = i;
                    return;
                }
            }
            lastIndex = -1;
        }

        public int getValue() {
            return value;
        }

        public void tryPush(int index, Goods good) {
            if (good.weight + this.weight <= capacity) {
                push(index, good);
            }
        }
    }

    /**
     * 穷举装入背包物品的所有可能，找出最大价值
     */
    private static void exhaustively(Knapsack01 knapsack) {
        int maxValue = 0;
        DynamicArray<Goods> goodsList = knapsack.goodsList;
        int len = goodsList.size();
        Bag bag = new Bag(len, knapsack.getTotalWeight());
        int startIndex = 0;
        int iteration = 0;
        int end;
        do {
            for (int j = startIndex; j < len; j++) {
                iteration += 1;
                Goods goods = goodsList.get(j);
                bag.tryPush(j, goods);
            }
            maxValue = Math.max(maxValue, bag.getValue());
            end = bag.getEnd();
            startIndex = end + 1;
//            System.out.println("enIndex = " + end);
        } while (bag.pop(end) != null);
        System.out.println("iteration=" + iteration);
        System.out.println("maxValue=" + maxValue);
    }

    static class Recursive {
        /*
        new Goods(35, 10));
        knapsack.addGoods(new Goods(30, 40));
        knapsack.addGoods(new Goods(60, 30));
        knapsack.addGoods(new Goods(50, 50));
        knapsack.addGoods(new Goods(40, 35));
        knapsack.addGoods(new Goods(10, 40));
        knapsack.addGoods(new Goods(25, 30));
         */
        static int[] weights = new int[]{35, 30, 60, 50, 40, 10, 25};
        static int[] values = new int[]{10, 40, 30, 50, 35, 40, 30};

        static int recursionCount = 0;

        /**
         * @param index        装第几个物品
         * @param remainWeight 要装进背包的剩余数量
         * @return 指定空间能装的最大价值
         */
        public static int execute(int index, int remainWeight) {
            if (index == weights.length) return 0;
            recursionCount++;
            int weight = weights[index];
            if (weight > remainWeight) {
                //装该物品剩余空间不够，尝试装下一个
                return execute(index + 1, remainWeight);
            } else {
                // 能装下，就比较能装下，和不装的最大值
                int v1 = execute(index + 1, remainWeight - weight) + values[index];
                // 此处会有重复子问题的计算，可被优化
                int v2 = execute(index + 1, remainWeight);
                return Math.max(v1, v2);
            }
        }

        /**
         * 将选过的结果缓存起来。
         * n表示物品数组的最后一个物品。
         * remainWeight，表示总重量
         * cache[i][j]: 表示选取前i个物品，容量为J的最大价值。
         */
        public static int cache(int remainWeight) {
            recursionCount = 0;
            int n = weights.length;
            int[][] cache = new int[n + 1][remainWeight + 1];
            for (int i = 1; i < cache.length; i++) {
                for (int j = 1; j < cache[i].length; j++) {
                    cache[i][j] = -1;
                }
            }

            int result = cache(n, remainWeight, cache);
            for (int i = 0; i < cache.length; i++) {
                for (int j = 0; j < cache[i].length; j++) {
                    System.out.print(cache[i][j] + " ");
                }
                System.out.println();
            }

            return result;
        }

        public static int cache(int i, int remainWeight, int[][] cache) {
            if (i == 0 || remainWeight == 0) return 0;
            if (cache[i][remainWeight] == -1) {
                recursionCount++;
                if (weights[i - 1] > remainWeight) {
                    cache[i][remainWeight] = cache(i - 1, remainWeight, cache);
                } else {
                    int v1 = cache(i - 1, remainWeight - weights[i - 1], cache) + values[i - 1];
                    int v2 = cache(i - 1, remainWeight, cache);
                    cache[i][remainWeight] = Math.max(v1, v2);
                }
            }
            return cache[i][remainWeight];
        }
    }

    public static void main(String[] args) {
        Knapsack01 knapsack = new Knapsack01(150);
        initData(knapsack);
        exhaustively(knapsack);

        System.out.println("-------------Recursive-------------------");
        int maxValue = Recursive.execute(0, knapsack.getTotalWeight());
        System.out.println("recursionCount=" + Recursive.recursionCount);
        System.out.println("maxValue=" + maxValue);

        System.out.println("-----------Recursive cache---------------------");
        int cache = Recursive.cache(knapsack.getTotalWeight());
        System.out.println("recursionCount=" + Recursive.recursionCount);
        System.out.println("cache, maxValue=" + cache);
    }


}







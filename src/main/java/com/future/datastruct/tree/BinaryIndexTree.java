package com.future.datastruct.tree;

import java.util.Arrays;

/**
 * 树状数组，也称为二进制索引树
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class BinaryIndexTree {

    private final int[] data;

    public BinaryIndexTree(int size) {
        data = new int[size + 1];
    }

    public BinaryIndexTree(int[] array) {
        this(array.length);
        System.arraycopy(array, 0, data, 1, array.length);
        //init complex: O(n)
        int[] sum = new int[array.length + 1]; // 前n项的前缀和
        for (int i = 1; i <= array.length; i++) {
            sum[i] = sum[i - 1] + array[i - 1];
            data[i] = sum[i] - sum[i - (i & (-i))];
        }
    }

    /**
     * 区间和查询
     */
    public int rangeSumQuery(int from, int to) {
        return rangeSumQuery(to) - rangeSumQuery(from - 1);
    }

    /**
     * 查询树
     * 计算从0到index的和
     */
    public int rangeSumQuery(int index) {
        int sum = 0;
        while (index > 0) {
            sum += data[index];
            index -= index & (-index);
        }
        return sum;
    }

    /**
     * 单点更新
     * 更新树
     */
    public void change(int index, int value) {
        while (index < data.length) {
            data[index] += value;
            index += index & (-index);
        }
    }

    public int size() {
        return data.length - 1;
    }

    @Override
    public String toString() {
        return "BinaryIndexTree{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 1, 3, 2, 6, 5};
        BinaryIndexTree tree = new BinaryIndexTree(array.length);
        //init complex: NlogN
        for (int a = 0; a < array.length; a++) {
            tree.change(a + 1, array[a]);
        }
        System.out.println(tree);
        System.out.println(tree.rangeSumQuery(2));

    }
}

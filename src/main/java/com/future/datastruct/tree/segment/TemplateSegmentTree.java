package com.future.datastruct.tree.segment;

import com.future.utils.PrintUtils;

/**
 * 线段树的数据结构模版
 */
class TemplateSegmentTree {

    // 以数组下标作为区间
    private final int[] tree;
    private final int[] array;

    public TemplateSegmentTree(int[] data) {
        int len = data.length;
        // len是叶子结点数量。同时线段树是一棵真二叉树，所以满足 n=2*len-1，
        // 而线段树空间自少要包含一棵满二叉树的结点数量.
        // 也就是最大结点数量为：最接近且 ≥（2*len-1）的2进制数
        int ceilBinary = ceilBinary((len << 1) - 1);
        tree = new int[ceilBinary];
        array = new int[len + 1];
        System.arraycopy(data, 0, array, 1, len);
    }

    /**
     * 取出最靠近且≥len的二进制数
     */
    private int ceilBinary(int len) {
        int n = len - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    /**
     * 构建线段树
     *
     * @param p     根结点位置
     * @param left  区间左端点
     * @param right 区间右端点
     */
    public void build(int p, int left, int right) {
        if (left == right) {
            tree[p] = array[left];
            return;
        }
        int mid = (left + right) >> 1;
        build(p << 1, left, mid);
        build(p << 1 | 1, mid + 1, right);
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }

    /**
     * @param p     根结点位置
     * @param left  区间左端点
     * @param right 区间右端点
     * @param x     要查找的值，也就是要被更新的旧值所在的区间
     * @param num   要更新的值，可以是替换或加减（此处是加减演示）
     */
    public void change(int p, int left, int right, int x, int num) {
        if (left == right) {
            tree[p] += num;
            return;
        }
        int mid = (left + right) >> 1;
        if (x <= mid) {
            change(p << 1, left, mid, x, num);
        } else {
            change(p << 1 | 1, mid + 1, right, x, num);
        }
        // 更新父结点的权值
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }

    /**
     * 查找所在区间的权值
     *
     * @param p     区间根结点位置
     * @param left  区间左端点
     * @param right 区间右端点
     * @param x     要查找的区间左端点
     * @param y     要查找的区间右端点
     */
    public int find(int p, int left, int right, int x, int y) {
        if (x <= left && y >= right) {
            return tree[p];
        }
        int mid = (left + right) >>> 1;
        if (y <= mid) {
            return find(p << 1, left, mid, x, y);
        } else if (x >= mid) {
            return find(p << 1 | 1, mid + 1, right, x, y);
        }
        return find(p << 1, left, mid, x, mid) + find(p << 1 | 1, mid + 1, right, mid + 1, y);
    }

    void println() {
        PrintUtils.println(tree);
    }

    public static void main(String[] args) {
//        int[] array = new int[]{5, 6, 7, 8, 9};
        int[] array = new int[]{1, 2, 3, 4, 5};
        TemplateSegmentTree tree = new TemplateSegmentTree(array);
        tree.build(1, 1, 5);
        tree.println();
        // 数组下标表示区间，数组元素表示区间结点的权值。
        int[] twoArr = new int[]{1, 2, 3, 4, 5};
        TemplateSegmentTree two = new TemplateSegmentTree(twoArr);
        two.build(1, 0, 4);
        tree.println();
    }
}

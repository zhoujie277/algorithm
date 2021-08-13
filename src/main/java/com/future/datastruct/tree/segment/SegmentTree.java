package com.future.datastruct.tree.segment;


import com.future.utils.PrintUtils;

/**
 * 线段树
 * 线段树通常用于解决连续区间的插入、查找、统计、查询等操作
 * 该示例演示了一个用作区间最小值和区间和的结点。
 *
 * @author jayzhou
 */
public class SegmentTree {

    // 原 数据
    private final int[] data;
    // 用数组下标划分区间
    private final Node[] nodes;

    public SegmentTree(int[] array) {
        int len = array.length;
        data = new int[len];
        System.arraycopy(array, 0, data, 0, len);
        // len是叶子结点数量。同时线段树是一棵真二叉树，所以满足: n=2*len-1，
        // 而线段树空间自少要包含一棵满二叉树的结点数量.
        // 也就是最大结点数量为：最接近且 ≥（2*len-1）的2进制数
        int size = ceilBinary((len << 1) - 1);
        nodes = new Node[size];
        // index=1，将nodes的第一个结点作为空，留着用做哨兵结点
        build(1, 0, len - 1);
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

    private void build(int index, int left, int right) {
        nodes[index] = new Node();
        nodes[index].left = left;
        nodes[index].right = right;
        if (left == right) {
            nodes[index].sum = data[left];
            nodes[index].min = data[left];
            return;
        }
        int mid = (left + right) >>> 1;
        build(index << 1, left, mid);
        build(index << 1 | 1, mid + 1, right);
        nodes[index].sum = nodes[index << 1].sum + nodes[index << 1 | 1].sum;
        nodes[index].min = Math.min(nodes[index << 1].min, nodes[index << 1 | 1].min);
    }

    /**
     * 区间和查询
     */
    public int rangeSumQuery(int left, int right) {
        return rangeSumQuery(1, left, right);
    }

    private int rangeSumQuery(int index, int left, int right) {
        Node node = nodes[index];
        if (node.left == left && node.right == right) {
            return node.sum;
        }
        int mid = (node.left + node.right) >> 1;
        if (right <= mid) {
            return rangeSumQuery(index << 1, left, right);
        } else if (left > mid) {
            return rangeSumQuery(index << 1 | 1, left, right);
        }
        return rangeSumQuery(index << 1, left, mid) + rangeSumQuery(index << 1 | 1, mid + 1, right);
    }

    /**
     * 区间最小值查询
     */
    public int rangeMinQuery(int left, int right) {
        return rangeMinQuery(1, left, right);
    }

    private int rangeMinQuery(int index, int left, int right) {
        Node node = nodes[index];
        if (node.left == left && node.right == right) {
            return node.min;
        }
        int mid = (node.left + node.right) >> 1;
        if (right <= mid) { //左区间查询
            return rangeMinQuery(index << 1, left, right);
        } else if (left > mid) { //右区间查询
            return rangeMinQuery(index << 1 | 1, left, right);
        }
        return Math.min(rangeMinQuery(index << 1, left, mid), rangeMinQuery(index << 1 | 1, mid + 1, right));
    }

    /**
     * 指定原数组的索引值被更新
     *
     * @param subscript 数组下标，即区间索引
     * @param value     需要被更新的值
     */
    public void change(int subscript, int value) {
        // 需要找到线段树上所在的区间
        change(subscript, subscript, value);
    }

    /**
     * 区间更新
     *
     * @param from  区间左索引
     * @param to    区间右索引
     * @param value 需要被更新的值
     */
    public void change(int from, int to, int value) {
        change(1, from, to, value);
    }

    private void change(int index, int left, int right, int value) {
        Node node = nodes[index];
        if (node.left == left && node.right == right) {
            change(node, value);
            return;
        }
        int mid = (node.left + node.right) >>> 1;
        if (right <= mid) {
            change(index << 1, left, right, value);
        } else if (left > mid) {
            change(index << 1 | 1, left, right, value);
        }
        // 更新父结点
        nodes[index].sum = nodes[index << 1].sum + nodes[index << 1 | 1].sum;
        nodes[index].min = Math.min(nodes[index << 1].min, +nodes[index << 1 | 1].min);
    }

    private void change(Node node, int value) {
        node.sum = value;
        node.min = value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SegmentTree, size=").append(nodes.length).append("\n");
        for (Node node : nodes) {
            builder.append(node);
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * 区间结点
     * 该示例保存了区间和以及区间最小值
     * 区间 [left,right]
     */
    private static class Node {
        int sum;
        int min;
        int left;
        int right;

        @Override
        public String toString() {
            return "Node{" +
                    "sum=" + sum +
                    ", min=" + min +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    public static void main(String[] args) {
//        int[] array = new int[]{41, 91, 29, 28, 82, 98, 92, 69, 12, 15};
        int[] array = new int[]{18, 6, 4, 16, 2, 10, 19, 7, 8, 12};
        SegmentTree tree = new SegmentTree(array);
//        PrintUtils.println(tree);
        int total = tree.rangeSumQuery(0, 9);
        PrintUtils.println("rangeSumQuery(0,9)=" + total);
        int sum = tree.rangeSumQuery(4, 6);
        PrintUtils.println("rangeSumQuery(4,6)=" + sum);
        int min = tree.rangeMinQuery(0, 7);
        PrintUtils.println("min=" + min);
        tree.change(2, 1);
        int changeMin = tree.rangeMinQuery(0, 7);
        PrintUtils.println("changeMin=" + changeMin);
        int changeTotal = tree.rangeSumQuery(0, 9);
        PrintUtils.println("rangeSumQuery(0,9)=" + changeTotal);
        int changeSum = tree.rangeSumQuery(4, 6);
        PrintUtils.println("rangeSumQuery(4,6)=" + changeSum);
    }
}







package com.future.datastruct.tree.segment;

/**
 * 线段树
 * 线段树的链表存储实现
 * 线段树通常用于统计、查询等操作，比如求区间和、求区间最大值等的操作。
 * 因为其最值信息或者求和信息可以保存在线段树的区间结点上，因此可以提高效率
 * 比如动态规划在求解线性模型中反复需要求 dp[1],dp[2]...dp[n]之间的最大值。
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class LinkedSegmentTree {

    private final Node root;
    private final int[] data;

    public LinkedSegmentTree(int[] array) {
        int len = array.length;
        data = new int[len];
        System.arraycopy(array, 0, data, 0, len);
        root = build(0, len - 1);
    }

    private Node build(int begin, int end) {
        Node node = new Node(begin, end);
        if (begin == end) {
            node.min = node.sum = data[begin];
            return node;
        }
        int mid = (begin + end) >> 1;
        node.left = build(begin, mid);
        node.right = build(mid + 1, end);
        node.sum = node.left.sum + node.right.sum;
        node.min = Math.min(node.left.min, node.right.min);
        return node;
    }

    public int rangeSumQuery(int begin, int end) {
        return rangeSumQuery(root, begin, end);
    }

    private int rangeSumQuery(Node node, int begin, int end) {
        if (node.begin == begin && node.end == end) {
            return node.sum;
        }
        int mid = (node.begin + node.end) >> 1;
        if (end <= mid) {
            //区间左查询
            return rangeSumQuery(node.left, begin, end);
        } else if (begin > mid) {
            return rangeSumQuery(node.right, begin, end);
        }
        return rangeSumQuery(node.left, begin, mid) + rangeSumQuery(node.right, mid + 1, end);
    }

    public int rangeMinQuery(int begin, int end) {
        return rangeMinQuery(root, begin, end);
    }

    private int rangeMinQuery(Node node, int begin, int end) {
        if (node.begin == begin && node.end == end) {
            return node.min;
        }
        int mid = (node.begin + node.end) >> 1;
        if (end <= mid) {
            return rangeMinQuery(node.left, begin, end);
        } else if (begin > mid) {
            return rangeMinQuery(node.right, begin, end);
        }
        return Math.min(rangeMinQuery(node.left, begin, mid), rangeMinQuery(node.right, mid + 1, end));
    }

    /**
     * 单点更新
     */
    public void change(int pos, int value) {
        rangeChange(root, pos, pos, value);
    }

    /**
     * 区间更新
     */
    public void rangeChange(int begin, int end, int value) {

    }

    private void rangeChange(Node node, int begin, int end, int value) {
        if (node.begin == begin && node.end == end) {
            node.sum = value;
            node.min = value;
            return;
        }
        int mid = (node.begin + node.end) >> 1;
        if (end <= mid) {
            rangeChange(node.left, begin, end, value);
            return;
        } else if (begin > mid) {
            rangeChange(node.right, begin, end, value);
            return;
        }
        rangeChange(node.left, begin, mid, value);
        rangeChange(node.right, mid + 1, end, value);
        node.sum = node.left.sum + node.right.sum;
        node.min = Math.min(node.left.min, node.right.min);
    }

    private static class Node {
        int begin;
        int end;
        int sum;
        int min;
        Node left;
        Node right;

        public Node(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }
}

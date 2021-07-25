package com.future.datastruct.union.experiment;

import java.util.Arrays;

/**
 * 并查集的合并
 * 因 QuickUnion直接是将左边的根结点向右边的根结点的合并，
 * 有可能会退化成链表的情况，这时查找时间复杂度则会上升至O(n)。
 * 为避免出现这种情况，则不能总是采用同一方向的合并，
 * 基于size的优化，也依然不能保证树的高度很低，
 * 树的高度决定了查找的速度
 * 该实现方案为矮树嫁接到高树。
 * rank为树的高度
 *
 * @author jayzhou
 */
public class RankUnion extends QuickUnion {

    private int[] ranks;

    public RankUnion(int cap) {
        super(cap);
        ranks = new int[cap + 1];
        Arrays.fill(ranks, 1);
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        if (ranks[p1] == ranks[p2]) {
            parents[p1] = p2;
            ranks[p2]++;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "parents=" + Arrays.toString(parents) +
                ", ranks=" + Arrays.toString(ranks) +
                '}';
    }
}

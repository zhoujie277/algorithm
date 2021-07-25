package com.future.datastruct.union.experiment;

/**
 * 快速查找的并查集方案实现，
 * find时间复杂度为O(1)，
 * union时间复杂度为O(n)
 *
 * @author jayzhou
 */
public class QuickFind extends IntegerUnionFind {
    public QuickFind(int cap) {
        super(cap);
    }

    @Override
    public void union(int v1, int v2) {
        int i1 = find(v1);
        int i2 = find(v2);
        if (i1 == i2) return;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == i1) {
                parents[i] = i2;
            }
        }
    }

    @Override
    public int find(int v) {
        return parents[v];
    }
}

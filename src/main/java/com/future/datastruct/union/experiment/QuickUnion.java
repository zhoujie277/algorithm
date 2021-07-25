package com.future.datastruct.union.experiment;

/**
 * 快速合并的并查集方案实现
 * find时间复杂度为0(logN)
 * union 时间复杂度为0(logN)
 * 要达到logN的情况，还需要优化，详见子类
 * 基于size的优化：SizeUnion
 * 基于rank的优化：RankUnion
 * @author jayzhou
 */
public class QuickUnion extends IntegerUnionFind {
    public QuickUnion(int cap) {
        super(cap);
    }

    @Override
    public void union(int v1, int v2) {
        int i1 = find(v1);
        int i2 = find(v2);
        if (i1 == i2) return;
        // 将i1的根结点(代表元素)嫁接到i2的根结点(代表元素)上面
        parents[i1] = i2;
    }

    @Override
    public int find(int v) {
        while (v != parents[v]) {
            v = parents[v];
        }
        return parents[v];
    }
}

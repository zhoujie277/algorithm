package com.future.datastruct.union.experiment;

/**
 * 路径分裂
 * 使路径上每个结点都指向其祖父结点
 * 相比路径压缩，修改结点指向次数相对较少。
 * @author jayzhou
 */
public class PathSplitting extends QuickUnion {

    public PathSplitting(int cap) {
        super(cap);
    }

    @Override
    public int find(int v) {
        while (v != parents[v]) {
            int parent = parents[v];
            parents[v] = parents[parent];
            v = parent;
        }
        return parents[v];
    }
}

package com.future.datastruct.union.experiment;

/**
 * 路径减半
 * 使路径上每隔一个结点就指向其祖父结点
 *
 * @author jayzhou
 */
public class PathHalving extends QuickUnion {
    public PathHalving(int cap) {
        super(cap);
    }

    @Override
    public int find(int v) {
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return parents[v];
    }
}

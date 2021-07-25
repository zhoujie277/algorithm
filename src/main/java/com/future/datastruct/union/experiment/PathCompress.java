package com.future.datastruct.union.experiment;

/**
 * 路径压缩
 * 虽然基于树的高度优化能保证树的高度增长较为缓慢，但随着数据规模的增大，树的高度依然会增长的很高，
 * 所以才有了路径压缩的优化算法，在查找过程中，压缩树的高度
 * <p>
 * 路径压缩指的是：在find时，使路径上的所有结点都指向根结点，从而降低树的高度。
 *
 * @author jayzhou
 */
public class PathCompress extends QuickUnion {
    public PathCompress(int cap) {
        super(cap);
    }

    @Override
    public int find(int v) {
        if (v != parents[v]) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}

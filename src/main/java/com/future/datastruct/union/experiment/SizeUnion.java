package com.future.datastruct.union.experiment;

import java.util.Arrays;

/**
 * 并查集的合并
 * 因 QuickUnion直接是将左边的根结点向右边的根结点的合并，
 * 有可能会退化成链表的情况，这时查找时间复杂度则会上升至O(n)。
 * 为避免出现这种情况，则不能总是采用同一方向的合并，
 * 该实现方案为采用size较少的一端向size较多的一端合并。
 *
 * @author jayzhou
 */
public class SizeUnion extends QuickUnion {
    private int[] sizes;

    public SizeUnion(int cap) {
        super(cap);
        sizes = new int[cap + 1];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        if (sizes[p1] <= sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }

    @Override
    public String toString() {
        return "{" +
                "parents=" + Arrays.toString(parents) +
                ", sizes=" + Arrays.toString(sizes) +
                '}';
    }
}

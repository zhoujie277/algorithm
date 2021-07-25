package com.future.datastruct.union.experiment;

import java.util.Arrays;

/**
 * 并查集
 * 用int数组演示的实验程序
 * 数组下标表示自身编号，数组值表示所在集合的编号（并查集中的代表元素）
 *
 * @author jayzhou
 */
public abstract class IntegerUnionFind {

    protected int[] parents;

    public IntegerUnionFind(int cap) {
        this.parents = new int[cap + 1];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public abstract void union(int v1, int v2);

    public abstract int find(int v);

    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    @Override
    public String toString() {
        return "{" +
                "parents=" + Arrays.toString(parents) +
                '}';
    }

    public static void main(String[] args) {
        int len = 8;
        test(new QuickFind(len));
        test(new QuickUnion(len));
        test(new SizeUnion(len));
        test(new RankUnion(len));
        test(new PathCompress(len));
        test(new PathSplitting(len));
        test(new PathHalving(len));
    }

    private static void test(IntegerUnionFind fd) {
        fd.union(1, 4);
        fd.union(2, 3);
        fd.union(1, 3);
        fd.union(5, 6);
        fd.union(7, 8);
        fd.union(1, 8);
        System.out.println("--" + fd.getClass().getSimpleName());
        System.out.println(fd.isSame(1, 5));
        System.out.println(fd.isSame(4, 2));
        System.out.println(fd.isSame(7, 1));
        System.out.println(fd);
        System.out.println("---------------------------------");
    }
}

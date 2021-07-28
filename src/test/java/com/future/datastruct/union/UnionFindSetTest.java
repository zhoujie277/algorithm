package com.future.datastruct.union;

import org.junit.Assert;
import org.junit.Test;

public class UnionFindSetTest {

    private void makeSet(HashUnionFind<Integer> fd, int... ints) {
        for (int anInt : ints) {
            fd.makeSet(anInt);
        }
    }

    @Test
    public void testUnionFind() {
        HashUnionFind<Integer> fd = new HashUnionFind<>();
        makeSet(fd, 1, 2, 3, 4, 5, 6, 7, 8);
        fd.union(1, 4);
        fd.union(2, 3);
        fd.union(1, 3);
        fd.union(5, 6);
        fd.union(7, 8);
        fd.union(1, 8);

        System.out.println("--" + fd.getClass().getSimpleName());
        System.out.println(fd.isConnected(1, 5));
        System.out.println(fd.isConnected(4, 2));
        System.out.println(fd.isConnected(7, 1));
        System.out.println(fd);
    }

    @Test
    public void testTreeUnionFind() {
        TreeUnionFind<Integer> unionFind = new TreeUnionFind<>();
        unionFind.union(0, 2);
        unionFind.union(2, 1);
        unionFind.union(1, 5);
//        unionFind.union(1, 5);
        Assert.assertTrue(unionFind.isConnected(2, 5));
        Assert.assertFalse(unionFind.isConnected(4, 5));
    }
}

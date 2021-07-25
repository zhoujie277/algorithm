package com.future.datastruct.union;

import org.junit.Test;

public class UnionFindSetTest {

    @Test
    public void testUnionFind() {
        UnionFindSet<Integer> fd = new UnionFindSet<>();
        for (int i = 1; i <= 8; i++) {
            fd.makeSet(i);
        }
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
    }
}

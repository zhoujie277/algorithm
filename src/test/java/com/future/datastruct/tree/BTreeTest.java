package com.future.datastruct.tree;

import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BTreeTest {

    private BTree<Integer> bTree;

    @Before
    public void setup() {
        bTree = new BTree<>(3);
    }

    @Test
    public void testTree() {
        int len = 10;
        int[] permutation = StdRandom.permutation(100, len);
        permutation = new int[]{55, 46, 93, 15, 22, 91, 29, 64, 5, 74};
        PrintUtils.println(permutation);
        for (int i = 0; i < permutation.length; i++) {
            bTree.add(permutation[i]);
        }
        Assert.assertEquals(bTree.size(), len);
        Assert.assertFalse(bTree.isEmpty());
    }
}

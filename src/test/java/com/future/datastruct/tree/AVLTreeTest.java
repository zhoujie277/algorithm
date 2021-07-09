package com.future.datastruct.tree;

import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import com.future.utils.drawtree.DrawTreeUtil;
import com.future.utils.drawtree.IDrawableTree;
import com.future.utils.drawtree.PrintTreeUtil;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {

    private AVLBinaryTree<Integer> avlTree = null;
    private int[] array;

    @Before
    public void setup() {
        avlTree = new AVLBinaryTree<>();
    }

    @Test
    public void testAVL() {
        int uniform = StdRandom.uniform(8, 15);
        array = StdRandom.permutation(100, uniform);
//        array = new int[]{69, 94, 62, 29, 17, 3, 93, 96};
//        array = new int[]{60, 98, 83, 30, 55, 24, 51, 29, 89, 67, 0};
        PrintUtils.println(array);
        for (int i = 0; i < array.length; i++) {
            avlTree.add(array[i]);
            PrintUtils.println("add element:" + array[i]);
            PrintTreeUtil.printTree(avlTree.getDrawableTree(false));
            PrintUtils.println();

        }
        IDrawableTree addTree = avlTree.getDrawableTree(false);
        PrintTreeUtil.printTree(addTree);
    }
}

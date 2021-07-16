package com.future.datastruct.tree;

import com.future.utils.PrintUtils;
import com.future.utils.drawtree.IDrawableTree;
import com.future.utils.drawtree.PrintTreeUtil;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Test;

public class RBTreeTest {
    private RedBlackTree<Integer> avlTree = null;
    private int[] array;

    @Before
    public void setup() {
        avlTree = new RedBlackTree<>();
    }

    @Test
    public void testRedBlack() {
        int uniform = StdRandom.uniform(8, 25);
        array = StdRandom.permutation(80, uniform);
        array = new int[]{39, 59, 51, 31, 41, 9, 75, 10, 68, 45, 16, 62, 76, 8, 11, 71, 21, 29};
        PrintUtils.println(array);
        for (int i = 0; i < array.length; i++) {
            PrintUtils.info("add element:" + array[i]);
            avlTree.add(array[i]);
            PrintTreeUtil.printTree(avlTree.getDrawableTree(false));
            PrintUtils.println();
        }
        IDrawableTree addTree = avlTree.getDrawableTree(false);
        PrintTreeUtil.printTree(addTree);

        PrintUtils.println("====================测试删除=========================");
        PrintUtils.println("====================================================");
        PrintUtils.println("====================================================");
//
        int[] permutation = StdRandom.permutation(80, 25);
        permutation = new int[]{23, 18, 16, 62, 22, 74, 72, 40, 15, 3, 41, 64, 28, 24, 57, 0, 49, 39, 66, 33, 70, 75, 17, 12, 65, 10, 71, 76, 21, 68, 9, 11};
        PrintUtils.println(permutation);
        for (int i = 0; i < permutation.length; i++) {
            int delete = permutation[i];
            Integer remove = avlTree.remove(delete);
            if (remove != null) {
                PrintUtils.info("remove element: " + delete);
                PrintTreeUtil.printTree(avlTree.getDrawableTree(false));
                PrintUtils.println();
                PrintUtils.println();
            }
        }
        PrintTreeUtil.printTree(avlTree.getDrawableTree(false));
    }
}

package com.future.datastruct.tree;

import com.future.utils.PrintUtils;
import com.future.utils.drawtree.PrintTreeUtil;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class LeftRBTreeTest {

    @Test
    public void testRBTree() {
        LeftRBTree<Integer, String> rbTree = new LeftRBTree<>();
        int[] array = new int[]{81, 11, 17, 67, 65, 22, 26, 1, 47, 61};//
        PrintUtils.println(array);
        for (int i = 0; i < array.length; i++) {
            PrintUtils.info("add element:" + array[i]);
            rbTree.put(array[i], "r" + array[i]);
            PrintTreeUtil.printTree(rbTree);
            PrintUtils.println();
        }

        PrintUtils.println("====================测试删除=========================");
        PrintUtils.println("====================================================");
        PrintUtils.println("====================================================");

        int[] permutation = StdRandom.permutation(80, 25);
        permutation = new int[]{23, 18, 16, 62, 22, 74, 72, 40, 15, 3, 41, 64, 28, 24, 57, 0, 49, 39, 66, 33, 70, 75, 17, 12, 65, 10, 71, 76, 21, 68, 9, 11};
//        permutation = new int[]{11};
        PrintUtils.println(permutation);
        for (int i = 0; i < permutation.length; i++) {
            int delete = permutation[i];
            String remove = rbTree.remove(delete);
            if (remove != null) {
                PrintUtils.info("remove element: " + delete);
                PrintTreeUtil.printTree(rbTree);
                PrintUtils.println();
                PrintUtils.println();
            }
        }
    }
}

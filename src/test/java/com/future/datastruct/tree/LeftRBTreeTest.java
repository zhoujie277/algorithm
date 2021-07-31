package com.future.datastruct.tree;

import com.future.utils.PrintUtils;
import com.future.utils.drawtree.PrintTreeUtil;
import org.junit.Test;

public class LeftRBTreeTest {

    @Test
    public void testRBTree() {
        LeftRBTree<Integer, String> rbTree = new LeftRBTree<>();
        int[] array = new int[]{81, 11, 17, 67, 65, 22, 26, 1, 47, 61};
        PrintUtils.println(array);
        for (int i = 0; i < array.length; i++) {
            PrintUtils.info("add element:" + array[i]);
            rbTree.put(array[i], "r" + array[i]);
            PrintTreeUtil.printTree(rbTree);
            PrintUtils.println();
        }
    }
}

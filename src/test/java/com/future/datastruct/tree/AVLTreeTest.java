package com.future.datastruct.tree;

import com.future.utils.PrintUtils;
import com.future.utils.drawtree.IDrawableTree;
import com.future.utils.drawtree.PrintTreeUtil;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class AVLTreeTest {

    private AVLBinaryTree<Integer> avlTree = null;
    private int[] array;

    @Before
    public void setup() {
        avlTree = new AVLBinaryTree<>();
    }

    @Test
    public void testAVL() {
        int uniform = StdRandom.uniform(8, 25);
        array = StdRandom.permutation(80, uniform);
//        array = new int[]{39, 59, 51, 31, 41, 9, 75, 10, 68, 45, 16, 62, 76, 8, 11, 71, 21, 29};
        PrintUtils.println(array);
        for (int i = 0; i < array.length; i++) {
            PrintUtils.println("add element:" + array[i]);
            avlTree.add(array[i]);
            assertBalanced();
            PrintTreeUtil.printTree(avlTree.getDrawableTree(false));
            PrintUtils.println();
        }
        IDrawableTree addTree = avlTree.getDrawableTree(false);
        PrintTreeUtil.printTree(addTree);

        PrintUtils.println("====================测试删除=========================");
        PrintUtils.println("====================================================");
        PrintUtils.println("====================================================");

        int[] permutation = StdRandom.permutation(80, 25);
//        permutation = new int[]{23, 18, 16, 62, 22, 74, 72, 40, 15, 3, 41, 64, 28, 24, 57, 0, 49, 39, 66, 33, 70, 75, 17, 12, 65};
        PrintUtils.println(permutation);
        for (int i = 0; i < permutation.length; i++) {
            int delete = permutation[i];
            PrintUtils.println("will remove element: " + delete);
            Integer remove = avlTree.remove(delete);
            if (remove != null) {
                assertBalanced();
                PrintTreeUtil.printTree(avlTree.getDrawableTree(false));
                PrintUtils.println();
                PrintUtils.println();
            }
        }
        PrintTreeUtil.printTree(avlTree.getDrawableTree(false));
    }

    private void assertBalanced() {
        Iterator<BinaryTree.Node<Integer>> nodeIterator = avlTree.breadthFirstSearchNodeIterator();
        while (nodeIterator.hasNext()) {
            AVLBinaryTree.AVLNode<Integer> node = (AVLBinaryTree.AVLNode<Integer>) nodeIterator.next();
            Assert.assertTrue(!avlTree.notBalanced(node));
            int h = Math.max(avlTree.getHeight(node.left), avlTree.getHeight(node.right)) + 1;
            Assert.assertTrue(node.height == h);
        }
    }
}

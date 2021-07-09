package com.future.datastruct.tree;

import com.future.utils.ArrayUtils;
import com.future.utils.drawtree.DrawTreeUtil;
import com.future.utils.PrintUtils;
import com.future.utils.drawtree.IDrawableTree;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.Iterator;

@FixMethodOrder(MethodSorters.JVM)
public class CompleteBinaryTreeTest {

    private CompleteBinaryTree<Integer> binaryTree = null;
    private Integer[] originArray = null;
    private IDrawableTree initTree;

    @Before
    public void setup() {
        int len = StdRandom.uniform(6, 12);
        int[] permutation = StdRandom.permutation(100, len);
        PrintUtils.println("setup array");
        PrintUtils.println(permutation);
        originArray = ArrayUtils.convert(permutation);
        binaryTree = new CompleteBinaryTree<>(originArray);
        initTree = binaryTree.getDrawableTree();
    }

    public void testProperty() {
        PrintUtils.print("leafCount=");
        PrintUtils.println(binaryTree.leafCount());
    }

    public IDrawableTree<Object> testAdd() {
        int[] ints = StdRandom.permutation(40, 5);
        PrintUtils.println("add " + Arrays.toString(ints));
        for (int anInt : ints) {
            binaryTree.add(anInt);
        }
        return binaryTree.getDrawableTree();
    }

    public IDrawableTree<Object> testPush() {
        int[] ints = StdRandom.permutation(70, 3);
        PrintUtils.println("push " + Arrays.toString(ints));
        for (int anInt : ints) {
            binaryTree.add(anInt);
        }
        return binaryTree.getDrawableTree();
    }

    public IDrawableTree testRemove() {
        int[] permutation = StdRandom.permutation(40, 10);
        PrintUtils.println("即将要删除 " + Arrays.toString(permutation));
        for (int i = 0; i < permutation.length; i++) {
            Integer remove = binaryTree.remove(permutation[i]);
            if (remove != null) {
                PrintUtils.println(permutation[i] + "被删除成功");
            }
        }
        return binaryTree.getDrawableTree();
    }

    @Test
    public void testEnter() {
        IDrawableTree<Object> addTree = testAdd();
        IDrawableTree removeTree = testRemove();
        IDrawableTree<Object> pushTree = testPush();
        testProperty();
//        println();
        Object[] integers = binaryTree.toArray();
        DrawTreeUtil.drawTree(integers, initTree, addTree, removeTree, pushTree);
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrintln() {
        PrintUtils.println("/");
    }

    public void println() {
        PrintUtils.println();
        PrintUtils.printf("-------------------size = %d -----------------", binaryTree.size());
        PrintUtils.println();
        PrintUtils.println();
        PrintUtils.println("------------------PreOrder----------------------");
        binaryTree.preOrder((t) -> {
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();
        PrintUtils.println("------------------preOrderByStack----------------------");
        binaryTree.preOrderByStack((t) -> {
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();
        PrintUtils.println("------------------preOrderIterator----------------------");
        Iterator preOrderIterator = binaryTree.preOrderIterator();
        while (preOrderIterator.hasNext()) {
            PrintUtils.print(preOrderIterator.next() + ", ");
        }
        PrintUtils.println();

        PrintUtils.println("------------------InOrder----------------------");
        binaryTree.inOrder((t) -> {
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();

        PrintUtils.println("------------------inOrderByStack----------------------");
        binaryTree.inOrderByStack((t) -> {
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();

        PrintUtils.println("------------------inOrderIterator----------------------");
        Iterator inOrderIterator = binaryTree.inOrderIterator();
        while (inOrderIterator.hasNext()) {
            PrintUtils.print(inOrderIterator.next() + ", ");
        }
        PrintUtils.println();

        PrintUtils.println("-----------------PostOrder-----------------------");
        binaryTree.postOrder((t) -> {
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();

        PrintUtils.println("------------------PostOrderByStack----------------------");
        binaryTree.postOrderByStack((t) -> {
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();

        PrintUtils.println("------------------postOrderIterator----------------------");
        Iterator postOrderIterator = binaryTree.postOrderIterator();
        while (postOrderIterator.hasNext()) {
            PrintUtils.print(postOrderIterator.next() + ", ");
        }
        PrintUtils.println();

        PrintUtils.println("-----------------BreadthFirstSearch-----------------------");
        Iterator iterator = binaryTree.breadthFirstSearchIterator();
        while (iterator.hasNext()) {
            PrintUtils.print(iterator.next() + ", ");
        }
        PrintUtils.println();

    }

}

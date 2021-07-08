package com.future.datastruct.tree;

import com.future.utils.ArrayUtils;
import com.future.utils.DrawTreeUtil;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Iterator;

@FixMethodOrder(MethodSorters.JVM)
public class CompleteBinaryTreeTest {

    private CompleteBinaryTree<Integer> binaryTree = null;
    private Integer[] originArray = null;

    @Before
    public void setup() {
        int len = StdRandom.uniform(10, 40);
        int[] permutation = StdRandom.permutation(len);
        PrintUtils.println("setup array");
        PrintUtils.println(permutation);
        originArray = ArrayUtils.convert(permutation);
        binaryTree = new CompleteBinaryTree<>(originArray);
    }

    @Test
    public void testProperty() {
        Assert.assertEquals(binaryTree.size(), originArray.length);
        PrintUtils.print("leafCount=");
        PrintUtils.println(binaryTree.leafCount());
    }

    @Test
    public void testAdd() {
        int[] ints = StdRandom.permutation(50, 60);
        PrintUtils.println(ints);
        for (int anInt : ints) {
            binaryTree.add(anInt);
        }
    }

    @Test
    public void testAfterAdd() {
        Assert.assertEquals(binaryTree.size(), originArray.length);
        PrintUtils.println(binaryTree.leafCount());
        PrintUtils.print("binaryTree.isCompleteBinaryTree()=");
        PrintUtils.println(binaryTree.isCompleteBinaryTree());
        DrawTreeUtil.drawTree(binaryTree.getDrawableTree());

    }

    @Test
    public void testPush() {
        int[] ints = StdRandom.permutation(70, 80);
        PrintUtils.println(ints);
        for (int anInt : ints) {
            binaryTree.add(anInt);
        }
    }

    @Test
    public void testAfterPush() {
        Assert.assertEquals(binaryTree.size(), originArray.length);
        PrintUtils.println(binaryTree.leafCount());
    }

    @After
    public void println() {
        PrintUtils.println();
        PrintUtils.printf("-------------------size = %d -----------------", binaryTree.size());
        PrintUtils.println();
        PrintUtils.println();
        PrintUtils.println("------------------PreOrder----------------------");
        binaryTree.preOrder((t)->{
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();
        PrintUtils.println("------------------preOrderByStack----------------------");
        binaryTree.preOrderByStack((t)->{
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
        binaryTree.inOrder((t)->{
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();

        PrintUtils.println("------------------inOrderByStack----------------------");
        binaryTree.inOrderByStack((t)->{
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
        binaryTree.postOrder((t)->{
            PrintUtils.print(t + ", ");
        });
        PrintUtils.println();

        PrintUtils.println("------------------PostOrderByStack----------------------");
        binaryTree.postOrderByStack((t)->{
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

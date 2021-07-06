package com.future.datastruct.tree;

import com.future.utils.PrintUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class ThreadedBinaryTreeTest {

    private ThreadedBinaryTree binaryTree = null;

    @Before
    public void setup() {
        binaryTree = new ThreadedBinaryTree();
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        binaryTree.initTree(array);
    }

    @Test
    public void preOrder() {
        binaryTree.preOrder((t) -> {
            PrintUtils.print(t + "\t");
        });
        PrintUtils.println();
        testThreadedPreOrder();
    }

    public void testThreadedPreOrder() {
        PrintUtils.println("------------------testThreadedPreOrder----------------------");
        binaryTree.threadedPreOrder((t) -> {
            PrintUtils.print(t + "\t");
        });
        PrintUtils.println();
    }

    @Test
    public void inOrder() {
        binaryTree.inOrder((t) -> {
            PrintUtils.print(t + "\t");
        });
        PrintUtils.println();
        testThreadedInOrder();
    }

    public void testThreadedInOrder() {
        PrintUtils.println("------------------ThreadedInOrder----------------------");
        binaryTree.threadedInOrder((t) -> {
            PrintUtils.print(t + "\t");
        });
        PrintUtils.println();
    }

    @After
    public void println() {
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

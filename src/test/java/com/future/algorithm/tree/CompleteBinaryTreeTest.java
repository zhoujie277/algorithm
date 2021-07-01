package com.future.algorithm.tree;

import com.future.algoriithm.tree.CompleteBinaryTree;
import com.future.algoriithm.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

public class CompleteBinaryTreeTest {

    private CompleteBinaryTree binaryTree = null;

    @Before
    public void setup() {
        binaryTree = new CompleteBinaryTree();
    }

    @Test
    public void testArrayConvertTree() {
        int[] permutation = StdRandom.permutation(30);
        PrintUtils.println(permutation);
        binaryTree.initTree(Arrays.stream(permutation).boxed().toArray());
    }

    public void testAdd() {

    }

    public void testPush() {

    }

    @After
    public void println() {
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

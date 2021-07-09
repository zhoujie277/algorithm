package com.future.datastruct.tree;

import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import com.future.utils.drawtree.DrawTreeUtil;
import com.future.utils.drawtree.IDrawableTree;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> searchTree;
    private int[] array;

    @Before
    public void setup() {
        searchTree = new BinarySearchTree<>();
    }

    public IDrawableTree testAdd() {
        int uniform = StdRandom.uniform(8, 15);
        array = StdRandom.permutation(100, uniform);
        for (int i = 0; i < array.length; i++) {
            searchTree.add(array[i]);
        }
        PrintUtils.println(array);
        return searchTree.getDrawableTree();
    }

    @Test
    public void testRemove() {
        IDrawableTree addTree = testAdd();
        int[] permutation = StdRandom.permutation(100, 10);
        PrintUtils.println("即将删除 " + Arrays.toString(permutation));
        PrintUtils.print("删除成功：");
        for (int i = 0; i < permutation.length; i++) {
            Integer remove = searchTree.remove(permutation[i]);
            if (remove != null) {
                PrintUtils.print(permutation[i] + " ");
            }
        }
        PrintUtils.println();
        IDrawableTree removeTree = searchTree.getDrawableTree();
        traversal();
        Object[] integers = searchTree.toArray();
        DrawTreeUtil.drawTree(ArrayUtils.convert(array), addTree, removeTree);
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void traversal() {
        PrintUtils.println();
        PrintUtils.println("-----------------Array-------------------------");
        PrintUtils.println(array);

        PrintUtils.println("-----------------BreadthFirstSearch-----------------------");
        searchTree.breadthFirstSearch((t) -> {
            PrintUtils.print(t + " ");
        });
    }
}

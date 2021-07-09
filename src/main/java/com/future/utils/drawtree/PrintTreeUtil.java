package com.future.utils.drawtree;

import com.future.datastruct.tree.BinarySearchTree;
import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;

public class PrintTreeUtil {
    public static void main(String[] args) {
//        int[] array = new int[]{9, 5, 3, 7, 0, 4, 6, 8, 2, 1};
        int[] array = StdRandom.permutation(15);
        Integer[] convert = ArrayUtils.convert(array);
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        for (int j : array) {
            searchTree.add(j);
        }
//        drawTree(convert, searchTree.getDrawableTree());
        printTree(searchTree.getDrawableTree());
    }

    public static <E> void printTree(IDrawableTree<E> tree) {
        DrawTree<E> drawTree = new DrawTree<>(tree);
        drawTree.println();
        PrintUtils.println();
    }
}

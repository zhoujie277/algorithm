package com.future.datastruct.tree;

import com.future.utils.PrintUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

    private BinarySearchTree searchTree;
    private int[] array ;

    @Before
    public void setup() {
//        array = StdRandom.permutation(10);
        array = new int[]{1, 9, 6, 8, 5, 3, 4, 7, 0, 2};
        searchTree = new BinarySearchTree();
        testAdd();
    }

    public void testAdd() {
        for (int i = 0; i < array.length; i++) {
            searchTree.add(array[i]);
        }
    }

    @Test
    public void testRemove() {
        searchTree.remove(6);
        searchTree.remove(8);
        searchTree.remove(4);
        searchTree.remove(3);
        searchTree.remove(2);
        searchTree.remove(0);
        searchTree.remove(9);
        searchTree.remove(5);
        searchTree.remove(7);
        searchTree.remove(1);
        //1 0 9 6 5 8 3 7 2 4
    }

    @After
    public void traversal() {
        PrintUtils.println();
        PrintUtils.println("-----------------Array-------------------------");
        PrintUtils.println(array);
//        PrintUtils.println("------------------PreOrder----------------------");
//        searchTree.preOrder((t)->{
//            PrintUtils.print(t + " ");
//        });
//        PrintUtils.println();
//
//        PrintUtils.println("------------------InfixOrder----------------------");
//        searchTree.inOrder((t)->{
//            PrintUtils.print(t + " ");
//        });
//        PrintUtils.println();

//        PrintUtils.println("-----------------PostOrder-----------------------");
//        searchTree.postOrder((t)->{
//            PrintUtils.print(t + " ");
//        });
//        PrintUtils.println();

        PrintUtils.println("-----------------BreadthFirstSearch-----------------------");
        searchTree.breadthFirstSearch((t)->{
            PrintUtils.print(t + " ");
        });
    }
}

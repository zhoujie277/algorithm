package com.future.algorithm.tree;

import com.future.algoriithm.tree.SortedBinaryTree;
import com.future.algoriithm.utils.DataUtils;
import com.future.algoriithm.utils.PrintUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

    private SortedBinaryTree searchTree;

    @Before
    public void setup() {
        searchTree = new SortedBinaryTree();
    }

    @Test
    public void testBinaryNode() {
        for (int i = 1; i <= 7; i++) {
//            searchTree.add(DataUtils.newStudent());
            searchTree.add(i);
        }
    }

    @After
    public void traversal() {
        PrintUtils.println("------------------PreOrder----------------------");
        searchTree.preOrder((t)->{
            PrintUtils.print(t + " ");
        });
        PrintUtils.println();

        PrintUtils.println("------------------InfixOrder----------------------");
        searchTree.inOrder((t)->{
            PrintUtils.print(t + " ");
        });
        PrintUtils.println();

        PrintUtils.println("-----------------PostOrder-----------------------");
        searchTree.postOrder((t)->{
            PrintUtils.print(t + " ");
        });
        PrintUtils.println();

        PrintUtils.println("-----------------BreadthFirstSearch-----------------------");
        searchTree.breadthFirstSearch((t)->{
            PrintUtils.print(t + " ");
        });
    }
}

package com.future.datastruct.tree;

import com.future.datastruct.list.Stack;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Test;

public class ArrayBinaryTreeTest {

    private int[] array = null;

    private void preOrder(int index) {
        PrintUtils.print(array[index] + "\t");
        if (index * 2 + 1 < array.length) {
            preOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < array.length) {
            preOrder(index * 2 + 2);
        }
    }

    private void preOrder() {
        Stack<Integer> stack = new Stack();
        stack.push(0);
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            PrintUtils.print(array[pop] + "\t");
            int left = pop * 2 + 1;
            int right = pop * 2 + 2;
            if (right < array.length) {
                stack.push(right);
            }
            if (left < array.length) {
                stack.push(left);
            }
        }
    }

    @Before
    public void setup() {
        array = StdRandom.permutation(10);
        PrintUtils.println(array);
    }

    @Test
    public void testPreOrder() {
        // 前序遍历：根、前、后
        preOrder(0);
    }

    @Test
    public void testPreOrder2() {
        preOrder();
    }


}

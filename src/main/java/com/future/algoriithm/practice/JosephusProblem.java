package com.future.algoriithm.practice;

import com.future.datastruct.list.DualCircleLinkedList;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 约瑟夫问题
 */
public class JosephusProblem {

    public static int startGame(int n, int startIndex, int space) {
        // 初始化游戏数据
        DualCircleLinkedList<Integer> list = new DualCircleLinkedList<>();
        int[] permutation = StdRandom.permutation(n);
        PrintUtils.println(permutation);
        for (int i = 0; i < permutation.length; i++) {
            list.add(permutation[i]);
        }
        // 从第startIndex个人开始
        int i = 1;
        while (i < startIndex) {
            list.next();
            i++;
        }

        // start cycle
        i = 1;
        while (list.size() > 1) {
            i++;
            list.next();
            if (i == space) {
                PrintUtils.println("JosephusProblem eliminate data is " + list.current());
                list.removeCurrent();
                i = 1;
            }
        }
        return list.peek();
    }

    public static void main(String[] args) {
        int survivor = startGame(30, 2, 3);
        PrintUtils.println("survivor is " + survivor);

    }
}

package com.future.datastruct.map;

import com.future.utils.PrintUtils;
import com.future.utils.drawtree.PrintTreeUtil;
import org.junit.Test;

public class RBTreeMapTest {

    @Test
    public void testRBTree() {
        RBTreeMap<Integer, String> map = new RBTreeMap<>();
//        int[] permutation = StdRandom.permutation(100, 10);
        int[] permutation = {81, 11, 17, 67, 65, 22, 26, 1, 47, 61};
        PrintUtils.println(permutation);
        for (int j : permutation) {
            PrintUtils.info("add element:"+ j);
            map.put(j, "rb" + j);
            PrintTreeUtil.printTree(map);
        }
        for (Integer integer : map) {
            PrintUtils.print(integer + " ");
        }

        PrintTreeUtil.printTree(map);
    }
}

package com.future.algorithm.sort;

import com.future.algoriithm.sort.HeapSorter;
import com.future.algoriithm.sort.SimpleSorting;
import com.future.algoriithm.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HeapSorterTest {
    private int[] array = null;

    @Before
    public void setup() {
        array = StdRandom.permutation(17);
//        array = new int[]{3, 9, 7, 8, 0, 6, 2, 5, 1, 4};
        PrintUtils.print(array);
    }

    @Test
    public void testSort() {
        HeapSorter.sort(array);
//        HeapSorter.slowSort(array);
//        SimpleSorting.selectSort(array);
    }

    @After
    public void after() {
        PrintUtils.println(array);
    }
}

package com.future.algorithm.sort;

import com.future.algoriithm.sort.MergeSorter;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.BeforeClass;
import org.junit.Test;

public class MergeSorterTest {
    private static int[] array = null;

    @BeforeClass
    public static void setUp() throws Exception {
        array = StdRandom.permutation(30);
        PrintUtils.println(array);
    }

    @Test
    public void testTopDown() {
        MergeSorter.topDownSort(array);
        PrintUtils.print(array);
    }

    @Test
    public void testBottomUp() {
        MergeSorter.bottomUpSort(array);
        PrintUtils.print(array);
    }
}

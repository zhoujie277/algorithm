package com.future.algorithm.sort;

import com.future.algoriithm.sort.ShellSorter;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShellSorterTest {

    private static int[] array = null;

    @BeforeClass
    public static void setUp() throws Exception {
        array = StdRandom.permutation(8000000);
//        PrintUtils.println(array);
    }


    @Test
    public void testSort() {
        int[] clone = array.clone();
        ShellSorter.sort(clone);
//        PrintUtils.println(clone);
    }

    @Test
    public void testSortOpt() {
        int[] clone = array.clone();
        ShellSorter.sortOpt(clone);
//        PrintUtils.println(clone);
    }

    @Test
    public void testSortGapOpt() {
        int[] clone = array.clone();
        ShellSorter.sortGapOpt(clone);
//        PrintUtils.println(clone);
    }
}

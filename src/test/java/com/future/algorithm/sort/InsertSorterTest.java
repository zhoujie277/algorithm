package com.future.algorithm.sort;

import com.future.algoriithm.sort.InsertSorter;
import com.future.algoriithm.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.BeforeClass;
import org.junit.Test;

public class InsertSorterTest  {

    private static int[] array = null;

    @BeforeClass
    public static void setUp() throws Exception {
        array = StdRandom.permutation(80000);
//        PrintUtils.println(array);
    }

    @Test
    public void directInsert() {
        InsertSorter.directInsert(array.clone());
    }

    @Test
    public void directInsertOpt() {
        InsertSorter.directInsertOpt(array.clone());
    }

    @Test
    public void binaryInsert() {
        InsertSorter.binaryInsert(array.clone());
    }

    @Test
    public void binaryPairInsert() {
        int[] clone = array.clone();
        InsertSorter.binaryInsertOpt(clone);
//        PrintUtils.println(clone);
    }
}

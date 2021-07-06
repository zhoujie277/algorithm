package com.future.algorithm.sort;

import com.future.algoriithm.sort.RadixSorter;
import com.future.utils.DataUtils;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RadixSorterTest {

   private long start = 0L;

    private static int[] array = null;

    @BeforeClass
    public static void setup() {
        array = StdRandom.permutation(8000000);
        //        PrintUtils.print(array);
    }


    @Before
    public void before() {
        start = System.currentTimeMillis();
    }

    @After
    public void after() {
        PrintUtils.print("cost time " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test
    public void numberTest() {
        RadixSorter.RadixNumberSorter sorter = new RadixSorter.RadixNumberSorter();
        sorter.sort(array);
//        PrintUtils.print(array);
    }

    @Test
    public void stringTest() {
        String[] array = DataUtils.generateStrings(10);
        PrintUtils.println(array);
        RadixSorter.StringSorter sorter = new RadixSorter.StringSorter();
        sorter.sort(array);
        PrintUtils.println(array);
    }

    @Test
    public void multiKeyTest() {
        DataUtils.Student[] students = DataUtils.generateStudents(20);
        PrintUtils.println(students);
        RadixSorter.MultiKeySorter sorter = new RadixSorter.MultiKeySorter();
        sorter.sort(students);
        PrintUtils.println(students);
    }
}

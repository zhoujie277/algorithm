package com.future.algorithm.search;

import com.future.algoriithm.search.BinarySearch;
import com.future.algoriithm.search.InterpolationSearch;
import com.future.algoriithm.sort.InsertSorter;
import com.future.algoriithm.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchTest {

    private static int[] array = null;
    private static int[] randomArray = null;
    private static int findTarget = 0;

    @BeforeClass
    public static void setup() {
        array = StdRandom.permutation(100);
        InsertSorter.directInsertOpt(array);
        findTarget = StdRandom.uniform(200);
        PrintUtils.println(array);
        PrintUtils.println("need to find target is " + findTarget);
    }

    @Test
    public void testBinarySearch() {
        int index = BinarySearch.search(array, findTarget);
        if (index < 0)
        PrintUtils.println("index = " + ~index);
        Assert.assertTrue(index == findTarget || (~index == 100));
    }

    @Test
    public void testBinarySearchRecursive() {
        int index = BinarySearch.searchRecursive(array, findTarget);
        if (index < 0)
        PrintUtils.println("index = " + ~index);
        Assert.assertTrue(index > 0|| (~index == 100));
    }

    @Test
    public void testInterpolationSearch() {
        int search = InterpolationSearch.search(array, findTarget);
        PrintUtils.println("search=" + search);
    }

    @Test
    public void testFibonacciSearch() {
        int index = InterpolationSearch.search(array, findTarget);
        PrintUtils.println("index=" + index);
    }

}

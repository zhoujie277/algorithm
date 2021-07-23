package com.future.algorithm.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class JavaTest {

    @Test
    public void testHashSet() {
        HashSet<Integer> set = new HashSet<>();
        int len = Integer.MAX_VALUE / 100;
        for (int i = 0; i < len; i++) {
            set.add(i);
        }
    }

    @Test
    public void testHashMap() {
        HashMap<Integer, String> map = new HashMap();
        int len = Integer.MAX_VALUE / 100;
        for (int i = 0; i < len; i++) {
            map.put(i, "h");
        }
        Assert.assertEquals(map.size(), len);
    }
}

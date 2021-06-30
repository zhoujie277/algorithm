package com.future.algorithm.search;

import com.future.algoriithm.search.HashTab;
import com.future.algoriithm.utils.DataUtils;
import com.future.algoriithm.utils.PrintUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class HashTabTest {
    private int count = 10;
    private HashTab<DataUtils.Student> hashTab = new HashTab<>(count);

    @Before
    public void testAdd() {
        for (int i = 0; i < count; i++) {
            hashTab.add(DataUtils.newStudent());
        }
    }

    @After
    public void testIterator() {
        Iterator<DataUtils.Student> iterator = hashTab.iterator();
        while (iterator.hasNext()) {
            DataUtils.Student next = iterator.next();
            PrintUtils.println(next);
        }
    }

    @Test
    public void testSize() {
        int size = hashTab.size();
        Assert.assertEquals(size, count);
    }

    @Test
    public void testRemove() {
        Iterator<DataUtils.Student> iterator = hashTab.iterator();
        if (iterator.hasNext()) {
            boolean remove = hashTab.remove(iterator.next());
            Assert.assertTrue(remove);
        }
    }
}

package com.future.algorithm.search;

import com.future.datastruct.list.HashTab;
import com.future.utils.DataUtils;
import com.future.utils.PrintUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashTabTest {
    private int count = 20;
    private HashTab<DataUtils.Key<Integer>, DataUtils.Student> hashTab;
    private HashMap<DataUtils.Key<Integer>, DataUtils.Student> javaMap;

    @Before
    public void testAdd() {
        javaMap = new HashMap<>();
        hashTab = new HashTab<>();
        for (int i = 0; i < count; i++) {
            DataUtils.Student student = DataUtils.newStudent();
            DataUtils.Key<Integer> key = new DataUtils.Key<>(student.id);
            hashTab.put(key, student);
            javaMap.put(key, student);
        }
    }

    @Test
    public void testJavaHashMap() {
        int size = javaMap.size();
        PrintUtils.println("java map size=" + size);
        Set<Map.Entry<DataUtils.Key<Integer>, DataUtils.Student>> entries = javaMap.entrySet();
        for (Map.Entry<DataUtils.Key<Integer>, DataUtils.Student> entry : entries) {
        }
    }

    @After
    public void testIterator() {
        Iterator<DataUtils.Key<Integer>> iterator = hashTab.iterator();
        while (iterator.hasNext()) {
            DataUtils.Key<Integer> name = iterator.next();
            DataUtils.Student student = hashTab.get(name);
            if (student == null) {
                System.err.println("key---------" + name);
            }
//            PrintUtils.println("key=" + name + ", student=" + student);
        }
    }

    @Test
    public void testSize() {
        int size = hashTab.size();
        Assert.assertEquals(size, count);
    }

    @Test
    public void testRemove() {
//        Iterator<DataUtils.Student> iterator = hashTab.iterator();
//        if (iterator.hasNext()) {
//            boolean remove = hashTab.remove(iterator.next());
//            Assert.assertTrue(remove);
//        }
    }
}

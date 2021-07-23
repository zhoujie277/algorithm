package com.future.datastruct.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class Key {
    protected int value;

    public Key(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value / 20 + 54;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        return ((Key) obj).value == value;
    }

    @Override
    public String toString() {
        return "v(" + value + ")";
    }
}

class SubKey1 extends Key {

    public SubKey1(int value) {
        super(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null ||
                (obj.getClass() != SubKey1.class
                        && obj.getClass() != SubKey2.class)) return false;
        return ((Key) obj).value == value;
    }
}

class SubKey2 extends Key {

    public SubKey2(int value) {
        super(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null ||
                (obj.getClass() != SubKey1.class
                        && obj.getClass() != SubKey2.class)) return false;
        return ((Key) obj).value == value;
    }
}

public class HashTableTest {
    private HashTable<Object, Integer> map = null;

    @Before
    public void setup() {
        map = new HashTable<>();
    }

    @Test
    public void two() {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        assertEquals(map.size(), 20);
        assertEquals(map.get(new Key(4)).intValue(), 4);
        assertEquals(map.get(new Key(5)).intValue(), 10);
        assertEquals(map.get(new Key(6)).intValue(), 11);
        assertEquals(map.get(new Key(7)).intValue(), 12);
        assertEquals(map.get(new Key(8)).intValue(), 8);
    }

    @Test
    public void three() {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        assertEquals(map.size(), 5);
        assertEquals(map.get(null).intValue(), 8);
        assertEquals(map.get("jack").intValue(), 6);
        assertNull(map.get(10));
        assertNull(map.get(new Object()));
        assertTrue(map.containsKey(10));
        assertTrue(map.containsKey(null));
        assertTrue(map.containsValue(null));
        assertFalse(map.containsValue(1));
    }

    @Test
    public void four() {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            assertEquals(map.remove(new Key(i)).intValue(), i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        assertEquals(map.size(), 19);
        assertEquals(map.get(new Key(1)).intValue(), 6);
        assertEquals(map.get(new Key(2)).intValue(), 7);
        assertEquals(map.get(new Key(3)).intValue(), 8);
        assertEquals(map.get(new Key(4)).intValue(), 4);
        assertNull(map.get(new Key(5)));
        assertNull(map.get(new Key(6)));
        assertNull(map.get(new Key(7)));
        assertEquals(map.get(new Key(8)).intValue(), 8);
    }

    @Test
    public void five() {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        assertEquals(map.get(new SubKey1(1)).intValue(), 5);
        assertEquals(map.get(new SubKey2(1)).intValue(), 5);
        assertEquals(map.size(), 20);
    }

    @Test
    public void testData() {
        int len = 200;
        for (int i = 0; i < len; i++) {
            map.put(new Key(i), i);
        }
    }
}

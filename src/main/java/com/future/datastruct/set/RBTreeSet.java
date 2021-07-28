package com.future.datastruct.set;

import com.future.datastruct.map.RBTreeMap;

import java.util.Iterator;

public class RBTreeSet<E extends Comparable<E>> implements ISet<E> {

    private RBTreeMap<E, Object> map = new RBTreeMap<>();
    private static Object EMPTY = new Object();

    @Override
    public boolean contains(E e) {
        return map.containsKey(e);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, EMPTY) == null;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return map.iterator();
    }

    @Override
    public void clear() {
        map.clear();
    }
}

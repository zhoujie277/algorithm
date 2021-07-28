package com.future.datastruct.set;

import com.future.datastruct.map.IdentityRBTreeMap;

import java.util.Iterator;

public class IdentityTreeSet<E> implements ISet<E> {

    private final static Object EMPTY = new Object();
    private final IdentityRBTreeMap<E, Object> map = new IdentityRBTreeMap<>();

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

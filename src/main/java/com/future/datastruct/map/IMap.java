package com.future.datastruct.map;

import java.util.Iterator;

public interface IMap<K, V> extends Iterable<K> {
    boolean containsKey(K key);

    boolean containsValue(V v);

    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    @Override
    IMapIterator<K, V> iterator();

    interface IMapIterator<K, V> extends Iterator<K> {
        @Override
        boolean hasNext();

        @Override
        K next();

        V value();

    }
}

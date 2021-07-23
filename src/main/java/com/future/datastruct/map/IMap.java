package com.future.datastruct.map;

public interface IMap<K, V> extends Iterable<K> {
    boolean containsKey(K key);

    boolean containsValue(V v);

    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

}

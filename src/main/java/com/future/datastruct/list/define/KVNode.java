package com.future.datastruct.list.define;


public class KVNode<K, V> extends AbstractLinked.Node<V> {
    public K key;
    public int hash;

    public KVNode(K key, V value) {
        this(key, value, null);
    }

    public KVNode(K key, V value, AbstractLinked.Node<V> next) {
        this(key, value, next, 0);
    }

    public KVNode(K key, V value, AbstractLinked.Node<V> next, int hash) {
        super(value, next);
        this.key = key;
        this.hash = hash;
    }
}

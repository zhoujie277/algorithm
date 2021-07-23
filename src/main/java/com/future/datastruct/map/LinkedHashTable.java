package com.future.datastruct.map;

public class LinkedHashTable<K, V> extends HashTable<K, V> {

    static class LinkedNode<K, V> extends Node<K, V> {

        public LinkedNode(K key, V value, Node<K, V> next, int hash) {
            super(key, value, next, hash);
        }
    }
}

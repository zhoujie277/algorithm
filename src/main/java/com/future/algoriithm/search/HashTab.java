package com.future.algoriithm.search;

import com.future.algoriithm.node.KVNode;
import com.future.algoriithm.utils.Hashable;
import com.future.algoriithm.utils.PrintUtils;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 哈希表
 */
@SuppressWarnings("unused")
public class HashTab<K, V> implements Iterable<K>, Serializable {
    private static final int DEFAULT_INIT_CAPACITY = 16;
    private static final float DEFAULT_FACTOR = 0.75f;
    private KVNode<K, V>[] table;
    private int size;
    private int threshold;

    public HashTab() {
        this(DEFAULT_INIT_CAPACITY);
    }

    public HashTab(int capacity) {
        size = 0;
        capacity = tabSizeFor(capacity);
        table = new KVNode[capacity];
        threshold = (int) (capacity * DEFAULT_FACTOR);
    }

    public static int tabSizeFor(int cap) {
        if (cap <= 0) return DEFAULT_INIT_CAPACITY;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    public V get(K key) {
        int index = hash(key);
        KVNode<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = (KVNode<K, V>) current.next;
        }
        return null;
    }

    public V put(K key, V value) {
        int hashcode = hashCode(key);
        int index = hash(hashcode);
        KVNode<K, V> node = table[index];
        KVNode<K, V> current = node;
        while (current != null) {
            if (current.key.equals(key)) {
                V oldVal = current.value;
                current.value = value;
                return oldVal;
            }
            current = (KVNode<K, V>) current.next;
        }
        table[index] = new KVNode<>(key, value, node, hashcode);
        size++;
        resize();
        return null;
    }

    public int size() {
        return size;
    }

    public V remove(K key) {
        int index = hash(key);
        KVNode<K, V> prev = table[index];
        if (prev == null) return null;
        KVNode<K, V> node = prev;
        while (node != null) {
            if (node.key.equals(key)) {
                break;
            }
            prev = node;
            node = (KVNode<K, V>) node.next;
        }
        if (node == null) return null;
        V oldVal = node.value;
        if (node == prev) {
            table[index] = (KVNode<K, V>) node.next;
        } else {
            prev.next = node.next;
        }
        node.value = null;
        node.next = null;
        size--;
        return oldVal;
    }

    private void resize() {
        if (size == threshold) {
            grow();
        }
    }

    private void grow() {
        KVNode<K, V>[] oldTab = table;
        int oldCap = oldTab.length;
        int newCap = oldCap << 1;
        threshold = (int) (newCap * DEFAULT_FACTOR);
        KVNode[] newTab = new KVNode[newCap];
        for (int i = 0; i < oldTab.length; i++) {
            KVNode<K, V> node = oldTab[i];
            KVNode<K, V> loHead = null, loTail = null;
            KVNode<K, V> hiHead = null, hiTail = null;
            while (node != null) {
                if ((node.hash & oldCap) == 0) {
                    if (loHead == null) {
                        loHead = node;
                    } else {
                        loTail.next = node;
                    }
                    loTail = node;
                } else {
                    if (hiHead == null) {
                        hiHead = node;
                    } else {
                        hiTail.next = node;
                    }
                    hiTail = node;
                }
                node = (KVNode<K, V>) node.next;
            }
            if (loTail != null) {
                loTail.next = null;
                newTab[i] = loHead;
            }
            if (hiTail != null) {
                hiTail.next = null;
                newTab[i + oldCap] = hiHead;
            }
        }
        table = newTab;
    }

    private int hash(K key) {
        int hashCode = hashCode(key);
        return hash(hashCode);
    }

    private int hashCode(K key) {
        int h = (key instanceof Hashable) ? ((Hashable) key).hashValue() : key.hashCode();
        return (h ^ (h >>> 16));
    }

    private int hash(int hashcode) {
        return hashcode & (table.length - 1);
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyItr();
    }

    private class KeyItr implements Iterator<K> {

        private int index;
        private KVNode<K, V> current;

        private KeyItr() {
            index = 0;
            current = findNext();
        }

        private KVNode<K, V> findNext() {
            KVNode<K, V> c = null;
            while (index < table.length && (c = table[index]) == null) {
                index++;
            }
            return c;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public K next() {
            K val = current.key;
//            PrintUtils.println("key:" + current.key + ", tabIndex=" + hash(current.hash));
            current = (KVNode<K, V>) current.next;
            if (current == null) {
                index++;
                current = findNext();
            }
            return val;
        }
    }
}

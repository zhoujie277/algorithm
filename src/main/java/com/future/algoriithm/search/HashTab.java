package com.future.algoriithm.search;

import com.future.algoriithm.linked.SingleLinkedList;
import com.future.algoriithm.node.Node;
import com.future.algoriithm.utils.Hashable;

import java.util.Iterator;

/**
 * 哈希表
 *
 * @param <E>
 */
public class HashTab<E> implements Iterable<E> {
    private SingleLinkedList<E>[] table;
    private int capacity;
    private int size;

    public HashTab(int capacity) {
        table = new SingleLinkedList[capacity];
        this.capacity = capacity;
        size = 0;
        initTable();
    }

    private void initTable() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new SingleLinkedList<>();
        }
    }

    public void add(E e) {
        int index = hash(e);
        table[index].push(e);
        size++;
    }

    public int size() {
        return size;
    }

    public boolean remove(E e) {
        int index = hash(e);
        return table[index].remove(e);
    }

    private int hash(E e) {
        int hashCode = (e instanceof Hashable) ? ((Hashable) e).hashValue() : e.hashCode();
        return hash(hashCode);
    }

    private int hash(int hashcode) {
        return hashcode & (table.length - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator {

        private int index;
        private Node<E> current;

        private Itr() {
            index = 0;
            current = findNext();
        }

        private Node<E> findNext() {
            Node<E> c = null;
            while (index < table.length && (c = table[index].first()) == null) {
                index++;
            }
            return c;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E val = current.value;
            current = current.next;
            if (current == null) {
                index++;
                current = findNext();
            }
            return val;
        }
    }
}

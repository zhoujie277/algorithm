package com.future.datastruct.list;

import com.future.datastruct.list.define.*;

import java.util.Iterator;

/**
 * 实现顺序存储接口的双向链表
 *
 * @author jayzhou
 */
public class DualLinkedList<T> extends AbstractLinked<T> implements ISequence<T> {
    protected DualNode<T> head;
    protected DualNode<T> tail;

    public DualLinkedList() {
        head = tail = null;
    }

    @Override
    protected int innerIndexOf(T t) {
        DualNode<T> node = head;
        int index = 0;
        while (node != null) {
            if (node.value.equals(t)) {
                return index;
            }
            index++;
            node = (DualNode<T>) node.next;
        }
        return ELEMENT_NOT_FOUND;
    }

    @SuppressWarnings("unused")
    public T first() {
        return head == null ? null : head.value;
    }

    @Override
    public boolean remove(T obj) {
        if (obj == null) return false;
        for (DualNode<T> f = head; f != null; ) {
            if (f.value.equals(obj)) {
                unLink(f);
                return true;
            }
            f = (DualNode<T>) f.next;
        }
        return false;
    }

    @Override
    public boolean add(T t) {
        linkLast(t);
        return true;
    }

    @Override
    public T poll() {
        return unLink(head);
    }

    @Override
    public T pop() {
        return unLink(tail);
    }

    @Override
    public void push(T obj) {
        linkLast(obj);
    }

    public void unshift(T obj) {
        linkFirst(obj);
    }

    @SuppressWarnings("unused")
    public T shift() {
        return unLink(head);
    }

    protected T unLink(DualNode<T> node) {
        if (node == null) return null;
        if (head == tail) head = tail = null;
        T value = node.value;
        DualNode<T> prev = node.prev;
        DualNode<T> next = (DualNode<T>) node.next;
        if (prev == null || prev == tail) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null || next == head) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.value = null;
        afterUnLink();
        size--;
        return value;
    }

    protected void afterUnLink() {
    }

    private void linkFirst(T obj) {
        DualNode<T> first = head;
        DualNode<T> newNode = new DualNode<>(obj, first, null);
        head = newNode;
        if (first == null) {
            tail = newNode;
        } else {
            first.prev = newNode;
        }
        afterLinkFirst();
        size++;
    }

    private void linkBefore(T e, DualNode<T> node) {
        final DualNode<T> pred = node.prev;
        final DualNode<T> newNode = new DualNode<>(e, node, pred);
        node.prev = newNode;
        if (pred == null) {
            head = newNode;
            afterLinkFirst();
        } else {
            pred.next = newNode;
        }
        size++;
    }

    private void linkLast(T obj) {
        DualNode<T> last = tail;
        DualNode<T> newNode = new DualNode<>(obj, null, last);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        afterLinkLast();
        size++;
    }

    protected void afterLinkFirst() {

    }

    protected void afterLinkLast() {

    }

    @Override
    public T get(int index) {
        Node<T> current = node(index);
        return current == null ? null : current.value;
    }

    @Override
    public T set(int index, T element) {
        rangeCheck(index);
        DualNode<T> x = node(index);
        if (x == null) return null;
        T oldVal = x.value;
        x.value = element;
        return oldVal;
    }

    @Override
    public void add(int index, T element) {
        rangeCheck(index);
        if (index == size)
            linkLast(element);
        else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        return unLink(node(index));
    }

    @Override
    public int indexOf(T o) {
        return innerIndexOf(o);
    }

    protected DualNode<T> node(int index) {
        rangeCheck(index);
        DualNode<T> current;
        if (index > size >>> 1) {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        } else {
            current = head;
            for (int i = 0; i < index; i++) {
                current = (DualNode<T>) current.next;
            }
        }
        return current;
    }

    @Override
    public void clear() {
        DualNode<T> current = head;
        DualNode<T> next;
        while (current != null) {
            next = (DualNode<T>) current.next;
            current.value = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        head = null;
        size = 0;
    }

    public Iterator<T> reverseIterator() {
        return new ReverseIterator();
    }

    @Override
    public void offer(T t) {
        linkFirst(t);
    }

    @Override
    public T last() {
        return tail == null ? null : tail.value;
    }

    private class ReverseIterator implements Iterator<T> {

        DualNode<T> current;

        public ReverseIterator() {
            current = tail;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T val = current.value;
            current = current.prev;
            return val;
        }
    }

    /**
     * 双向链表节点
     * @param <T>
     */
    protected static class DualNode<T> extends Node<T> {
        public DualNode<T> prev = null;

        public DualNode(T value) {
            super(value);
        }

        public DualNode(T value, DualNode<T> next) {
            super(value, next);
        }

        public DualNode(T value, Node<T> next, DualNode<T> prev) {
            super(value, next);
            this.prev = prev;
        }


    }
}

package com.future.datastruct.list;

import com.future.datastruct.list.define.IDeque;
import com.future.datastruct.list.define.DualNode;
import com.future.datastruct.list.define.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * 双向链表
 */
@SuppressWarnings("unused")
public class DualLinkedList<T extends Comparable<T>> implements Iterable<T>, IDeque<T> {

    private DualNode<T> head;
    private DualNode<T> tail;
    private int size;

    public DualLinkedList() {
        head = tail = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(T o) {
       return indexOf(o) >= 0;
    }

    public T first() {
        return head == null ? null : head.value;
    }

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
    public boolean offer(T t) {
        return add(t);
    }

    @Override
    public T remove() {
        return removeLast();
    }

    @Override
    public T poll() {
        return unLinkFirst(head);
    }

    @Override
    public T peek() {
        return null;
    }

    public T pop() {
        return unLinkLast(tail);
    }

    public void push(T obj) {
        linkLast(obj);
    }

    public void unshift(T obj) {
        linkFirst(obj);
    }

    public T shift() {
        return unLinkFirst(head);
    }

    @SuppressWarnings("all")
    private T unLink(DualNode<T> node) {
        T value = node.value;
        DualNode<T> prev = node.prev;
        DualNode<T> next = (DualNode<T>) node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.value = null;
        size--;
        return value;
    }

    private T unLinkFirst(DualNode<T> node) {
        if (head == null) return null;
        T value = node.value;
        DualNode<T> next = (DualNode<T>) node.next;
        node.value = null;
        node.next = null;
        head = next;
        if (next == null) {
            tail = null;
        } else {
            next.prev = null;
        }
        size--;
        return value;
    }

    private T unLinkLast(DualNode<T> node) {
        if (tail == null) return null;
        T value = node.value;
        DualNode<T> prev = node.prev;
        node.value = null;
        node.prev = null;
        tail = prev;
        if (prev != null) {
            prev.next = null;
        } else {
            head = null;
        }
        size--;
        return value;
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
        size++;
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

    private void linkBefore(T e, DualNode<T> node) {
        final DualNode<T> pred = node.prev;
        final DualNode<T> newNode = new DualNode<>(e, node, pred);
        node.prev = newNode;
        if (pred == null)
            head = newNode;
        else
            pred.next = newNode;
        size++;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        return unLink(node(index));
    }

    @Override
    public int indexOf(T o) {
        DualNode<T> node = head;
        int index = 0;
        while (node != null) {
            if (node.value.equals(o)) {
                return index;
            }
            index++;
            node = (DualNode<T>) node.next;
        }
        return -1;
    }

    private DualNode<T> node(int index) {
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
    public Iterator<T> iterator() {
        return new Itr();
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
    }

    public Iterator<T> reverseIterator() {
        return new ReverseIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> current = head;
        while (current != null) {
            action.accept(current.value);
            current = current.next;
        }
    }

    @Override
    public void addFirst(T t) {
        linkFirst(t);
    }

    @Override
    public void addLast(T t) {
        linkLast(t);
    }

    @Override
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }

    @Override
    public boolean offerLast(T t) {
        addLast(t);
        return true;
    }

    public T removeFirst() {
        final DualNode<T> f = head;
        if (f == null)
            throw new NoSuchElementException();
        return unLinkFirst(f);
    }

    @Override
    public T removeLast() {
        final DualNode<T> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return unLinkLast(l);
    }

    @Override
    public T pollFirst() {
        final DualNode<T> t = head;
        return (t == null) ? null : unLinkFirst(t);
    }

    @Override
    public T pollLast() {
        final DualNode<T> t = tail;
        return (t == null) ? null : unLinkLast(t);
    }

    @Override
    public T getFirst() {
        final DualNode<T> l = head;
        if (l == null)
            throw new NoSuchElementException();
        return l.value;
    }

    @Override
    public T getLast() {
        final DualNode<T> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return l.value;
    }

    @Override
    public T peekFirst() {
        return head == null ? null : head.value;
    }

    @Override
    public T peekLast() {
        return tail == null ? null : tail.value;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private class Itr implements Iterator<T> {

        Node<T> current;

        public Itr() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T val = current.value;
            current = current.next;
            return val;
        }
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

}

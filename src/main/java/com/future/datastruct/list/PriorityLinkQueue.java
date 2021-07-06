package com.future.datastruct.list;

import com.future.datastruct.node.Node;

import java.util.Iterator;

/**
 * 链表实现的优先队列
 */
@SuppressWarnings("unused")
public class PriorityLinkQueue<E extends Comparable<E>> implements Iterable<E> {

    public Node<E> head = new Node<>();
    private int size = 0;

    public PriorityLinkQueue() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public E poll() {
        if (head.next == null) {
            return null;
        }
        Node<E> old = head.next;
        head.next = old.next;
        E oldVal = old.value;
        old.value = null;
        size--;
        return oldVal;
    }

    public void push(E e) {
        Node<E> node = newNode(e);
        Node<E> current = head;
        while (current.next != null && e.compareTo(current.next.value) > 0) {
            current = current.next;
        }
        node.next = current.next;
        current.next = node;
        size++;
    }

    private Node<E> newNode(E e) {
        return new Node<>(e);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private Node<E> current;
        public Itr() {
            current = head.next;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E val = current.value;
            current = current.next;
            return val;
        }
    }
}

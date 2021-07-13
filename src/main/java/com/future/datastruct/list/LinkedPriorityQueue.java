package com.future.datastruct.list;


import java.util.Iterator;

/**
 * 链表实现的优先队列
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class LinkedPriorityQueue<E extends Comparable<E>> implements Iterable<E> {

    public Node<E> head = new Node<>();
    private int size = 0;

    public LinkedPriorityQueue() {

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

    private static class Node<T> {
        public T value;
        public Node<T> next;

        public Node() {
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}

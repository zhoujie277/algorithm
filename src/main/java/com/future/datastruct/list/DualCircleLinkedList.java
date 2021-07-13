package com.future.datastruct.list;

import java.util.Iterator;

/**
 * 双向环形链表
 *
 * @author jayzhou
 */
public class DualCircleLinkedList<E> extends DualLinkedList<E> {

    private DualNode<E> current;
    // 正在使用中
    private boolean isUsing = false;

    @Override
    protected void afterLinkFirst() {
        head.prev = tail;
        tail.next = head;
        if (!isUsing) {
            current = head;
        }
    }

    @Override
    protected void afterLinkLast() {
        head.prev = tail;
        tail.next = head;
        if (!isUsing) {
            current = head;
        }
    }

    @Override
    protected void afterUnLink() {
        head.prev = tail;
        tail.next = head;
    }

    @SuppressWarnings("unused")
    public void reset() {
        current = head;
        isUsing = false;
    }

    public void next() {
        isUsing = true;
        current = (DualNode<E>) current.next;
    }

    public void removeCurrent() {
        Node<E> next = current.next;
        unLink(current);
        current = (DualNode<E>) next;
    }

    public E current() {
        return current.value;
    }

    @Override
    public String toString() {
        return "DualCircleLinkedList{" +
                "current=" + current +
                ", isUsing=" + isUsing +
                ", head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Iterator<E> reverseIterator() {
        return new ReverseIterator();
    }

    private class Itr implements Iterator<E> {

        Node<E> current;

        public Itr() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E val = current.value;
            if (current == tail) {
                current = null;
            } else {
                current = current.next;
            }
            return val;
        }
    }

    private class ReverseIterator implements Iterator<E> {

        DualNode<E> current;

        public ReverseIterator() {
            current = tail;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E val = current.value;
            if (current == head) {
                current = null;
            } else {
                current = current.prev;
            }
            return val;
        }
    }
}

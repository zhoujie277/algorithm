package com.future.datastruct.list.define;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractLinked<E> extends AbstractList<E> implements ILinked<E> {

    protected Node<E> head = null;

    @Override
    public boolean add(E t) {
        push(t);
        return true;
    }

    @Override
    public E last() {
        throw new UnsupportedOperationException("该链表结构不支持查看最后一个元素的操作");
    }

    @Override
    public void offer(E e) {
        throw new UnsupportedOperationException("该链表结构不支持往头部插入元素的操作");
    }

    @Override
    public boolean remove(E o) {
        throw new UnsupportedOperationException("该链表结构不支持删除操作");
    }

    @Override
    public E peek() {
        return head == null ? null : head.value;
    }

    @Override
    public void clear() {
        clear(head);
        head = null;
        size = 0;
    }

    protected void clear(Node<E> node) {
        Node<E> cur = node;
        while (cur != null) {
            Node<E> next = cur.next;
            cur.next = null;
            cur.value = null;
            cur = next;
        }
    }

    protected Node<E> node(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    protected int innerIndexOf(E t) {
        int index = 0;
        Node<E> cur = head;
        Node<E> fast = head.next;
        while (cur != null) {
            if (Objects.equals(cur.value, t)) {
                return index;
            }
            if (fast == cur) return ELEMENT_NOT_FOUND;
            index++;
            cur = cur.next;
            if (fast != null && fast.next != null) fast = fast.next.next;
        }
        return ELEMENT_NOT_FOUND;
    }


    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Iterator<E> reverseIterator() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Linked{" + "size=").append(size).append('[');
        for (E e : this) {
            builder.append(e);
            builder.append(",");
        }
        builder.append("]}");
        return builder.toString();
    }

    private class Itr implements Iterator<E> {
        Node<E> cursor = head;
        Node<E> fast = head.next;

        @Override
        public boolean hasNext() {
            return cursor != null && fast != cursor;
        }

        @Override
        public E next() {
            E value = cursor.value;
            cursor = cursor.next;
            if (fast != null && fast.next != null) {
                fast = fast.next.next;
            } else {
                fast = null;
            }
            return value;
        }
    }

}

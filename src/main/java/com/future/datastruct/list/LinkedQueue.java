package com.future.datastruct.list;

import com.future.datastruct.list.define.AbstractLinked;
import com.future.datastruct.list.define.Node;

/**
 * 链表队列
 *
 * @author jayzhou
 */
public class LinkedQueue<T> extends AbstractLinked<T> {
    private Node<T> rear = null;

    public LinkedQueue() {
    }

    @Override
    public void push(T t) {
        Node<T> newNode = new Node<>(t, null);
        if (head == null) {
            head = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
    }

    @Override
    public void offer(T t) {
        push(t);
    }

    @Override
    public T peek() {
        return head == null ? null : head.value;
    }

    @Override
    public T pop() {
        return poll();
    }

    public T poll() {
        if (head == null) return null;
        Node<T> first = head;
        T value = first.value;
        first.value = null;
        head = first.next;
        first.next = null;
        size--;
        if (head == null) {
            rear = null;
        }
        return value;
    }

    @Override
    public void clear() {
        super.clear();
        rear = null;
    }

}

package com.future.datastruct.list;

import com.future.datastruct.list.define.AbstractLinked;
import com.future.datastruct.list.define.Node;

/**
 * 链表实现的栈
 *
 * @author jayzhou
 */
public class LinkedStack<E> extends AbstractLinked<E> {

    public LinkedStack() {
    }

    @Override
    public void push(E e) {
        if (e == null) return;
        head = new Node<>(e, head);
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        Node<E> first = head;
        E value = first.value;
        head = first.next;
        first.next = null;
        size--;
        return value;
    }

    @Override
    public E poll() {
        return pop();
    }

}

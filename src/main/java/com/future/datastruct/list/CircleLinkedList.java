package com.future.datastruct.list;

import com.future.datastruct.list.define.Node;

/**
 * 单向环形链表
 *
 * @author jayzhou
 */
public class CircleLinkedList<T> extends SingleLinkedList<T> {

    private Node<T> tail;

    public CircleLinkedList() {
        tail = null;
    }

    @Override
    protected void afterAppend(Node<T> prev, Node<T> node) {
        if (tail == null) {
            tail = node;
        } else if (tail.next == node){
            tail = node;
        }
    }

    @Override
    protected void beforeRemove(Node<T> prev, Node<T> node) {
        if (tail == null) return;
        if (node == tail) tail = prev;
    }

    @Override
    protected boolean reachEnd(Node<T> node) {
        return node == null || node == tail.next;
    }

}


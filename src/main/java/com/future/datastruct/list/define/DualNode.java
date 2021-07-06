package com.future.datastruct.list.define;

/**
 * 双向链表节点
 * @param <T>
 */
public class DualNode<T> extends Node<T> {
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

package com.future.datastruct.node;

import java.io.Serializable;

public class Node<T> implements Serializable {
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
}

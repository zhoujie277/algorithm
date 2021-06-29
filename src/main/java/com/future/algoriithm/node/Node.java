package com.future.algoriithm.node;

public class Node<T extends Comparable> implements Comparable<Node<T>> {
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

    public int compareTo(Node<T> o) {
        return value.compareTo(o.value);
    }

}

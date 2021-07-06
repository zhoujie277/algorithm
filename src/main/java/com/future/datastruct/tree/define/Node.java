package com.future.datastruct.tree.define;

/**
 * 二叉树节点
 */
public class Node<T> {

    public static final byte FLAG_CHILD = 0;
    public static final byte FLAG_THREADED = 1;

    public T value;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    public byte lChildFlag;
    public byte rChildFlag;

    public boolean lChildThreaded() {
        return lChildFlag == FLAG_THREADED;
    }

    public boolean rChildThreaded() {
        return rChildFlag == FLAG_THREADED;
    }

    public Node(T value, Node<T> left) {
        this(value, left, null);
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this(value, left, right,null);
    }

    public Node(T value, Node<T> left, Node<T> right, Node<T> parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.lChildFlag = FLAG_CHILD;
        this.rChildFlag = FLAG_CHILD;
    }

    public Node(T value) {
        this(value, null, null);
    }

    public Node() {
        this(null, null, null);
    }
}

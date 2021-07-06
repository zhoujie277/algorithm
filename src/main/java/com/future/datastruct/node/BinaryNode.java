package com.future.datastruct.node;

/**
 * 二叉树节点
 */
public class BinaryNode<T> extends Node<T> {

    public static final byte FLAG_CHILD = 0;
    public static final byte FLAG_THREADED = 1;

    public BinaryNode<T> left;
    public BinaryNode<T> right;
    public byte lChildFlag;
    public byte rChildFlag;

    public boolean lChildThreaded() {
        return lChildFlag == FLAG_THREADED;
    }

    public boolean rChildThreaded() {
        return rChildFlag == FLAG_THREADED;
    }

    public BinaryNode(T value, BinaryNode<T> left) {
        this(value, left, null);
    }

    public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
        super(value, null);
        this.left = left;
        this.right = right;
        this.lChildFlag = FLAG_CHILD;
        this.rChildFlag = FLAG_CHILD;
    }

    public BinaryNode(T value) {
        this(value, null, null);
    }

    public BinaryNode() {
        this(null, null, null);
    }
}

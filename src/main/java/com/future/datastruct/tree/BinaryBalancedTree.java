package com.future.datastruct.tree;

public class BinaryBalancedTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    protected void leftRotate(Node<E> g) {
        Node<E> p =  g.right;
        g.right = p.left;
        if (p.left != null) {
            p.left.parent = g;
        }
        p.left = g;
        updateRotate(g, p);
    }

    protected void rightRotate(Node<E> g) {
        Node<E> p = g.left;
        g.left = p.right;
        if (p.right != null) {
            p.right.parent = g;
        }
        p.right = g;
        updateRotate(g, p);
    }

    protected void updateRotate(Node<E> g, Node<E> p) {
        if (g.parent == null) {
            root = p;
            p.parent = null;
        } else if (g.parent.left == g) {
            g.parent.left = p;
        } else if (g.parent.right == g) {
            g.parent.right = p;
        }
        p.parent = g.parent;
        g.parent = p;
    }
}

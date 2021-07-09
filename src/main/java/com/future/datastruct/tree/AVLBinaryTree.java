package com.future.datastruct.tree;

import sun.tools.jconsole.JConsole;

import java.util.logging.Logger;

/**
 * 平衡二叉树，又称AVL树，又称平衡二叉搜索树
 * 因为二叉排序树存在有可能变成单链表的情况，或者类似单链表。如果根节点的值很小，那么后面的值就会形成一个类似单链表的树，其查询效率会很低
 * 因此，出现了平衡二叉树，常见的平衡二叉树实现方法有：AVL、红黑树、替罪羊树、Treap树、伸展树。
 * 它具有以下特点：
 * 它是一颗空树或者它的左右两颗子树的高度差的绝对值不超过1，并且它的左右子树也都是平衡二叉树
 */
public class AVLBinaryTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    @Override
    protected void fixAfterDeletion(Node<E> node) {
        // 修复删除之后导致的失衡
    }

    /**
     * 修复插入导致的失衡
     * 本身是一颗平衡二叉树，添加之后，可能会影响到父节点或者其祖结点的失衡
     * 所以需要检查该树所在的父节点及其祖结点是不是平衡，找到一个失衡的结点即可返回
     * 修复最近的祖结点的失衡，即可修复由于该结点插入导致的失衡。
     */
    @Override
    protected void fixAfterInsertion(Node<E> node) {
        AVLNode<E> c = (AVLNode<E>) node;
        updateNodeHeight(c);
        AVLNode<E> p = (AVLNode<E>) node.parent;
        if (p == null) return;
        updateNodeHeight(p);
        while (p.parent != null) {
            AVLNode<E> g = (AVLNode<E>) p.parent;
            updateNodeHeight(g);
            if (!isBalanced(g)) {
                if (g.left == p) {
                    // LR
                    if (p.right == c) {
                        leftRotate(p);
                    }
                    //LL
                    rightRotate(g);
                } else {
                    // RL
                    if (p.left == c) {
                        rightRotate(p);
                    }
                    // RR
                    leftRotate(g);
                }
                break;
            } else {
                c = p;
                p = g;
            }
        }
    }

    private void leftRotate(AVLNode<E> g) {
        AVLNode<E> p = (AVLNode<E>) g.right;
        g.right = p.left;
        p.left = g;
        updateRotate(g, p);
    }

    private void rightRotate(AVLNode<E> g) {
        AVLNode<E> p = (AVLNode<E>) g.left;
        g.left = p.right;
        p.right = g;
        updateRotate(g, p);
    }

    private void updateRotate(AVLNode<E> g, AVLNode<E> p) {
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
        updateNodeHeight(g);
    }

    protected boolean isBalanced(AVLNode<E> node) {
        AVLNode<E> left = (AVLNode<E>) node.left;
        AVLNode<E> right = (AVLNode<E>) node.right;
        int leftHeight = (left == null) ? 0 : left.height;
        int rightHeight = (right == null) ? 0 : right.height;
        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    private void updateNodeHeight(AVLNode<E> node) {
        AVLNode<E> left = (AVLNode<E>) node.left;
        AVLNode<E> right = (AVLNode<E>) node.right;
        int leftHeight = (left == null) ? 0 : left.height;
        int rightHeight = (right == null) ? 0 : right.height;
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    protected AVLNode<E> newNode(E e) {
        return new AVLNode<>(e);
    }

    private static class AVLNode<E> extends Node<E> {
        public int height;

        public AVLNode(E value) {
            super(value);
        }

        protected Node<E> newNode(E value) {
            return new AVLNode<>(value);
        }

        @Override
        public String toString() {
            return value + "(" + height + ")";
        }
    }
}

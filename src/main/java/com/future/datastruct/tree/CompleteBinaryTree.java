package com.future.datastruct.tree;

import com.future.datastruct.tree.define.Node;

/**
 * 链表结构的完全二叉树
 * 完全二叉树的特点：
 * 设第1个节点（根节点）的位置为0，则则第二个节点的位置为1，2，
 * 如此，便有了，
 * 1号节点的两个子节点分别为3、4；
 * 2号节点的两个子节点分别为5、6；
 * 3号节点的两个子节点分别为7、8；
 * 所以
 * K号节点的两个子节点位置分别为2k + 1、2k + 2;
 * 并且k号节点的父节点位置为 (k - 1) / 2
 *
 * 如果一棵完全二叉树有768个结点，求叶子结点的个数
 * 解：因为二叉树满足 n = n0 + n1 + n2, 且 n0 = n2 + 1，则有 n = 2n0 + n1 - 1;
 * 又因为完全二叉树下：n1的结点个数要么是0，要么是1，且结点个数肯定为整数。
 * 所以，该题的答案是：384
 */
@SuppressWarnings("unused")
public class CompleteBinaryTree<E> extends AbstractBinaryTree<E> {

    public CompleteBinaryTree() {
    }

    public CompleteBinaryTree(E[] array) {
        initTree(array);
    }

    @SuppressWarnings("all")
    public void initTree(E[] array) {
        Node<E>[] nodeArray = new Node[array.length];
        for (int i = 0; i < array.length; i++) {
            nodeArray[i] = newNode(array[i]);
        }
        root = nodeArray[0];
        Node<E> node;
        for (int i = 0; i < nodeArray.length / 2; i++) {
            node = nodeArray[i];
            node.left = nodeArray[2 * i + 1];
            if (2 * i + 2 < array.length) {
                node.right = nodeArray[2 * i + 2];
            }
        }
        size = nodeArray.length;
    }

    /**
     * 以完全二叉树的形式添加
     */
    @Override
    public E add(E e) {
        Node<E> node = newNode(e);
        if (root == null) {
            root = node;
        } else {
            insertTail(root, node);
        }
        size++;
        return null;
    }

    /**
     * 完全二叉树的特点：
     * 如果左子树的最小深度大于右子树的最小深度，则应该将节点插在右端，继续递归
     * 如果深度
     */
    private void insertTail(Node<E> parent, Node<E> newNode) {
        int leftDepth = minDepth(parent.left);
        int rightDepth = minDepth(parent.right);
        if (leftDepth > rightDepth) {
            if (parent.right == null) {
                parent.right = newNode;
            } else {
                insertTail(parent.right, newNode);
            }
        } else {
            if (parent.left == null) {
                parent.left = newNode;
            } else {
                insertTail(parent.left, newNode);
            }
        }
    }

    public void push(E e) {
        int depth = maxDepth(root);
        Node<E> node = newNode(e);
        int subTreeDepth = depth;
        if ((size & (size + 1)) != 0) {
            subTreeDepth -= 1;
        }
        boolean result = push(root, node, 1, subTreeDepth);
        if (result) {
            size++;
        }
    }

    /**
     * 前序遍历方式添加
     * 当前最大深度为depth，最后一层固定为叶子节点，若当前为满二叉树，则叶子节点的深度就是子树节点的深度；
     * 反之，则子树节点深度为叶子节点的depth - 1;
     */
    private boolean push(Node<E> current, Node<E> newNode, int curDepth, int subTreeDepth) {
        if (curDepth == subTreeDepth) {
            if (current.left == null) {
                current.left = newNode;
                return true;
            } else if (current.right == null) {
                current.right = newNode;
                return true;
            }
        } else if (curDepth < subTreeDepth) {
            boolean result = push(current.left, newNode, curDepth + 1, subTreeDepth);
            if (!result) {
                result = push(current.right, newNode, curDepth + 1, subTreeDepth);
            }
            return result;
        }
        return false;
    }

    public boolean remove(E e) {
        //todo
        return false;
    }

}

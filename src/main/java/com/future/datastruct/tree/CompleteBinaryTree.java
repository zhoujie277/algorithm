package com.future.datastruct.tree;

import java.util.Iterator;
import java.util.Objects;

/**
 * 完全二叉树：叶子结点只会出现在最后两层，而且最后一层叶子结点是靠左对齐。
 * 故  完全二叉树度为1的结点最多只有一个，
 * 且总结点数量为：2^(h-1) <= n < 2^h - 1; 可得出： h - 1 <= logN < h, h = floor(logN) + 1
 * <p>
 * <p>
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
 * <p>
 * 如果一棵完全二叉树有768个结点，求叶子结点的个数
 * 解：因为二叉树满足 n = n0 + n1 + n2, 且 n0 = n2 + 1，则有 n = 2n0 + n1 - 1;
 * 又因为完全二叉树下：n1的结点个数要么是0，要么是1，且结点个数肯定为整数。
 * 所以，该题的答案是：384
 */
public class CompleteBinaryTree<E> extends BinaryTree<E> {

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
        E oldVal = null;
        if (root == null) {
            root = newNode(e);
        } else {
            oldVal = insertTail(e);
        }
        size++;
        return oldVal;
    }

    @Override
    public int height() {
        int height = 0;
        int s = size;
        while (s > 0) {
            s >>>= 1;
            height++;
        }
        return height;
    }

    @Override
    protected int depth(Node<E> node) {
        return height();
    }

    private E insertTail(E value) {
        Node<E> parent = null;
        Iterator<Node<E>> nodeIterator = breadthFirstSearchNodeIterator();
        while (nodeIterator.hasNext()) {
            Node<E> node = nodeIterator.next();
            if (Objects.equals(value, node.value)) {
                E oldVal = node.value;
                node.value = value;
                return oldVal;
            }
            // 找到第一个度不是的2那个结点就是父节点
            if (parent == null && !isDegreeTwo(node)) {
                parent = node;
            }
        }
        // 此处表明没有相同元素
        if (parent != null) {
            Node<E> eNode = newNode(value);
            if (parent.left == null) {
                parent.left = eNode;
            } else {
                parent.right = eNode;
            }
            eNode.parent = parent;
        }
        return null;
    }

    public void push(E e) {
        int depth = depth(root);
        Node<E> node = newNode(e);
        int subTreeDepth = depth;
        // 如果不是满二叉树，则在当前深度添加，如果是，则在下一深度添加
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
            } else if (current.right == null) {
                current.right = newNode;
            }
            newNode.parent = current;
            return true;
        } else if (curDepth < subTreeDepth) {
            boolean result = push(current.left, newNode, curDepth + 1, subTreeDepth);
            if (!result) {
                result = push(current.right, newNode, curDepth + 1, subTreeDepth);
            }
            return result;
        }
        return false;
    }

    public E remove(E e) {
        Node<E> tail = findTail();
        if (tail == null) return null;
        E oldVal = null;
        Iterator<Node<E>> nodeIterator = breadthFirstSearchNodeIterator();
        while (nodeIterator.hasNext()) {
            Node<E> next = nodeIterator.next();
            if (Objects.equals(next.value, e)) {
                oldVal = next.value;
                next.value = tail.value;
                break;
            }
        }
        if (oldVal == null) return null;
        Node<E> parent = tail.parent;
        if (parent.right == tail) {
            parent.right = null;
        } else {
            parent.left = null;
        }
        tail.parent = null;
        return oldVal;
    }

    private Node<E> findTail() {
        int index = 0;
        Iterator<Node<E>> nodeIterator = breadthFirstSearchNodeIterator();
        while (nodeIterator.hasNext()) {
            Node<E> next = nodeIterator.next();
            if (index == size - 1) {
                return next;
            }
            index++;
        }
        return null;
    }

    @SuppressWarnings("all")
    public E[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Iterator<Node<E>> nodeIterator = breadthFirstSearchNodeIterator();
        while (nodeIterator.hasNext()) {
            array[index++] = nodeIterator.next();
        }
        return (E[]) array;
    }

    /**
     * 叶子结点个数
     * 因 n = n0 + n1 + n2 且 n0 = n2 + 1;
     * 则 n = 2n0 + n1 - 1;
     * 故 n0 = (n + 1 - n1) >> 1;
     * 又因为在完全二叉树中，n1 <= 0;
     * 情况A：当 n1 = 1 时，n 必定为偶数，此时，n0 = n >> 1;
     * 情况B：当 n1 = 0 时，n 必定会奇数；此时，n0 = (n + 1) >> 1;
     * 情况A的值必定小于情况B的值。
     * 且 Java中除法默认省去向下取整，当任意数a为偶数时，有 a / 2 == (a + 1) / 2
     * 故 n0 = (n + 1) >> 1;
     */
    public int leafCount() {
        return (size + 1) >> 1;
    }
}

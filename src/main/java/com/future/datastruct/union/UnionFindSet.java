package com.future.datastruct.union;

import com.future.datastruct.map.HashTable;

import java.util.Objects;

/**
 * 泛型并查集
 * 基于QuickUnion + rank + pathHalving 方案实现的并查集
 *
 * @author jayzhou
 */
public class UnionFindSet<E> {

    private HashTable<E, Node<E>> table = new HashTable<>();

    /**
     * 添加元素
     */
    public void makeSet(E element) {
        if (table.containsKey(element)) return;
        table.put(element, new Node<>(element));
    }

    /**
     * 查找元素所属的集合
     *
     * @param e 指定元素
     * @return 所属集合的代表元素
     */
    public E find(E e) {
        Node<E> node = findNode(e);
        return node == null ? null : node.element;
    }

    public void union(E e1, E e2) {
        Node<E> p1 = findNode(e1);
        Node<E> p2 = findNode(e2);
        if (p1 == null || p2 == null) return;
        if (Objects.equals(p1.element, p2.element)) return;
        if (p1.rank == p2.rank) {
            p1.parent = p2;
            p2.rank++;
        } else if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else {
            p2.parent = p1;
        }
    }

    private Node<E> findNode(E e) {
        Node<E> eNode = table.get(e);
        if (eNode == null) return null;
        //path halving
        while (eNode.parent != eNode) {
            // todo: 路径减半，但rank没有减小，有bug
            eNode.parent = eNode.parent.parent;
            eNode = eNode.parent;
        }
        return eNode;
    }

    public boolean isSame(E e1, E e2) {
        return Objects.equals(find(e1), find(e2));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (E next : table) {
            builder.append(next);
            builder.append(":");
            builder.append(table.get(next));
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * parent == this 说明就是该集合的代表元素（根结点）
     */
    protected static class Node<E> {
        E element;
        Node<E> parent;
        int rank;

        public Node(E element) {
            this.element = element;
            this.rank = 1;
            this.parent = this;
        }

        @Override
        public String toString() {
            Node<E> p = parent == this ? null : parent;
            return "Node{" +
                    "element=" + element +
                    ", parent=" + p +
                    ", rank=" + rank +
                    '}';
        }
    }
}

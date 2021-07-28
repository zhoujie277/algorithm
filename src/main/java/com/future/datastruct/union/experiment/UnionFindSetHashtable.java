//package com.future.datastruct.union.experiment;
//
//import com.future.datastruct.map.HashTable;
//
//import java.util.Objects;
//
///**
// * 泛型并查集
// * 基于QuickUnion + size + pathHalving 方案实现的并查集
// * 采用路径压缩/分裂/减半等方案如果采用rank的方案，需要维护rank的更新，带来一定复杂度，
// * 而且随着find次数越多，待合并的两个集合高度几乎不会相差太多，甚至大概率会相等，不如采用size的优化更合适
// *
// * @author jayzhou
// */
//public class UnionFindSetHashtable<E> {
//
//    private final HashTable<E, Node<E>> table = new HashTable<>();
//
//    /**
//     * 添加元素
//     */
//    public void makeSet(E element) {
//        if (table.containsKey(element)) return;
//        table.put(element, new Node<>(element));
//    }
//
//    /**
//     * 查找元素所属的集合
//     *
//     * @param e 指定元素
//     * @return 所属集合的代表元素
//     */
//    public E find(E e) {
//        Node<E> node = findNode(e);
//        return node == null ? null : node.element;
//    }
//
//    public void union(E e1, E e2) {
//        Node<E> p1 = findNode(e1);
//        Node<E> p2 = findNode(e2);
//        if (p1 == null || p2 == null) return;
//        if (Objects.equals(p1.element, p2.element)) return;
//        if (p1.size <= p2.size) {
//            p1.parent = p2;
//            p2.size += p1.size;
//        } else {
//            p2.parent = p1;
//            p1.size += p2.size;
//        }
//    }
//
//    private Node<E> findNode(E e) {
//        Node<E> eNode = table.get(e);
//        if (eNode == null) return null;
//        //path halving
//        while (eNode.parent != eNode) {
//            eNode.parent = eNode.parent.parent;
//            eNode = eNode.parent;
//        }
//        return eNode;
//    }
//
//    public boolean isSame(E e1, E e2) {
//        return Objects.equals(find(e1), find(e2));
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        for (E next : table) {
//            builder.append(next);
//            builder.append(":");
//            builder.append(table.get(next));
//            builder.append("\n");
//        }
//        return builder.toString();
//    }
//
//    /**
//     * parent == this 说明就是该集合的代表元素（根结点）
//     */
//    protected static class Node<E> {
//        E element;
//        Node<E> parent;
//        int size;
//
//        public Node(E element) {
//            this.element = element;
//            this.size = 1;
//            this.parent = this;
//        }
//
//        @Override
//        public String toString() {
//            Node<E> p = parent == this ? null : parent;
//            return "Node{" +
//                    "element=" + element +
//                    ", parent=" + p +
//                    ", size=" + size +
//                    '}';
//        }
//    }
//}

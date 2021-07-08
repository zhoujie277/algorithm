package com.future.datastruct.tree.huffman;

import com.future.datastruct.tree.BinaryTree;
import com.future.datastruct.list.HeapPriorityQueue;
import com.future.datastruct.list.LinkedQueue;

import java.util.Iterator;

/**
 * 哈夫曼树
 */
@SuppressWarnings("unused")
public class HuffmanTree<T> extends BinaryTree<T> implements Iterable<T> {

    public static <T> HuffmanTree<T> createTree(WeightNode<T>[] array) {
        HeapPriorityQueue<WeightNode<T>> queue = new HeapPriorityQueue<>(array);
        WeightNode<T> prev;
        WeightNode<T> current;
        int newWeight;
        WeightNode<T> node;
        while (queue.size() > 1) {
            prev = queue.poll();
            current = queue.poll();
            newWeight = prev.weight + current.weight;
            node = newNode(null, newWeight, prev, current);
            queue.push(node);
        }
        return new HuffmanTree<>(queue.poll());
    }

    public static <T> WeightNode<T> newNode(T value, int weight) {
        return new WeightNode<>(value, weight);
    }

    public static <T> WeightNode<T> newNode(T value, int weight, WeightNode<T> left, WeightNode<T> right) {
        return new WeightNode<>(value, weight, left, right);
    }

    private final WeightNode<T> root;

    public HuffmanTree(WeightNode<T> root) {
        this.root = root;
    }

    public HuffmanTable<T> generateTable() {
        HuffmanTable<T> table = new HuffmanTable<>();
        findCodec(root, "", table);
        return table;
    }

    private void findCodec(WeightNode<T> node, String prefix, HuffmanTable<T> table) {
        if (node.value != null) {
            table.put(node.value, prefix);
            return;
        }
        if (node.left != null) {
            findCodec((WeightNode<T>) node.left, prefix + "0", table);
        }
        if (node.right != null) {
            findCodec((WeightNode<T>) node.right, prefix + "1", table);
        }
    }

    public void getCodec() {

    }

    public int weightPathLength() {
        return wpl(root, 0);
    }

    private int wpl(WeightNode<T> node, int depth) {
        if (node.value != null) {
            return node.weight * depth;
        }
        int left = wpl((WeightNode<T>) node.left, depth + 1);
        int right = wpl((WeightNode<T>) node.right, depth + 1);
        return left + right;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public T add(T element) {
        throw new UnsupportedOperationException("哈夫曼树不允许添加元素");
    }

    @Override
    public T remove(T element) {
        throw new UnsupportedOperationException("哈夫曼树不允许删除元素");
    }

    private class Itr implements Iterator<T> {

        private final LinkedQueue<WeightNode<T>> queue = new LinkedQueue<>();

        public Itr() {
            queue.push(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            WeightNode<T> node;
            while ((node = queue.poll()) != null && node.value == null) {
                if (node.left != null) {
                    queue.push((WeightNode<T>) node.left);
                }
                if (node.right != null) {
                    queue.push((WeightNode<T>) node.right);
                }
            }
            return node != null ? node.value : null;
        }
    }

    /**
     * 带权的结点
     * @param <T>
     */
    protected static class WeightNode<T> extends Node<T> implements Comparable<WeightNode<T>> {
        public int weight;

        public WeightNode(T value, int weight) {
            this.weight = weight;
            this.value = value;
        }

        public WeightNode(T value, int weight, WeightNode<T> left, WeightNode<T> right) {
            this.weight = weight;
            this.left = left;
            this.right = right;
            this.value = value;
        }

        @Override
        public String toString() {
            return "WeightNode{" +
                    "value=" + value +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(WeightNode<T> o) {
            return o.weight - weight;
        }
    }
}

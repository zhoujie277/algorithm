package com.future.algoriithm.tree.huffman;

import com.future.algoriithm.node.WeightNode;
import com.future.algoriithm.queue.PriorityHeapQueue;
import com.future.algoriithm.queue.Queue;
import com.future.algoriithm.utils.PrintUtils;

import java.util.Iterator;

/**
 * 哈夫曼树
 */
@SuppressWarnings("unused")
public class HuffmanTree<T> implements Iterable<T> {

    public static <T> HuffmanTree<T> createTree(WeightNode<T>[] array) {
        PriorityHeapQueue<WeightNode<T>> queue = new PriorityHeapQueue<>(array);
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

    private class Itr implements Iterator<T> {

        private final Queue<WeightNode<T>> queue = new Queue<>();

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
}

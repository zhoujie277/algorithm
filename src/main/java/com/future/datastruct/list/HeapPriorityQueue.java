package com.future.datastruct.list;

import com.future.datastruct.heap.BinaryHeap;

import java.util.Arrays;
import java.util.Iterator;


/**
 * 优先队列
 * 二叉堆实现
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class HeapPriorityQueue<E extends Comparable<E>> implements Iterable<E> {

    private BinaryHeap<E> heap;

    public HeapPriorityQueue() {
        heap = new BinaryHeap<>();
    }

    public HeapPriorityQueue(int[] array) {
        Integer[] arr = Arrays.stream(array).boxed().toArray(Integer[]::new);
        heap = new BinaryHeap(arr);
    }

    public HeapPriorityQueue(E[] array) {
        heap = new BinaryHeap(array);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public E poll() {
        return heap.delMax();
    }

    public void push(E e) {
        heap.insert(e);
    }

    @Override
    public Iterator<E> iterator() {
        return heap.iterator();
    }
}

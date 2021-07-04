package com.future.algoriithm.queue;

import com.future.algoriithm.heap.BinaryMaxHeap;

import java.util.Arrays;
import java.util.Iterator;


/**
 * 优先队列
 * 二叉堆实现
 */
@SuppressWarnings("unused")
public class PriorityHeapQueue<E extends Comparable<E>> implements Iterable<E> {

    private BinaryMaxHeap<E> heap;

    public PriorityHeapQueue() {
        heap = new BinaryMaxHeap<>();
    }

    public PriorityHeapQueue(int[] array) {
        Integer[] arr = Arrays.stream(array).boxed().toArray(Integer[]::new);
        heap = new BinaryMaxHeap(arr);
    }

    public PriorityHeapQueue(E[] array) {
        heap = new BinaryMaxHeap(array);
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

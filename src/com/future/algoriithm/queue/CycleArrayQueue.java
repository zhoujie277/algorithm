package com.future.algoriithm.queue;

import com.future.algoriithm.PrintUtils;
import edu.princeton.cs.algs4.StdIn;

public class CycleArrayQueue<T> {
    private int front;
    private int rear;
    private Object[] arr;
    private int capacity;
    private int size;

    public CycleArrayQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void add(T t) {
        if (isFull()) {
            PrintUtils.print("Full !");
            return;
        }
        size++;
        arr[rear] = t;
        rear = (rear + 1) % capacity;
    }

    public T get() {
        if (isEmpty()) {
            PrintUtils.print("Empty !");
            return null;
        }
        T old = (T) arr[front];
        front = (front + 1) % capacity;
        size --;
        return old;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Empty");
            return null;
        }
        return (T) arr[front];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void print() {
        System.out.printf("size: %d  front:%d  rear:%d", size, front, rear);
        System.out.println();
        for (int i = front; i < front + size; i++) {
            int index = i % capacity;
            System.out.printf("arr[%d]=%d\t", index, arr[index]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CycleArrayQueue<Integer> queue = new CycleArrayQueue<>(5);
        char code = ' ';
        while (code != 'q') {
            System.out.println("===================================");
            System.out.println("q exit; a add; g get; p peek; s show;");
            code = StdIn.readChar();
            if (code == 'a') {
                queue.add(StdIn.readInt());
            } else if (code == 'g') {
                Integer obj = queue.get();
                System.out.println("取出元素：" + obj);
            } else if (code == 'p') {
                System.out.println("头部元素：" + queue.peek());
            } else if (code == 's') {
                queue.print();
            }
            StdIn.readChar();
        }
    }
}

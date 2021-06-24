package com.future.algoriithm.queue;

import com.future.algoriithm.PrintUtils;
import com.future.algoriithm.Printable;
import com.future.algoriithm.node.Node;

import java.util.Iterator;

public class Queue <T extends Comparable<T>> implements Printable, Iterable {
    private Node<T> front;
    private Node<T> rear;
    private int count;

    public void push(T t) {
        Node<T> newNode = new Node<>(t, null);
        if (rear == null) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        count++;
    }

    public T unshift() {
        if (front == null) return null;
        Node<T> first = front;
        T value = first.value;
        first.value = null;
        front = first.next;
        first.next = null;
        count--;
        return value;
    }

    public int size() {
        return count;
    }

    @Override
    public void println() {
        PrintUtils.println("----------------Queue---------------");
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            PrintUtils.print(it.next());
        }
        PrintUtils.println();
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.push("beautiful");
        queue.push("hello world");
        queue.push("hundred");
        queue.push("thousand");
        queue.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        Node<T> cursor;

        Itr() {
            cursor = front;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            T value = cursor.value;
            cursor = cursor.next;
            return value;
        }
    }
}

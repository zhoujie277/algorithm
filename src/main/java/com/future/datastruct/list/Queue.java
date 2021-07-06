package com.future.datastruct.list;

import com.future.utils.PrintUtils;
import com.future.utils.Printable;
import com.future.datastruct.list.define.Node;

import java.util.Iterator;

@SuppressWarnings("unused")
public class Queue<T> implements Printable, Iterable<T> {
    private Node<T> front = null;
    private Node<T> rear = null;
    private int count;

    public Queue() {
    }

    public void push(T t) {
        Node<T> newNode = new Node<>(t, null);
        if (front == null) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        count++;
    }

    public T peek() {
        return front == null ? null : front.value;
    }

    public T poll() {
        if (front == null) return null;
        Node<T> first = front;
        T value = first.value;
        first.value = null;
        front = first.next;
        first.next = null;
        count--;
        if (front == null) {
            rear = null;
        }
        return value;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public void println() {
        PrintUtils.println("----------------Queue---------------");
        for (T t : this) {
            PrintUtils.print(t);
        }
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

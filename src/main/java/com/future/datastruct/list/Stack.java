package com.future.datastruct.list;

import com.future.utils.PrintUtils;
import com.future.utils.Printable;
import com.future.datastruct.list.define.Node;

import java.util.Iterator;

public class Stack<E> implements Printable, Iterable<E> {
    private Node<E> top = null;

    public Stack() {
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(E e) {
        if (e == null) return;
        Node<E> newNode = new Node<>(e, top);
        top = newNode;
    }

    public E peek() {
        if (isEmpty()) return null;
        return top.value;
    }

    public E pop() {
        if (isEmpty()) return null;
        Node<E> first = top;
        E value = first.value;
        top = first.next;
        first.next = null;
        return value;
    }

    @Override
    public void println() {
        PrintUtils.println("------------Stack--------------");
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            PrintUtils.print(iterator.next());
        }
        PrintUtils.println();
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.println();
        stack.push("zhoujie");
        stack.push("hello baby");
        stack.push("zhijin");
        stack.println();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        Node<E> cursor;

        public Itr() {
            this.cursor = top;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            E val = cursor.value;
            cursor = cursor.next;
            return val;
        }
    }
}

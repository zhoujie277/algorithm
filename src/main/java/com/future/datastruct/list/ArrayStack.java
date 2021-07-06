package com.future.datastruct.list;

import com.future.utils.PrintUtils;
import com.future.utils.Printable;

public class ArrayStack<E> implements Printable {
    private int capacity;
    private int top;
    private Object[] stack;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.top = -1;
        stack = new Object[capacity];
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(E e) {
        if (isFull()) return;
        top++;
        stack[top] = e;
    }

    public E pop() {
        if (isEmpty()) return null;
        E value = (E) stack[top];
        stack[top] = null;
        top--;
        return value;
    }

    @Override
    public void println() {
        E e;
        while ((e = pop()) != null) {
            PrintUtils.print(e + "\t");
        }
        PrintUtils.println();
    }

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>(10);
        stack.println();
        stack.push("zhoujie");
        stack.push("Wangfei");
        stack.push("zhijin");
        stack.println();
    }
}

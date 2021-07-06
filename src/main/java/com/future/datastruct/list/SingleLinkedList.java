package com.future.datastruct.list;

import com.future.utils.PrintUtils;
import com.future.datastruct.list.define.Node;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SingleLinkedList<T> {
    private Node<T> head;
    private int count;

    public SingleLinkedList() {
    }

    public Node<T> first() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // 尾插
    public int push(T e) {
        Node<T> newNode = new Node<>(e);
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            prev = current;
            current = current.next;
        }
        link(prev, newNode);
        count++;
        return count;
    }

    //头插
    public int unshift(T e) {
        Node<T> first = head;
        Node<T> newNode = new Node<>(e, first);
        head = newNode;
        count++;
        return count;
    }

    // 尾删
    public T pop() {
        if (head == null) return null;
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            if (current.next == null) {
                //此元素是最后一个元素
                break;
            }
            prev = current;
            current = current.next;
        }
        prev.next = null;
        count--;
        return current.value;
    }

    // 头删
    public T shift() {
        if (head == null) return null;
        Node<T> first = head;
        head = first.next;
        first.next = null;
        return first.value;
    }

    // 反向遍历
    public void reverseTraversal(Consumer<T> consumer) {
        reverseTraversal(head, consumer);
    }

    public void reverseTraversal(Node<T> current, Consumer<T> consumer) {
        if (current == null) return;
        reverseTraversal(current.next, consumer);
        consumer.accept(current.value);
    }

    public boolean remove(T e) {
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(e)) {
                unLink(prev, current);
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    private void link(Node<T> prev, Node<T> newNode) {
        if (head == null) {
            head = newNode;
        }
        if (prev != null) {
            prev.next = newNode;
        }
    }

    private void unLink(Node<T> prev, Node<T> current) {
        current.value = null;
        if (prev == null) {
            head = current.next;
        } else {
            prev.next = current.next;
        }
    }

    public void println() {
        Node<T> current = head;
        while (current != null) {
            PrintUtils.print(current.value + "\t");
            current = current.next;
        }
    }

    public void reverse() {
        Node<T> last = null;
        Node<T> current = head;
        Node<T> next;
        while (current != null) {
            next = current.next;
            current.next = last;
            last = current;
            current = next;
        }
        head = last;
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
        List<Integer> data = Arrays.asList(2, 9, 12, 3, 45, 6, 7);
        for (Integer i : data) {
            linkedList.push(i);
        }
        linkedList.println();
        linkedList.reverse();
        PrintUtils.println();
        linkedList.println();
        linkedList.reverse();
        PrintUtils.println();
        linkedList.println();
    }
}

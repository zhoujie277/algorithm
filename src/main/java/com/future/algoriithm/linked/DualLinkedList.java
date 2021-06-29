package com.future.algoriithm.linked;

import com.future.algoriithm.utils.PrintUtils;
import com.future.algoriithm.utils.Printable;
import com.future.algoriithm.node.DualNode;
import com.future.algoriithm.node.Node;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 双向链表
 */
@SuppressWarnings("unused")
public class DualLinkedList<T extends Comparable<T>> implements Printable, Iterable<T> {

    private DualNode<T> head;
    private DualNode<T> tail;
    private int count;

    public DualLinkedList() {
        head = tail = null;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T first() {
        return head == null ? null : head.value;
    }

    public boolean remove(T obj) {
        if (obj == null) return false;
        for(DualNode<T> f = head; f != null;) {
            if (f.value.equals(obj)) {
                unLink(f);
                return true;
            }
            f = (DualNode<T>) f.next;
        }
        return false;
    }

    public T poll() {
        return unLinkFirst(head);
    }

    public T pop() {
        return unLinkLast(tail);
    }

    public void push(T obj) {
        linkLast(obj);
    }

    public void unshift(T obj) {
        linkFirst(obj);
    }

    public T shift() {
        return unLinkFirst(head);
    }

    @SuppressWarnings("all")
    private T unLink(DualNode<T> node) {
        T value = node.value;
        DualNode<T> prev = node.prev;
        DualNode<T> next = (DualNode<T>) node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.value = null;
        count--;
        return value;
    }

    private T unLinkFirst(DualNode<T> node) {
        if (head == null) return null;
        T value = node.value;
        DualNode<T> next = (DualNode<T>) node.next;
        node.value = null;
        node.next = null;
        head = next;
        if (next == null) {
            tail = null;
        } else {
            next.prev = null;
        }
        count--;
        return value;
    }

    private T unLinkLast(DualNode<T> node) {
        if (tail == null) return null;
        T value = node.value;
        DualNode<T> prev = node.prev;
        node.value = null;
        node.prev = null;
        tail = prev;
        if (prev != null) {
            prev.next = null;
        } else {
            head = null;
        }
        count--;
        return value;
    }

    private void linkFirst(T obj) {
        DualNode<T> first = head;
        DualNode<T> newNode = new DualNode<>(obj, first, null);
        head = newNode;
        if (first == null) {
            tail = newNode;
        } else {
            first.prev = newNode;
        }
        count++;
    }

    private void linkLast(T obj) {
        DualNode<T> last = tail;
        DualNode<T> newNode = new DualNode<>(obj, null, last);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        count++;
    }

    private T get(int index) {
        Node<T> current = node(index);
        return current == null ? null : current.value;
    }

    private Node<T> node(int index) {
        if (index >= count) return null;
        DualNode<T> current;
        if (index > count >>> 1) {
            current = tail;
            for (int i = count - 1; i > index; i--) {
                current = current.prev;
            }
        } else {
            current = head;
            for (int i = 0; i < index; i++) {
                current = (DualNode<T>) current.next;
            }
        }
       return current;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    public Iterator<T> reverseIterator() {
        return new ReverseIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> current = head;
        while (current != null) {
            action.accept(current.value);
            current = current.next;
        }
    }

    @Override
    public void println() {
        PrintUtils.printf("-------------DualLinkListed(%d)------------\n", count);
        forEach((t) -> PrintUtils.printf("%s", t.toString()));
    }

    public static void main(String[] args) {
        DualLinkedList<Integer> dualLinkedList = new DualLinkedList<>();
        java.util.List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        Collections.shuffle(list);
        PrintUtils.println(list);
        for (Integer o : list) {
            dualLinkedList.linkLast(o);
        }
        dualLinkedList.println();
    }


    private class Itr implements Iterator<T> {

        Node<T> current;
        public Itr() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T val = current.value;
            current = current.next;
            return val;
        }
    }

    private class ReverseIterator implements Iterator<T> {

        DualNode<T> current;
        public ReverseIterator() {
            current = tail;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T val = current.value;
            current = current.prev;
            return val;
        }
    }

}

package com.future.datastruct.list;

import com.future.datastruct.list.define.AbstractLinked;
import com.future.datastruct.list.define.ISequence;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 实现了顺序访问接口的单向链表
 * @author jayzhou
 */
public class SingleLinkedList<T> extends AbstractLinked<T> implements ISequence<T> {

    // 尾插
    @Override
    public void push(T e) {
        Node<T> newNode = new Node<>(e);
        Node<T> prev = null;
        Node<T> current = head;
        while (!reachEnd(current)) {
            prev = current;
            current = current.next;
        }
        link(prev, newNode);
        afterAppend(prev, newNode);
        size++;
    }

    protected void afterAppend(Node<T> prev, Node<T> node) {

    }

    //头插
    @Override
    public void offer(T e) {
        Node<T> first = head;
        head = new Node<>(e, first);
        afterAppend(null, head);
        size++;
    }

    // 尾删
    @Override
    public T pop() {
        if (head == null) return null;
        Node<T> prev = null;
        Node<T> current = head;
        while (!reachEnd(current)) {
            prev = current;
            current = current.next;
        }
        //此元素是最后一个元素
        beforeRemove(prev, current);
        T val = current.value;
        current.value = null;
        if (prev != null) {
            prev.next = null;
        }
        size--;
        return val;
    }

    protected void beforeRemove(Node<T> prev, Node<T> node) {

    }

    protected boolean reachEnd(Node<T> node) {
        return node == null;
    }

    @Override
    public T poll() {
        if (head == null) return null;
        beforeRemove(null, head);
        Node<T> first = head;
        head = first.next;
        first.next = null;
        return first.value;
    }

    @Override
    public T last() {
        if (head == null) return null;
        Node<T> current = head;
        while (!reachEnd(current.next)) {
            current = current.next;
        }
        return current.value;
    }

    // 反向遍历
    @SuppressWarnings("unused")
    public void reverseTraversal(Consumer<T> consumer) {
        reverseTraversal(head, consumer);
    }

    public void reverseTraversal(Node<T> current, Consumer<T> consumer) {
        if (reachEnd(current)) return;
        reverseTraversal(current.next, consumer);
        consumer.accept(current.value);
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        Node<T> node = node(index);
        return node.value;
    }

    @Override
    public T set(int index, T element) {
        rangeCheck(index);
        Node<T> node = node(index);
        T oldVal = node.value;
        node.value = element;
        return oldVal;
    }

    @Override
    public void add(int index, T element) {
        rangeCheck(index);
        Node<T> tNode = new Node<>(element);
        if (index == 0) {
            tNode.next = head;
            head = tNode;
            afterAppend(null, head);
            size++;
            return;
        }
        Node<T> parent = node(index - 1);
        tNode.next = parent.next;
        parent.next = tNode;
        afterAppend(parent, tNode);
        size++;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T val;
        if (index == 0) {
            beforeRemove(null, head);
            val = head.value;
            head = head.next;
        } else {
            Node<T> parent = node(index - 1);
            beforeRemove(parent, parent.next);
            val = parent.next.value;
            parent.next = parent.next.next;
        }
        return val;
    }

    @Override
    public int indexOf(T o) {
        return innerIndexOf(o);
    }

    @Override
    public boolean remove(T e) {
        Node<T> prev = null;
        Node<T> current = head;
        while (!reachEnd(current)) {
            if (current.value.equals(e)) {
                beforeRemove(prev, current);
                unLink(prev, current);
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    protected void link(Node<T> prev, Node<T> newNode) {
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

    // 反转链表
    @SuppressWarnings("unused")
    public void reverse() {
        Node<T> last = null;
        Node<T> current = head;
        Node<T> next;
        while (!reachEnd(current)) {
            next = current.next;
            current.next = last;
            last = current;
            current = next;
        }
        head = last;
    }

    @Override
    public Iterator<T> reverseIterator() {
        return new ReverseItr();
    }

    private class ReverseItr implements Iterator<T> {
        private Node<T> newHead = null;
        public ReverseItr() {
            Node<T> cur = head;
            while (cur != null) {
                newHead = new Node<>(cur.value, newHead);
                cur = cur.next;
            }
        }

        @Override
        public boolean hasNext() {
            return newHead != null;
        }

        @Override
        public T next() {
            T val = newHead.value;
            newHead = newHead.next;
            return val;
        }
    }
}

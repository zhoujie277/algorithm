package com.future.datastruct.list;

import com.future.datastruct.list.define.Node;


/**
 * 单向有序链表
 *
 * @author jayzhou
 */
public class SingleSortedLinkedList<T extends Comparable<T>> extends SingleLinkedList<T> {

    @Override
    public void push(T obj) {
        nullCheck(obj);
        insert(obj);
    }

    @Override
    public void offer(T t) {
        nullCheck(t);
        insert(t);
    }

    private void insert(T t) {
        Node<T> target = new Node<>(t);
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            if (target.value.compareTo(current.value) < 0) {
                break;
            }
            prev = current;
            current = current.next;
        }
        if (prev == null) {
            target.next = head;
            head = target;
        } else {
            prev.next = target;
            target.next = current;
        }
        size++;
    }
}

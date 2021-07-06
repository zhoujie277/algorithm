package com.future.datastruct.list;

import com.future.utils.PrintUtils;
import com.future.datastruct.list.define.Node;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * 单向有序链表
 * 设计说明：
 * 1、使用head作为固定头节点的设计，可在删除和添加的时候，不用移动head节点，从而避免很多麻烦
 * 2、在单向链表有序插入时，使用目标元素和当前节点的下一个节点进行比较，这样就能在插入的时候，仍然持有当前节点的引用，并且可以更新当前节点的next引用。
 *
 * @param <T>
 */
public class SingleSortedLinkedList<T extends Comparable<T>> {

    private Node<T> head = new Node<>(null, null);
    private int count;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public T first() {
        return get(0);
    }

    public boolean remove(T obj) {
        if (obj == null) return false;
        Node<T> current = head;
        while (current.next != null) {
            if (obj.compareTo(current.next.value) == 0) {
                current.next = current.next.next;
                current.next.next = null;
                count--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void add(T obj) {
        Node<T> target = new Node<>(obj);
        Node<T> current = head;
        while (current.next != null) {
            if (target.value.compareTo(current.next.value) < 0) {
                break;
            }
            current = current.next;
        }
        target.next = current.next;
        current.next = target;
        count++;
    }

    public T get(int index) {
        Node<T> current = node(index);
        return current == null ? null : current.value;
    }

    private Node<T> node(int index) {
        if (index < 0 || index >= count) return null;
        int c = 0;
        Node<T> current = head.next;
        boolean flag = false;
        while (current != null) {
            if (c == index) {
                flag = true;
                break;
            }
            current = current.next;
            c++;
        }
        if (!flag) return null;
        return current;
    }

    public void foreach(Consumer<T> consumer) {
        Node<T> current = head.next;
        while (current != null) {
            consumer.accept(current.value);
            current = current.next;
        }
    }

    public void print() {
        System.out.printf("-------------SingleLinkListed(%d)------------\n", count);
        foreach((t) -> {
            System.out.printf("%s\t", t.toString());
        });
        System.out.println("\n---------------------------------------------");
    }

    public void recursive(Node<T> current, Node<T> root) {
        if (current == null) return;
        recursive(current.next, root);
        Node<T> node = root;
        while (node.next != null) {
            node = node.next;
        }
        node.next = current;
        current.next = null;
    }

    public void reverse2() {
        PrintUtils.println("==========reverse==========");
        Node<T> reverse = new Node<>(null, null);
        recursive(head.next, reverse);
        head.next = reverse.next;
    }

    public void reverse() {
        PrintUtils.println("==========reverse==========");
        Node<T> reverse = new Node<>(null, null);
        Node<T> current = head.next;
        Node<T> next;
        while (current != null) {
            next = current.next;
            current.next = reverse.next;
            reverse.next = current;
            current = next;
        }
        head.next = reverse.next;
    }

    public void reverseTraversal(Node<T> current) {
        if (current == null) return;
        reverseTraversal(current.next);
        PrintUtils.print(current.value + "\t");
    }

    public static <T extends Comparable<T>> void merge(SingleSortedLinkedList<T> src, SingleSortedLinkedList<T> dst) {
        dst.foreach((t) -> {
            src.add(t);
        });
    }

    public static <T extends Comparable<T>> void reverseTraversal(SingleSortedLinkedList<T> srcList) {
        srcList.reverseTraversal(srcList.head.next);
    }

    public static void main(String[] args) {
        SingleSortedLinkedList<Integer> singleLinked = new SingleSortedLinkedList<>();
        java.util.List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(list);
        PrintUtils.println(list);
        for (Integer o : list) {
            singleLinked.add(o);
        }
        singleLinked.print();
        singleLinked.remove(8);
        singleLinked.print();
        PrintUtils.println(singleLinked.get(4));
        PrintUtils.println();
        reverseTraversal(singleLinked);
        PrintUtils.println();
        singleLinked.reverse();
        singleLinked.print();
        singleLinked.reverse();
        singleLinked.print();
    }
}

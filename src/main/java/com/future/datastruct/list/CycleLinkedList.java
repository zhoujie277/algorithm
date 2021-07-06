package com.future.datastruct.list;

import com.future.utils.PrintUtils;
import com.future.datastruct.node.Node;

import java.util.Objects;

/**
 * 单向环形链表
 *
 * @param <T>
 */
public class CycleLinkedList<T extends Comparable<T>> {

    private Node<T> head;
    private Node<T> tail;
    private int count;

    public CycleLinkedList(int capacity) {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(T e) {
        Node<T> newNode = new Node<>(e, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
        count++;
    }

    public int size() {
        return count;
    }

    public void remove(T e) {
        if (e == null) return;
        if (head == null) return;
        Node<T> prev = tail;
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(e)) {
                unLink(prev, current);
                break;
            }
            if (current == tail) {
                // 循环列表没找到元素小心无限循环
                break;
            }
            prev = current;
            current = current.next;
        }
    }

    private Node<T> unLink(Node<T> prev, Node<T> current) {
        Node<T> next = current.next;
        prev.next = next;
        if (current == head) {
            head = next;
        }
        if (current == tail) {
            tail = prev;
        }
        current.next = null;
        count--;
        return next;
    }

    public void println() {
        PrintUtils.println("---------------josephus------------------\n");
        Node<T> current = head;
        while (current != null) {
            PrintUtils.print(current.value + "\t");
            if (current == tail) break;
            current = current.next;
        }
        PrintUtils.println("\n-----------------------------------------");
    }

    public void moveHead(int offset) {
        Node<T> first = head;
        Node<T> last = tail;
        for (int i = 0; i < offset; i++) {
            first = first.next;
            last = last.next;
        }
        head = first;
        tail = last;
    }

    public void jsoephus(int remain, int number) {
        int index = 0;
        Node<T> prev = tail;
        Node<T> current = head;
        while (count > remain) {
            index++;
            if (index % number == 0) {
//                System.out.print("deleted:" + current.value + " ");
                current = unLink(prev, current);
//                System.out.printf(" reset counter 1 :" + current.value);
//                println();
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    public static void josephusTest() {
        //模拟约瑟夫问题的用例
        int capacity = 20;
        int k = 6;
        CycleLinkedList<Person> cycleLinkedList = new CycleLinkedList<Person>(capacity);
        for (int i = 0; i < capacity; i++) {
            Person p = new Person("h" + i);
            cycleLinkedList.add(p);
        }
        cycleLinkedList.println();
        // 从第k个人开始报数，offset = k - 1;
        int offset = k - 1;
        cycleLinkedList.moveHead(offset);
        cycleLinkedList.println();
        cycleLinkedList.jsoephus(1, 3);
        cycleLinkedList.println();
    }

    public static void main(String[] args) {
        josephusTest();
    }

}

class Person implements Comparable<Person> {
    String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "{" + name + '\'' + '}';
    }
}

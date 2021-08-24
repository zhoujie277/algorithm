package com.future.leetcode.linked;

/**
 * 双链表的简单实现
 * <p>
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 */
class DualLinkedList {

    private static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) return - 1;
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node node = new Node(val);
        if (tail == null) {
            tail = node;
        }
        node.next = head;
        if (head != null)
            head.prev = node;
        head = node;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if (tail == null) {
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        Node p = head;
        for (int i = 0; i < index - 1; i++) {
            p = p.next;
        }
        Node node = new Node(val);
        Node next = p.next;
        node.next = next;
        node.prev = p;
        next.prev = node;
        p.next = node;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node next = cur.next;
        Node prev = cur.prev;
        prev.next = next;
        if (cur == tail) {
            tail = prev;
        }
        if (next != null)
            next.prev = prev;
        cur.prev = null;
        cur.next = null;
        size--;
    }

    private void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println(" size=" + size);
    }

    public static void main(String[] args) {
        DualLinkedList linkedList = new DualLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(2);
        linkedList.addAtTail(3);
        linkedList.addAtTail(4);
        linkedList.addAtHead(6);
        linkedList.addAtHead(7);
//        linkedList.print();
        linkedList.addAtIndex(6, 8);
//        linkedList.print();
        linkedList.addAtHead(9);
        linkedList.addAtHead(10);
        linkedList.print();
        linkedList.deleteAtIndex(6);
        linkedList.print();
        linkedList.deleteAtIndex(6);
        linkedList.print();
        linkedList.deleteAtIndex(6);


    }
}

package com.future.algoriithm.practice;

import java.util.HashMap;

/**
 * LRU (Least Recently Used) 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶：你是否可以在O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 *
 * 链接：https://leetcode-cn.com/problems/lru-cache
 *
 * @author jayzhou
 */
public class LRUCache {
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    private final HashMap<Integer, Node> map = new HashMap<>();
    private final int cacheSize;
    private Node head;
    private Node tail;
    private int size;

    public LRUCache(int capacity) {
        cacheSize = capacity;
        size = 0;
    }

    private Node newNode(int key, int value) {
        Node node = new Node(key, value, head);
        if (head == null) {
            tail = node;
        } else {
            head.prev = node;
        }
        head = node;
        return node;
    }

    private void moveHead(Node node) {
        if (node == head) return;
        if (node == tail) tail = node.prev;
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        if (next != null) next.prev = prev;
        head.prev = node;
        node.next = head;
        node.prev = null;
        head = node;
    }

    private void removeOldest() {
        if (size <= cacheSize) return;
        size--;
        Node node = tail;
        tail = tail.prev;
        tail.next = null;
        node.prev = null;
        map.remove(node.key);
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            moveHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            map.put(key, newNode(key, value));
            size++;
            removeOldest();
        } else {
            node.value = value;
            moveHead(node);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node p = head;
        while (p != null) {
            builder.append(p.key).append(" ");
            if (p.prev == null && p != head) {
                builder.append("x" + p.key);
            }
            p = p.next;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache);
        lRUCache.get(1);    // 返回 1
        System.out.println(lRUCache);
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache);
        System.out.println(lRUCache.get(2)); //返回-1，未找到
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache);
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}

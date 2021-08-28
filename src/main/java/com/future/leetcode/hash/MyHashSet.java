package com.future.leetcode.hash;

/**
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * <p>
 * 0 <= key <= 10^6
 * 最多调用 10^4 次 add、remove 和 contains 。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/hash-table/xh377h/
 *
 * @author jayzhou
 */
class MyHashSet {

    private final Node[] nodes = new Node[1000];

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {

    }

    public void add(int key) {
        int hash = hash(key);
        Node p = nodes[hash];
        if (p == null) {
            nodes[hash] = new Node(key);
        } else {
            if (p.key == key) return;
            while (p.next != null) {
                if (p.next.key == key) return;
                p = p.next;
            }
            p.next = new Node(key);
        }
    }

    public void remove(int key) {
        int hash = hash(key);
        Node p = nodes[hash];
        Node parent = null;
        while (p != null) {
            if (p.key == key) break;
            parent = p;
            p = p.next;
        }
        if (p == null) return;
        if (parent == null) {
            nodes[hash] = p.next;
        } else {
            parent.next = p.next;
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int hash = hash(key);
        Node p = nodes[hash];
        while (p != null) {
            if (p.key == key) return true;
            p = p.next;
        }
        return false;
    }

    private int hash(int key) {
        return key % nodes.length;
    }

    private static class Node {
        int key;
        Node next;

        public Node(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.contains(1);
        hashSet.contains(3);
        hashSet.add(2);
        System.out.println(hashSet.contains(2));
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));
    }
}
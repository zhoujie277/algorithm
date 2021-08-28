package com.future.leetcode.hash;

/**
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 */
class MyHashMap {

    private final Node[] nodes = new Node[1000];

    private int hash(int key) {
        return key % nodes.length;
    }

    private static class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hash = hash(key);
        Node p = nodes[hash];
        if (p == null) {
            nodes[hash] = new Node(key, value);
        } else if (p.key == key) {
            p.value = value;
        } else {
            while (p.next != null) {
                if (p.next.key == key) {
                    p.next.value = value;
                    return;
                }
                p = p.next;
            }
            p.next = new Node(key, value);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = hash(key);
        Node p = nodes[hash];
        while (p != null) {
            if (p.key == key) return p.value;
            p = p.next;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
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

    public static void main(String[] args) {
        //["MyHashMap","put","put","get","get","put","get","remove","get"]
        //[[],[1,1],[2,2],[1],[3],[2,1],[2],[2],[2]]
        MyHashMap map = new MyHashMap();
        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map.get(1));
        System.out.println(map.get(3));
        map.put(2, 1);
        System.out.println(map.get(2));
        map.remove(2);
        System.out.println(map.get(2));
    }
}
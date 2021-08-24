package com.future.leetcode.linked;

import java.util.HashMap;

/**
 * 复制带有随机指针的链表
 * <p>
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的深拷贝。深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由n个节点组成的链表来表示输入/输出中的链表。每个节点用一个[val, random_index]表示：
 * <p>
 * val：一个表示Node.val的整数。
 * random_index：随机指针指向的节点索引（范围从0 到 n-1）；如果不指向任何节点，则为null。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/fdi26/
 *
 * @author jayzhou
 */
class CopyRandomList {
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = node.next;
            newNode.random = node.random == null ? null : node.random.next;
        }
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node newNode = node.next;
            node.next = newNode.next;
            newNode.next = node.next == null ? null : node.next.next;
        }
        return headNew;
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    private final HashMap<Node, Node> map = new HashMap<>();

    public Node recursion(Node head) {
        if (head == null) return null;
        Node node = map.get(head);
        if (node == null) {
            Node newNode = new Node(head.val);
            newNode.next = copyRandomList(head.next);
            newNode.random = copyRandomList(head.random);
            node = newNode;
            map.put(head, node);
        }
        return node;
    }

}

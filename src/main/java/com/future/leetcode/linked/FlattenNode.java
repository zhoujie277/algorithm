package com.future.leetcode.linked;

/**
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * <p>
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 * 以 示例 1 为例：
 * <p>
 * *  1---2---3---4---5---6--NULL
 * *          |
 * *          7---8---9---10--NULL
 * *              |
 * *              11--12--NULL
 * 序列化其中的每一级之后：
 * <p>
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
 * <p>
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * 合并所有序列化结果，并去除末尾的 null 。
 * <p>
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * <p>
 * 提示：
 * 节点数目不超过 1000
 * 1 <= Node.val <= 10^5
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/fw8v5/
 *
 * @author jayzhou
 */
public class FlattenNode {
    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node flatten(Node head) {
        if (head == null) return null;
        getTail(head);
        return head;
    }

    private Node getTail(Node head) {
        Node current = head;
        while (current != null) {
            Node next = current.next;
            Node child = current.child;
            if (child != null) {
                Node tail = getTail(child);
                child.prev = current;
                current.next = child;
                tail.next = next;
                if (next != null)
                    next.prev = tail;
                else
                    next = tail;
            }
            current.child = null;
            if (next == null) break;
            current = next;
        }
        return current;
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        one.child = two;
        two.child = three;
        new FlattenNode().flatten(one);
        Node c = one;
        while (c != null) {
            System.out.print(c.val + " ->");
            c = c.next;
        }
    }
}

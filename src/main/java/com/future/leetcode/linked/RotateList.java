package com.future.leetcode.linked;

/**
 * 旋转列表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 *
 * @author jayzhou
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        k = k % len;
        tail.next = head;
        ListNode prev = tail;
        ListNode newHead = head;
        for (int i = 0; i < len - k; i++) {
            prev = newHead;
            newHead = newHead.next;
        }
        prev.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode d = new ListNode(5);
        ListNode c = new ListNode(4, d);
        ListNode b = new ListNode(3, c);
        ListNode a = new ListNode(2, b);
        ListNode headA = new ListNode(1, a);
        ListNode node = new RotateList().rotateRight(headA, 0);
        while (node != null) {
            System.out.print(" " + node.val);
            node = node.next;
        }
    }
}

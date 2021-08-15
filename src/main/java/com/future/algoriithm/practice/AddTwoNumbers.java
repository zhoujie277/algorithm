package com.future.algoriithm.practice;

/**
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以0开头。
 * <p>
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * @author jayzhou
 */
public class AddTwoNumbers {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 增加哨兵结点，减小代码量
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int addition = 0, value;
        ListNode dummyNode = new ListNode(0, null);
        ListNode tail = dummyNode;
        while (l1 != null || l2 != null) {
            value = 0;
            if (l1 != null) {
                value += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                value += l2.val;
                l2 = l2.next;
            }
            value += addition;
            addition = value / 10;
            value = value % 10;
            tail.next = new ListNode(value, null);
            tail = tail.next;
        }
        if (addition > 0) {
            tail.next = new ListNode(addition, null);
        }
        return dummyNode.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int addition = 0, value;
        ListNode newHead = null, tail = null;
        while (l1 != null || l2 != null) {
            value = 0;
            if (l1 != null) {
                value += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                value += l2.val;
                l2 = l2.next;
            }
            value += addition;
            addition = 0;
            while (value >= 10) {
                value -= 10;
                addition += 1;
            }
            if (newHead == null) {
                newHead = tail = new ListNode(value, null);
            } else {
                tail.next = new ListNode(value, null);
                tail = tail.next;
            }
        }
        if (addition > 0) {
            tail.next = new ListNode(addition, null);
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode threeH = new ListNode(3, null);
        ListNode forty = new ListNode(4, threeH);
        ListNode l1 = new ListNode(2, forty);

        ListNode fourH = new ListNode(4, null);
        ListNode sixty = new ListNode(6, fourH);
        ListNode l2 = new ListNode(5, sixty);

        ListNode listNode = new AddTwoNumbers().addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }
}

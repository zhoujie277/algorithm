package com.future.leetcode.linked;

/**
 * 两数相加
 * <p>
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/fv6w7/
 *
 * @author jayzhou
 */
public class AddToNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode left = l1, right = l2;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int carry = 0;
        while (left != null || right != null) {
            int sum = carry;
            if (left != null) {
                sum += left.val;
                left = left.next;
            }
            if (right != null) {
                sum += right.val;
                right = right.next;
            }
            int mod = sum % 10;
            carry = sum / 10;
            ListNode node = new ListNode(mod);
            tail.next = node;
            tail = node;
        }
        if (carry > 0) {
            ListNode node = new ListNode(carry);
            tail.next = node;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode threeH = new ListNode(3, null);
        ListNode forty = new ListNode(4, threeH);
        ListNode l1 = new ListNode(2, forty);

        ListNode fourH = new ListNode(4, null);
        ListNode sixty = new ListNode(6, fourH);
        ListNode l2 = new ListNode(5, sixty);

        ListNode listNode = new AddToNumbers().addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }
}

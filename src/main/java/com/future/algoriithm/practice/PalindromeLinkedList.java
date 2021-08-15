package com.future.algoriithm.practice;

/**
 * 回文链表
 * 请判断一个链表是否为回文链表
 *
 * @author jayzhou
 */
public class PalindromeLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private ListNode reverseList(ListNode node) {
        ListNode newHead = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = newHead;
            newHead = node;
            node = next;
        }
        return newHead;
    }

    // 节点数大于等于3
    private ListNode midNode(ListNode node) {
        ListNode slow = node;
        ListNode quick = node.next;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;
        ListNode midNode = midNode(head);
        ListNode newHead = reverseList(midNode.next);
        ListNode cur = head;
        while (newHead != null) {
            if (cur.val != newHead.val) return false;
            cur = cur.next;
            newHead = newHead.next;
        }
        return true;
    }

    private void print(ListNode node) {
        while (node != null) {
            System.out.print("node=" + node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        ListNode pre = head, prepre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        if (fast != null) {
            slow = slow.next;
        }
        while (pre != null && slow != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        //1->2->2->1
        ListNode n6 = new ListNode(1, null);
        ListNode n5 = new ListNode(1, n6);
        ListNode n3 = new ListNode(2, n5);

        ListNode n2 = new ListNode(1, n3);

        boolean palindrome = new PalindromeLinkedList().isPalindrome(n2);
        System.out.println("palindrome=" + palindrome);
    }
}

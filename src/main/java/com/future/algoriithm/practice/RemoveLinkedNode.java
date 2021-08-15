package com.future.algoriithm.practice;

/**
 * 移除链表元素
 * <p>
 * 给你一个链表的头节点head和一个整数val ，请你删除链表中所有满足 Node.val == val 的节点，并返回新的头节点
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * @author jayzhou
 */
public class RemoveLinkedNode {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return val + " ";
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) return null;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode six = new ListNode(6, null);
        ListNode five = new ListNode(5, six);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode six2 = new ListNode(6, three);
        ListNode two = new ListNode(2, six2);
        ListNode head = new ListNode(1, two);
        ListNode listNode = new RemoveLinkedNode().removeElements(head, 6);
        while (listNode != null) {
            System.out.println(listNode);
            listNode = listNode.next;
        }
    }
}

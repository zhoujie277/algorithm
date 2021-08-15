package com.future.algoriithm.practice;

/**
 * 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 链接：https://leetcode-cn.com/problems/partition-list
 *
 * @author jayzhou
 */
public class DivideLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 代码简洁，清晰明了
    public ListNode partition(ListNode head, int x) {
        ListNode lHead = new ListNode(0, null);
        ListNode rHead = new ListNode(0, null);
        ListNode lTail = lHead;
        ListNode rTail = rHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                lTail.next = cur;
                lTail = cur;
            } else {
                rTail.next = cur;
                rTail = cur;
            }
            cur = cur.next;
        }
        lTail.next = rHead.next;
        rTail.next = null;
        return lHead.next;
    }

    public ListNode partition1(ListNode head, int x) {
        ListNode dummyNode = new ListNode(0, null);
        ListNode tail = dummyNode;
        ListNode cur = head, prev = null;
        while (cur != null) {
            if (cur.val < x) {
                tail.next = cur;
                tail = cur;
                if (prev != null) {
                    prev.next = cur.next;
                } else {
                    head = cur.next;
                }
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        tail.next = head;
        return dummyNode.next;
    }


    public static void main(String[] args) {
        ListNode n6 = new ListNode(2, null);
        ListNode n5 = new ListNode(5, n6);
        ListNode n3 = new ListNode(2, n5);

        ListNode n2 = new ListNode(3, n3);
        ListNode n1 = new ListNode(4, n2);

        ListNode l3 = new ListNode(1, n1);

        ListNode listNode = new DivideLinkedList().partition(l3, 3);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

}

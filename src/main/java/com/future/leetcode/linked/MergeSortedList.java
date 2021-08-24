package com.future.leetcode.linked;

/**
 * 合并有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class MergeSortedList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        ListNode l = l1, r = l2;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                tail.next = l;
                tail = l;
                l = l.next;
            } else {
                tail.next = r;
                tail = r;
                r = r.next;
            }
        }
        while (l != null) {
            tail.next = l;
            tail = l;
            l = l.next;
        }

        while (r != null) {
            tail.next = r;
            tail = r;
            r = r.next;
        }
        return dummy.next;
    }
}

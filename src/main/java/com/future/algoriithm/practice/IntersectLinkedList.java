package com.future.algoriithm.practice;

/**
 * 相交链表
 * <p>
 * 给你两个单链表的头节点headA和headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 题目数据保证整个链式结构中不存在环。函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 *
 * @author jayzhou
 */
public class IntersectLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 两条链表只要相交了，后面的相交结点数量肯定是一样的。所以拼接起来，用双指针遍历即可。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            curA = (curA == null) ? headB : curA.next;
            curB = (curB == null) ? headA : curB.next;
        }
        return curA;
    }

    // 朴素法
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headB == null) return null;
        while (headA != null) {
            ListNode curB = headB;
            while (curB != null) {
                if (headA == curB) {
                    return headA;
                }
                curB = curB.next;
            }
            headA = headA.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode n6 = new ListNode(5, null);
        ListNode n5 = new ListNode(4, n6);
        ListNode n3 = new ListNode(8, n5);

        ListNode n2 = new ListNode(1, n3);
        ListNode n1 = new ListNode(4, n2);

        ListNode l3 = new ListNode(1, n3);
        ListNode l2 = new ListNode(0, l3);
        ListNode l1 = new ListNode(5, l2);

        ListNode listNode = new IntersectLinkedList().getIntersectionNode(l1, n1);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }
}

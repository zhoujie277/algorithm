package com.future.leetcode.linked;

/**
 * 奇偶链表
 * <p>
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，
 * 而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * <p>
 * 说明:
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/fe0kj/
 *
 * @author jayzhou
 */
public class ParityLinkedList {

    /**
     * 据题意：需要将奇数结点挪到前面，其中包含两个操作，
     * 一、删除原来的位置中删除奇数结点；
     * 二、在前面的奇数结点最后面插入结点；
     * 显然：每一个操作都需要知道其父结点，故需要保留两个指针。
     * 一个指针指向待插入的奇数结点的前驱结点。
     * 另一个是被删除奇数结点的前驱结点（偶数结点）
     * 退出链表条件：当偶数结点的指向为空时，说明链表已不存在奇数结点。
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode prev = head;
        ListNode even = head.next;
        while (even != null && even.next != null) {
            ListNode odd = even.next;
            ListNode nextEven = odd.next;
            //delete odd
            even.next = nextEven;
            //insert odd
            odd.next = prev.next;
            prev.next = odd;
            //change
            prev = odd;
            even = nextEven;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode d = new ListNode(5);
        ListNode c = new ListNode(4, d);
        ListNode b = new ListNode(3, c);
        ListNode a = new ListNode(2, b);
        ListNode headA = new ListNode(1, a);
        new ParityLinkedList().oddEvenList(headA);
        while (headA != null) {
            System.out.print(" " + headA.val);
            headA = headA.next;
        }
    }
}

package com.future.leetcode.linked;

/**
 * 相交链表
 * 给你两个单链表的头节点headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 * 提示：
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 * <p>
 * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/jjbj2/
 *
 * @author jayzhou
 */
class IntersectionLinkedList {

    /**
     * 因是链表的相交。故会有一个隐含条件，两个链表自相交之后，便会在相交点之后是一个链表，不会分叉。
     * 因为结点只有一个Next指针。所以，相交的长度也是相等的。
     * 所以，相交的链表也就是它们的公共链表，并且，公共链表一定是两个链表的尾部，因为此后不会再分叉了。
     * 1、设第一个链表 A，长度为M； 第一个链表 B， 长度为 N；
     * 2、由上述分析，便可得知：如果将两条链表分别首尾相连后，便会有： A + B 这条新链表 D，以及 B + A的新链表 E。
     * 3、新链表 D 和 E ，很明显在长度上是相同的，并且刚刚分析过 A、B 的公共链表都在尾部，那么得到的新链表也必然在尾部会有相同长度的相交链表。
     * 4、此时就可得到了，D 和 E 的尾部公共链表就是 A 和 B 的公共链表。
     * 所以代码如下：
     * 特别要注意的是，判定条件一定要是 headA == null 才可使用链表B。
     * 1、因为链表没有真正意义上的相连，如果是 headA.next == null,则会陷入死循环，因为访问结点永远不会为空。
     * 使用headA相当于，在A与B的相加链表中增加了一个空结点访问，但没关系，最终两个链表总长度还是相等。
     * 2、使用 headA == null判定，因为访问的链表长度相同，所以最后它们会一起进入访问空结点，所以会退出循环。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }

    public static void main(String[] args) {
        //[4,1,8,4,5]
        //[5,6,1,8,4,5]
        ListNode d = new ListNode(5);
        ListNode c = new ListNode(4, d);
        ListNode b = new ListNode(8, c);
        ListNode a = new ListNode(1, b);
        ListNode headA = new ListNode(4, a);

        ListNode f = new ListNode(1, b);
        ListNode e = new ListNode(6, f);
        ListNode headB = new ListNode(5, e);
        ListNode node = new IntersectionLinkedList().getIntersectionNode(headA, headB);
        System.out.println(node == null ? null : node.val);
    }
}

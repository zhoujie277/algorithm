package com.future.leetcode.linked;

/**
 * 回文链表
 * <p>
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * @author jayzhou
 */
public class PalindromeList {

    /**
     * 算法思想：
     * 用慢指针一边遍历一边反转。
     * 用快指针控制循环次数，也就是控制慢指针反转链表的个数。
     * 快指针走两步，慢指针走一步。便可达到中心点。
     * 注意一点：fast指针使用时必须判断 fast != null && fast.next != null;
     * 否则交换指针会出错： fast = fast.next.next;
     * 当考虑到了这个情况，尤其要注意循环结束时，slow指针指向的位置。
     * 这里有几种情况：先假设循环主体逻辑在交换指针前运行，伪代码如下：
     * *    while (fast != null && fast.next != null) {
     * *            //...
     * *           slow = slow.next;
     * *          fast = fast.next.next;
     * *     }
     * 一、链表长度是奇数，中心点只有一个：mid。
     * *  1、快慢指针同时出发。则slow指针最后落点是在 mid，但是能处理的逻辑是前 (mid - 1)个结点
     * *  2、慢指针0点，快指针1点位置。则slow指针最后落点在 mid，能处理的逻辑是前 (mid - 1）个结点。
     * 二、链表长度是偶数，中心点定位左边的 mid。（向下取整的mid）
     * *  1、快慢指针同时出发。则slow指针最后落点是在 mid + 1，能处理的逻辑是前 mid个结点。
     * *  2、慢指针0点，快指针1点。则slow指针最后落点是在 mid 。能处理的逻辑是前 (mid - 1) 个结点。
     * <p>
     * 如果需要反转链表，奇数长度链表则必须要处理到 mid - 1， 偶数长度链表则必须要处理到左边的mid。
     * 据以上分析，所以选择 同时出发快慢指针。不管奇数偶数，最后slow的落点都是在另外半边未处理的字符串的头部。
     * 这里要特别留意一个细节，退出循环时，fast指针指向的位置。（只适用在起点同时出发的情况）
     * 1、如果fast指针是空，说明，该链表是偶数链表。
     * 2、如果fast指针不为空，说明，该链表是奇数链表。
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        if (head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;
        ListNode slow = head;
        ListNode fast = head;
        ListNode reverse = null;
        while (fast != null && fast.next != null) {
            ListNode nextFast = fast.next.next;
            ListNode next = slow.next;
            slow.next = reverse;
            reverse = slow;
            slow = next;
            fast = nextFast;
        }
        // 如果是偶数链表，slow指针指向的是 mid + 1，也就是回文右边的起点。
        if (fast != null) {
            // 链表是奇数链表，此时 slow是中心点，需要往后移动一位。
            slow = slow.next;
        }
        ListNode l = reverse;
        ListNode r = slow;
        while (l != null) {
            if (l.val != r.val) return false;
            l = l.next;
            r = r.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode n6 = new ListNode(1, null);
        ListNode n5 = new ListNode(2, n6);
        ListNode n3 = new ListNode(2, n5);
        ListNode n2 = new ListNode(1, n3);

        boolean palindrome = new PalindromeList().isPalindrome(n2);
        System.out.println("palindrome=" + palindrome);
    }
}

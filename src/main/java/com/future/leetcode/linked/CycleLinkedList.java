package com.future.leetcode.linked;

/**
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * 提示：
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/jbex5/
 */
class CycleLinkedList {

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 算法说明：
     * 第一个while循环检测链表是否有环；当循环退出后，slow 与 fast 相遇，则说明有环，反之，返回null。
     * 如果链表有环，使fast指针停留在第一次相遇点。slow指针回到原点。这时快慢指针都用同样的步调行走。再次相遇即为入口点。
     * 证明如下：
     * 1、设起点为 H， 入口点为 E， 第一次相遇点为 P。
     * 2、再设： H -> E 的距离为 A， E 到 P 的距离为 B， P 到 E的距离为C， 环的周长为L。
     * 3、则有：B + C = L；
     * 4、第一次相遇时，fast指针走过的总距离为：A + B + (B + C) * k。-> A + (k + 1)*B + kC;
     * 5、第一次相遇时，slow指针走过的距离为：A + B。而fast指针走过的距离是slow指针的 2 倍。
     * 6、则有：A + (k + 1)*B + kC = 2*(A + B); --> A = (k - 1) *B + kC --> A = (k - 1)*(B + C) + C;
     * 7、最终整理可得： A = (k - 1) * L + C;
     * 8、也就是说：一个指针从起点再走一个A的距离，另一个指针从相遇点再走 (k-1)*L + C的距离，便可再次相遇。
     * 9、此时再次相遇便是环的入口点。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast != slow) return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 检查链表是不是有环
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

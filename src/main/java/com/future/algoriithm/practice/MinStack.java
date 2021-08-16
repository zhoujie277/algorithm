package com.future.algoriithm.practice;

/**
 * 最小栈
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。
 * 执行push、pop和min操作的时间复杂度必须为O(1)。
 * 链接：https://leetcode-cn.com/problems/min-stack-lcci
 *
 * @author jayzhou
 */
class MinStack {

    private Node top = new Node(0, 0, null);

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        Node next = top.next;
        int min = (next != null && next.min < x) ? next.min : x;
        top.next = new Node(min, x, next);
    }

    public void pop() {
        top.next = top.next.next;
    }

    public int top() {
        if (top.next == null) return 0;
        return top.next.val;
    }

    public int getMin() {
        if (top.next == null) {
            return 0;
        }
        return top.next.min;
    }

    private static class Node {
        int min;
        int val;
        Node next;

        public Node(int min, int val, Node next) {
            this.min = min;
            this.val = val;
            this.next = next;
        }
    }
}
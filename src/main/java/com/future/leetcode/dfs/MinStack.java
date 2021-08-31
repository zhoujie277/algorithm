package com.future.leetcode.dfs;

/**
 * 最小栈
 * <p>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop()—— 删除栈顶的元素。
 * top()—— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class MinStack {
    private Node top = null;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int val) {
        int min = top == null ? val : Math.min(top.min, val);
        top = new Node(val, min, top);
    }

    public void pop() {
        if (top == null) return;
        top = top.next;
    }

    public int top() {
        return top == null ? -1 : top.val;
    }

    public int getMin() {
        return top == null ? -1 : top.min;
    }

    private static class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        //["MinStack","push","push","push","getMin","pop","top","getMin"]
        //[[],[-2],[0],[-3],[],[],[],[]]
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}

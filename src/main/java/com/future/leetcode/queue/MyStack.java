package com.future.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 用队列实现栈
 * <p>
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * <p>
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * <p>
 * 示例：
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 * <p>
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 * <p>
 * 提示：
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 * <p>
 * 进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执行 n 个操作的总时间复杂度 O(n) ，尽管其中某个操作可能需要比其他操作更长的时间。你可以使用两个以上的队列。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/gw7fg/
 *
 * @author jayzhou
 */
public class MyStack {
    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    private final Queue<Integer> q1 = new ArrayDeque<>();

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        q1.add(x);
        int n = q1.size();
        for (int i = 1; i < n; i++) {
            q1.add(q1.poll());
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        Integer poll = q1.poll();
        return poll == null ? -1 : poll;
    }

    /**
     * Get the top element.
     */
    public int top() {
        Integer poll = q1.peek();
        return poll == null ? -1 : poll;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.top());
    }
}

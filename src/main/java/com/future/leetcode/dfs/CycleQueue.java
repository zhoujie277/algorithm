package com.future.leetcode.dfs;

/**
 * 循环队列
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则
 * 并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * <p>
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，
 * 即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * 你的实现应该支持如下操作：
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/kzlb5/
 *
 * @author jayzhou
 */
public class CycleQueue {

    private final int length;
    private final int[] queue;
    private int front = 0;
    private int size = 0;

    public CycleQueue(int k) {
        this.length = k;
        queue = new int[k];
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        int index = index(front + size);
        queue[index] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        queue[front] = 0;
        front = index(front + 1);
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return queue[front];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return queue[index(front + size - 1)];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == length;
    }

    private int index(int index) {
        return index % length;
    }

    public static void main(String[] args) {
        //["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"]
        //[[6],[6],[],[],[],[5],[],[],[],[],[],[]]
        CycleQueue circularQueue = new CycleQueue(6); // 设置长度为 3
        System.out.println(circularQueue.enQueue(6));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.enQueue(5));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());
    }
}

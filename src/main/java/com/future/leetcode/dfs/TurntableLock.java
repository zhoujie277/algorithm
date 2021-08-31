package com.future.leetcode.dfs;

import java.util.*;

/**
 * 转盘锁
 * <p>
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * <p>
 * 提示：
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target 不在 deadends 之中
 * target 和 deadends[i] 仅由若干位数字组成
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/kj48j/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class TurntableLock {

    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;
        Set<String> deadSet = new HashSet<>();
        for (int i = 0; i < deadends.length; i++) {
            deadSet.add(deadends[i]);
        }
        if (deadSet.contains("0000")) return -1;
        Set<String> seen = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.add("0000");
        seen.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            // 每一层一个步数，此题不可用DFS，因为求的是到达某点的最少步数，不是最短距离。
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String nextStatus : get(status)) {
                    if (!seen.contains(nextStatus) && !deadSet.contains(nextStatus)) {
                        if (nextStatus.equals(target)) return step;
                        queue.add(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> get(String str) {
        List<String> ret = new ArrayList<>(8);
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            array[i] = prev(c);
            ret.add(new String(array));
            array[i] = succ(c);
            ret.add(new String(array));
            array[i] = c;
        }
        return ret;
    }

    private char prev(char c) {
        return (c == '0') ? '9' : (char) (c - 1);
    }

    private char succ(char c) {
        return (c == '9') ? '0' : (char) (c + 1);
    }

    public static void main(String[] args) {
        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(new TurntableLock().openLock(deadends, target));
    }
}

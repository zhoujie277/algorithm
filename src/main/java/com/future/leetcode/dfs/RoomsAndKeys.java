package com.future.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 钥匙和房间
 * <p>
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，
 * 其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * 提示：
 * 1 <= rooms.length <=1000
 * 0 <= rooms[i].length <= 1000
 * 所有房间中的钥匙数量总计不超过3000。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/gle1r/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class RoomsAndKeys {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] seen = new boolean[rooms.size()];
        dfs(rooms, 0, seen);
        for (boolean b : seen) {
            if (!b) return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int index, boolean[] seen) {
        if (index == rooms.size() || seen[index]) return;
        seen[index] = true;
        List<Integer> list = rooms.get(index);
        for (int i = 0; i < list.size(); i++) {
            dfs(rooms, list.get(i), seen);
        }
    }

    public static void main(String[] args) {
//[[1,3],[3,0,1],[2],[0]]
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> zero = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        List<Integer> three = new ArrayList<>();
        zero.add(1);
        zero.add(3);
        one.add(3);
        one.add(0);
        one.add(1);
        two.add(2);
        three.add(0);
        rooms.add(zero);
        rooms.add(one);
        rooms.add(two);
        rooms.add(three);
        System.out.println(new RoomsAndKeys().canVisitAllRooms(rooms));
    }
}

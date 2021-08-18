package com.future.algoriithm.practice;

import java.util.*;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间[[s1, e1], [s2, e2], [s3, e3]](si<ei)
 * 为避免会议冲突，同时考虑充分利用会议室资源，请你计算需要多少间会议室，才能满足这些会议安排。
 * 输入：
 * [[0,30], [5, 10], [15, 20]]
 * 输出：2
 *
 * @author jayzhou
 */
public class ConferenceRoom2 {

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(m -> m[0]));
        List<Integer> list = new ArrayList<>(intervals.length);
        for (int[] interval : intervals) {
            list.add(interval[1]);
        }
        // 堆中存放正在进行的会议，会议按照时间逐个结束。
        // 排序数组中存放即将开始的会议，按照开始时间升序，轮训到哪个会议，哪个会议就开始进行会议。
        PriorityQueue<Integer> queue = new PriorityQueue<>(list);
        int minCount = 0;
        for (int[] interval : intervals) {
            Integer endTime = queue.peek();
            if (endTime == null) break;
            int beginTime = interval[0];
            if (beginTime < endTime) {
                minCount++;
            } else {
                queue.poll();
            }
        }
        return minCount;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0, 30}, {15, 20}, {5, 10}};
        System.out.println(new ConferenceRoom2().minMeetingRooms(intervals));
    }
}

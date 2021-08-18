package com.future.algoriithm.practice;

import java.util.Arrays;

/**
 * 会议室
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间[[s1, e1], [s2, e2], [s3, e3]](si<ei)
 * 请你判断一个人是否能够参加这里面的全部会议
 * <p>
 * 输入：
 * [[0,30], [5, 10], [15, 20]]
 * 输出: false
 *
 * @author jayzhou
 */
public class ConferenceRoom {

    private void swap(int[][] nums, int l, int r) {
        if (l == r) return;
        int[] tmp = nums[r];
        nums[r] = nums[l];
        nums[l] = tmp;
    }

    private void quicksort(int[][] nums, int l, int r) {
        if (l >= r) return;
        int end = r;
        int pivot = l++;
        while (l <= r) {
            while (l <= r && nums[l][0] <= nums[pivot][0]) {
                l++;
            }
            while (l <= r && nums[r][0] > nums[pivot][0]) {
                r--;
            }
            if (l > r) break;
            swap(nums, l, r);
        }
        swap(nums, pivot, r);
        quicksort(nums, pivot, r - 1);
        quicksort(nums, r + 1, end);
    }

    /**
     * 时间复杂度：O(nlogN) + O(n)
     */
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        quicksort(intervals, 0, intervals.length - 1);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0, 30}, {15, 20}, {5, 10}};
        System.out.println(new ConferenceRoom().canAttendMeetings(intervals));
        System.out.println(Arrays.deepToString(intervals));
    }
}

package com.future.leetcode.search;

/**
 * 给你一个山脉数组mountainArr，请你返回能够使得mountainArr.get(index)等于target最小的下标 index值。
 * 如果不存在这样的下标 index，就请返回-1。
 *
 * @author jayzhou
 */
public class FindTargetInMountain {
    private interface MountainArray {
        int get(int index);

        int length();
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int left = 0, right = mountainArr.length() - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int peak = left;
        int find = binarySearch(mountainArr, target, 0, peak, false);
        if (find != -1) return find;
        return binarySearch(mountainArr, target, peak + 1, mountainArr.length() - 1, true);
    }

    private int binarySearch(MountainArray array, int target, int l, int r, boolean inverse) {
        int left = l, right = r;
        do {
            int mid = (left + right) >> 1;
            int midValue = array.get(mid);
            if (target == midValue) return mid;
            if (target > midValue) {
                if (!inverse) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (!inverse) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        } while (left <= right);
        return -1;
    }
}

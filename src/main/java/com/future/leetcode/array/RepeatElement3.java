package com.future.leetcode.array;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * 存在重复元素3
 * <p>
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，
 * 同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * 0 <= nums.length <= 2 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^4
 * 0 <= t <= 2^31 - 1
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/introduction-to-data-structure-binary-search-tree/xpffam/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class RepeatElement3 {

    /**
     * 桶思想解法
     * 依然是滑动窗口，不符合窗口需剔除。
     * 1、由于 abs(nums[i] - nums[j]) ≤ t; 其实就是对于任意x的取值范围需要满足：[x - t, x + t]的区间内。
     * 2、故可采用桶思想，每个桶的范围是 t + 1的数据范围。（其实这个桶自始至终最多只会装一个数据，原因请看第三点说明）
     * 3、逐个遍历元素，若元素所代表的桶id已经存在，则可以直接返回true。（保证每个桶最多只会装一个数据）
     * 4、能到这一步，说明元素所代表的桶id是新的，需要比较相邻的桶是不是有元素，切满足 abs(nums[i] - nums[j]) ≤ t;
     * 5、如果都不满足，说明nums中数据都是在彼此独立的桶中，也说明没有任何两个元素的差值满足条件。返回false。
     * 6、实现方面，可以采用HashMap。
     * 时间复杂度：O(n)
     * 空间复杂度：O(min(n,k)).
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k == 0) return false;
        HashMap<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; i++) {
            long id = getId(nums[i], w);
            if (map.containsKey(id)) return true;
            if (map.get(id + 1) != null && Math.abs(map.get(id + 1) - nums[i]) < w) return true;
            if (map.get(id - 1) != null && Math.abs(map.get(id - 1) - nums[i]) < w) return true;
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getId(nums[i - k], w));
            }
        }
        return false;
    }

    private long getId(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }

    /**
     * 滑动窗口.
     * 首先使TreeSet中的数值都符合窗口内的数据，超出窗口则删除。
     * 另外，在一个数进入TreeSet窗口之前，先判断这个数和 TreeSet 中的数相减符合答案。
     * 需要使: abs(nums[i] - nums[j]) ≤ t
     * 则需要满足：  nums[j] ≥ nums[i] - t;
     * 时间复杂度：O(nlog(min(n,k))
     * 空间复杂度：O(1)
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k == 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        set.add((long) nums[0]);
        for (int i = 1; i < nums.length; i++) {
            Long celling = set.ceiling((long) nums[i] - (long) t);
            if (celling != null && celling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 朴素法
     * 时间复杂度：O(KN)
     * 空间复杂度：O(1)
     * 结论：超出时间限制。
     */
    public boolean brute(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k == 0) return false;
        for (int i = 1; i < nums.length; i++) {
            int j = Math.max(0, i - k);
            while (i - j > 0) {
                long diff = (long) nums[i] - nums[j];
                if (Math.abs(diff) <= t) {
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RepeatElement3 repeat = new RepeatElement3();
        //输入：nums = [1,2,3,1], k = 3, t = 0
        //输出：true
        int[] a1 = new int[]{1, 2, 3, 1};
        System.out.println(repeat.containsNearbyAlmostDuplicate(a1, 3, 0));
        //输入：nums = [1,5,9,1,5,9], k = 2, t = 3
        //输出：false
        int[] a2 = new int[]{1, 5, 9, 1, 5, 9};
        System.out.println(repeat.containsNearbyAlmostDuplicate(a2, 2, 3));
        //输入：nums = [1,0,1,1], k = 1, t = 2
        //输出：true
        int[] a3 = new int[]{1, 0, 1, 1};
        System.out.println(repeat.containsNearbyAlmostDuplicate(a3, 1, 2));
        int[] a4 = new int[]{1, 2, 2, 3, 4, 5};
        System.out.println(repeat.containsNearbyAlmostDuplicate(a4, 3, 0));
        int[] a5 = new int[]{-2147483648, 2147483647};
        System.out.println(repeat.containsNearbyAlmostDuplicate(a5, 1, 1));
    }
}

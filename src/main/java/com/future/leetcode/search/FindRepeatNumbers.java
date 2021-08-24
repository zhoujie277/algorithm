package com.future.leetcode.search;

import java.util.HashSet;

/**
 * 寻找重复数
 * <p>
 * 给定一个包含 n + 1 个整数的数组nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有一个重复的整数 ，找出这个重复的数 。
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：nums = [1,1]
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：nums = [1,1,2]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中只有一个整数出现两次或多次 ，其余整数均只出现一次
 * <p>
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xe6xnr/
 *
 * @author jayzhou
 */
public class FindRepeatNumbers {

    /**
     * 快慢指针解法
     * <p>
     * 前提思路：
     * 因为该示例中，长度为n + 1的数组里只能存放 1～n 的数据。
     * 所以数组的值也可以作为索引使用。
     * <p>
     * 以数组下标位置0开始，所存放的值作为下一个指针访问的对象。则会形成一个链表。比如：
     * 数组元素为: nums：[3,4,1,2,5] 。数组下标毋庸置疑：0，1，2，3，4；
     * 则会形成这样一个链表：从0开始依次访问到的元素分别为：3 -> 2 -> 1 -> 4 -> 5 -> null(越界)
     * <p>
     * 值得一提的是，用链表表示虽然容易理解，但并不能概括全部情况，比如说 [5,1,2,3,4], 其实构不成链表。
     * 而用图表示是最合理的，可以描述全部情况。以数组下标、数组元素为顶点。
     * 而数组本身的映射关系表示从数组下标到数组元素两个顶点的边。
     * 并且，起始下标为0，而数组元素全部为正整数，所以第一步一定迈的出去。
     * <p>
     * 按照本题所示，数组中有重复元素，那便说明，以图的方式或者用链表的方式表现该关系，则会形成一个环。
     * 发现了环，便打开了本题用快慢指针解决问题的思路。
     * <p>
     * 关键思路：
     * 假设环长为L，环的入口 e 点，slow指针和fast指针相遇 p 点，
     * 且从起点到环的入口距离为 a，从 e 点到 p 点距离为 b。从相遇位置 p 点继续走 c 步才能走到环的入口 e 点。
     * 则有 b + c = L；其中 L、a、b、c、都是正整数；
     * 由上述题意可知，slow和fast指针在 p 点相遇为止。slow指针移动的距离为 a + b。fast指针移动的距离为 2(a + b)；
     * fast指针移动的距离从另一个角度也可以表示成 a + b + kL；表示fast指针进入环之后又移动了k圈。
     * 故有 2(a + b) = a + b + kL; 解得：a = kL - b；整理可得：a = (k - 1)L + (L - b) = (k - 1)L + c;
     * 也就是：
     * *                  a = (k - 1)L + c;
     * 由这个公式可知，a是起点到环的入口 e 点的距离，c 是p点到达入口点e点的距离。
     * 也就是说。在用某一个指针从起点用 a 的距离走到e点， 从相遇p点移动走 c 的距离到e点，便能在此再次相遇。
     * 而此时，相遇点便是答案。
     */
    public int findDuplicates(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        System.out.println("fast=" + fast);
        int finder = 0;
        do {
            finder = nums[finder];
            slow = nums[slow];
        } while (finder != slow);
        return finder;
    }

    /**
     * bitmap方法
     * 时间复杂度：O(n)
     * 空间复杂度：常数级O(n/32), 10w级数据需要16KB的内存。
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] bits = new int[4096];
        for (int num : nums) {
            int bucket = num / Integer.SIZE;
            int mod = num % Integer.SIZE;
            int place = 1 << mod;
            if ((bits[bucket] & place) == place) {
                return num;
            } else {
                bits[bucket] |= place;
            }
        }
        return 0;
    }

    /**
     * hashmap方法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int findDuplicateHash(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return 0;
    }

    /**
     * 二分+计数方法
     * 这种解法本身有很大的局限性，
     * 必须是此题才有可用性。n + 1的数组里只存放 1～n 的数。且重复的数字只有一个
     * 如果 存放的数字大小超过了n，则不适用。如果重复的数字大于1一个，也不适用。
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    public int findDuplicateBinary(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        int ans = -1;
        do {
            int mid = (left + right) >>> 1;
            System.out.println("mid=" + mid);
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                // 出现重复的值在右边
                left = mid + 1;
            } else {
                right = mid - 1;
                ans = mid;
            }
        } while (left <= right);
        System.out.println("left=" + left);
        return ans;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 1, 3, 4, 2};
        int[] nums = new int[]{13, 46, 8, 11, 20, 17, 40, 13, 13, 13, 14, 1, 13, 36, 48, 41, 13, 13, 13, 13, 45, 13, 28, 42, 13, 10, 15, 22, 13, 13, 13, 13, 23, 9, 6, 13, 47, 49, 16, 13, 13, 39, 35, 13, 32, 29, 13, 25, 30, 13};
//        int[] nums = new int[]{13, 46, 8, 11, 20, 17, 6, 13, 13, 13, 14, 1, 13, 14};
        FindRepeatNumbers numbers = new FindRepeatNumbers();
        System.out.println(numbers.findDuplicate(nums));
        System.out.println(numbers.findDuplicateBinary(nums));
        System.out.println(numbers.findDuplicates(nums));
    }
}

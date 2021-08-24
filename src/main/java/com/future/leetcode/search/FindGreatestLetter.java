package com.future.leetcode.search;

/**
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母target，
 * 请你寻找在这一有序列表里比目标字母大的最小字母。
 * <p>
 * 在比较时，字母是依序循环出现的。举个例子：
 * <p>
 * 如果目标字母 target = 'z' 并且字符列表为letters = ['a', 'b']，则答案返回'a'
 * letters长度范围在[2, 10000]区间内。
 * letters 仅由小写字母组成，最少包含两个不同的字母。
 * 目标字母target 是一个小写字母。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xeiuui/
 *
 * @author jayzhou
 */
public class FindGreatestLetter {
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) return 0;
        int left = 0, right = letters.length - 1;
        do {
            int mid = (left + right) >>> 1;
            if (target >= letters[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left <= right);
        return left == letters.length ? letters[0] : letters[left];
    }

    public static void main(String[] args) {
        char[] chars = new char[]{'a', 'b', 'c'};
        FindGreatestLetter letter = new FindGreatestLetter();
        System.out.println(letter.nextGreatestLetter(chars, 'c'));
    }
}

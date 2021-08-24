package com.future.leetcode.string;

/**
 * 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 * 提示：
 * 1 <= s.length <= 2 * 10^5
 * 字符串 s 由 ASCII 字符组成
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x9tqjc/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        do {
            while (left <= right && (!(chars[left] <= 'z' && chars[left] >= 'a') && !(chars[left] <= 'Z' && chars[left] >= 'A')
                    && !(chars[left] >= '0' && chars[left] <= '9'))) {
                left++;
            }
            while (left <= right && (!(chars[right] <= 'z' && chars[right] >= 'a') && !(chars[right] <= 'Z' && chars[right] >= 'A')
                    && !(chars[right] >= '0' && chars[right] <= '9'))) {
                right--;
            }
            if (left > right) break;
            char l = tolowercase(chars[left++]);
            char r = tolowercase(chars[right--]);
            if (l != r) return false;
        } while (true);
        return true;
    }

    private char tolowercase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + 32);
        }
        return c;
    }

    public static void main(String[] args) {
        ValidPalindrome palindrome = new ValidPalindrome();
//        System.out.println(palindrome.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(palindrome.isPalindrome("race a car"));
    }
}

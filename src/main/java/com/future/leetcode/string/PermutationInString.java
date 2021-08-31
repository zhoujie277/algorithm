package com.future.leetcode.string;

/**
 * 字符串的排列
 * <p>
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 * <p>
 * 提示：
 * 1 <= s1.length, s2.length <= 10^4
 * s1 和 s2 仅包含小写字母
 * <p>
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int n = chars1.length;
        int m = chars2.length;
        if (n > m) return false;
        int[] bucket = new int[26];
        for (int i = 0; i < n; i++) {
            bucket[chars1[i] - 'a'] -= 1;
        }
        int left = 0;
        for (int right = 0; right < m; right++) {
            int x = chars2[right] - 'a';
            bucket[x] += 1;
            while (bucket[x] > 0) {
                // 还原
                bucket[chars2[left] - 'a'] -= 1;
                left++;
            }
            if (right - left + 1 == n) return true;
        }
        return false;
    }

    /**
     * 空间复杂度：O(26)
     * 时间复杂度：O(n)
     */
    public boolean checkInclusionOpt(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int n = chars1.length;
        int m = chars2.length;
        if (n > m) return false;
        int[] bucket = new int[26];
        for (int i = 0; i < n; i++) {
            bucket[chars1[i] - 'a'] -= 1;
            bucket[chars2[i] - 'a'] += 1;
        }
        int diffChar = 0;
        for (int count : bucket) {
            if (count != 0) diffChar++;
        }
        if (diffChar == 0) return true;

        for (int i = n; i < m; i++) {
            int x = chars2[i] - 'a', y = chars2[i - n] - 'a';
            if (x == y) continue;
            if (bucket[x] == 0) diffChar++;
            bucket[x] += 1;
            if (bucket[x] == 0) diffChar--;
            if (bucket[y] == 0) diffChar++;
            bucket[y] -= 1;
            if (bucket[y] == 0) diffChar--;
            if (diffChar == 0) return true;
        }
        return false;
    }

    /**
     * 朴素法
     * 时间复杂度：O(MN)
     * 空间复杂度：O(52)
     */
    public boolean checkInclusion2(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int n = chars1.length;
        int m = chars2.length;
        if (n > m) return false;
        int[] bucket1 = new int[26];
        int[] bucket2 = new int[26];
        for (int i = 0; i < n; i++) {
            bucket1[chars1[i] - 'a'] += 1;
            bucket2[chars2[i] - 'a'] += 1;
        }
        if (equalsArray(bucket1, bucket2)) return true;
        for (int i = n; i < m; i++) {
            bucket2[chars2[i] - 'a'] += 1;
            bucket2[chars2[i - n] - 'a'] -= 1;
            if (equalsArray(bucket1, bucket2)) return true;
        }
        return false;
    }

    private boolean equalsArray(int[] bucket1, int[] bucket2) {
        for (int i = 0; i < bucket1.length; i++) {
            if (bucket1[i] != bucket2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationInString inString = new PermutationInString();
        System.out.println(inString.checkInclusion("ab", "eidbaooo"));
        System.out.println(inString.checkInclusion("ab", "eidboaoo"));
        System.out.println("---------");
        System.out.println(inString.checkInclusion2("ab", "eidbaooo"));
        System.out.println(inString.checkInclusion2("ab", "eidboaoo"));
        System.out.println("---------");
        System.out.println(inString.checkInclusionOpt("ab", "eidbaooo"));
        System.out.println(inString.checkInclusionOpt("ab", "eidboaoo"));
        System.out.println(inString.checkInclusionOpt("ab", "eba"));
    }
}

package com.future.leetcode.string;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/ceda1/
 *
 * @author jayzhou
 */
public class LongestCommonPrefix {


    /**
     * 自底向上的分治法。
     * 空间复杂度：O(1)。但是会改变原数组，如果不允许改变原数组，则空间复杂度为O(n)/2
     * 时间复杂度：T(n) = 0(n/2) + O(n/4) + .... O(n/2^k)  -> 每一层的合并次数
     * *          令：n = 2^k. 则 T(n) = O(2^k-1) + ...+ 1 = 2^k - 1 = n - 1;
     * *          这是合并次数的复杂度。m是字符串平均长度，则 T(n) = M * N；
     */
    public String longestCommonPrefixBottom(String[] strings) {
        if (strings == null || strings.length == 0) return "";
        if (strings.length == 1) return strings[0];
        int len = strings.length;
        for (int i = len; i > 1; i >>= 1) {
            int strIndex = 0;
            boolean even = (i & 1) == 0;
            int evenLen = even ? i : i - 1;
            for (int j = 0; j < evenLen; j += 2) {
                strings[strIndex++] = commonPrefix(strings[j], strings[j + 1]);
            }
            if (!even) {
                strings[strIndex] = strings[i - 1];
                i += 1;
            }
        }
        return strings[0];
    }

    public String commonPrefix(String s1, String s2) {
        int min = Math.min(s1.length(), s2.length());
        for (int i = 0; i < min; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, min);
    }

    public String longestCommonPrefix(String[] strings, int left, int right) {
        if (left == right) {
            return strings[left];
        }
        int mid = (left + right) >>> 1;
        String s1 = longestCommonPrefix(strings, left, mid);
        String s2 = longestCommonPrefix(strings, mid + 1, right);
        return commonPrefix(s1, s2);
    }

    /**
     * 自顶向下分治法
     * 时间复杂度： O(mn)其中 m m 是字符串数组中的字符串的平均长度。n是字符串数量。
     * *           时间复杂度的递推式是：T(n)=2*T(n/2)+O(m)。通过计算可得：T(n) = O(mn)
     * 空间复杂度：O(mLogN).其中m是字符串数组中d的字符串平均长度，n是字符串的数量。空间复杂度主要取决于递归调用的深度。
     * *           深度最大为logN，每层需要 m 的空间存储返回的结果。
     */
    public String longestCommonPrefix(String[] strings) {
        if (strings == null || strings.length == 0) return "";
        if (strings.length == 1) return strings[0];
        return longestCommonPrefix(strings, 0, strings.length - 1);
    }

    /**
     * 横向扫描法
     * 时间复杂度：O(mn). m是字符串数组中的字符串的平均长度，n是字符串的数量。最坏情况下，字符串每个字符都会被比较一次。
     * *                如果比较短的字符串在前面，该算法相对比较快。如果比较短的靠后，则时间复杂度较高。
     * 空间复杂度：O(1).
     */
    public String longestCommonPrefix3(String[] strings) {
        if (strings == null || strings.length == 0) return "";
        if (strings.length == 1) return strings[0];
        String prefix = strings[0];
        for (int i = 1; i < strings.length; i++) {
            prefix = commonPrefix(prefix, strings[i]);
            if (prefix.length() == 0) break;
        }
        return prefix;
    }

    /**
     * 纵向扫描法
     * 精简prefix1的代码
     * 时间复杂度：O(mn)。其中m是字符串数组中长度最小的长度。n是字符串数组的数量。
     * 空间复杂度：O(1)
     */
    public String longestCommonPrefix2(String[] strings) {
        if (strings == null || strings.length == 0) return "";
        int len = strings[0].length();
        for (int i = 0; i < len; i++) {
            char c = strings[0].charAt(i);
            for (int j = 1; j < strings.length; j++) {
                if (i == strings[j].length() || strings[j].charAt(i) != c) {
                    return strings[0].substring(0, i);
                }
            }
        }
        return strings[0];
    }

    public String longestCommonPrefix1(String[] strings) {
        if (strings == null || strings.length == 0) return "";
        int index = 0;
        StringBuilder builder = new StringBuilder();
        while (true) {
            String first = strings[0];
            if (index >= first.length()) break;
            char c = first.charAt(index);
            boolean same = true;
            for (int i = 1; i < strings.length; i++) {
                String string = strings[i];
                if (index >= string.length() || c != string.charAt(index)) {
                    same = false;
                    break;
                }
            }
            if (same) {
                builder.append(c);
            } else {
                break;
            }
            index++;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"flower", "flow", "flight"};
        LongestCommonPrefix prefix = new LongestCommonPrefix();
        String s = prefix.longestCommonPrefix(strings);
        System.out.println(s);
        System.out.println(prefix.longestCommonPrefix3(strings));
        System.out.println(prefix.longestCommonPrefixBottom(strings));
    }
}

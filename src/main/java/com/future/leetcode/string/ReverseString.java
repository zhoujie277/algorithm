package com.future.leetcode.string;

/**
 * 字符串反转
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cacxi/
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class ReverseString {

    public void reverseString(char[] s) {
        if (s == null || s.length == 0) return;
        int i = 0, j = s.length - 1;
        do {
            char tmp = s[i];
            s[i++] = s[j];
            s[j--] = tmp;
        } while (i < j);
    }

}

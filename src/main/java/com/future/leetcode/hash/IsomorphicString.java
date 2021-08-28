package com.future.leetcode.hash;

import java.util.HashMap;

/**
 * 同构字符串
 * <p>
 * 给定两个字符串s和t，判断它们是否是同构的。
 * 如果s中的字符可以按某种映射关系替换得到t，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
 * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * <p>
 * 提示：
 * 可以假设 s 和 t 长度相同。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/hash-table/xhjvbj/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class IsomorphicString {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> hashMap1 = new HashMap<>();
        HashMap<Character, Character> hashMap2 = new HashMap<>();
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            char c = chars1[i];
            char p = chars2[i];
            Character put = hashMap1.put(c, p);
            if (put != null && put != p) return false;
            put = hashMap2.put(p, c);
            if (put != null && put != c) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //输入：s = "egg", t = "add"
        //输出：true
        IsomorphicString isomorphicString = new IsomorphicString();
        System.out.println(isomorphicString.isIsomorphic("egg", "add"));
        System.out.println(isomorphicString.isIsomorphic("eggg", "addr"));
        System.out.println(isomorphicString.isIsomorphic("badc", "baba"));
        System.out.println(isomorphicString.isIsomorphic("paper", "title"));
    }
}

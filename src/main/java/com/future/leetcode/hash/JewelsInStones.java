package com.future.leetcode.hash;

import java.util.HashSet;

/**
 * 宝石和石头
 * <p>
 * 给定字符串J代表石头中宝石的类型，和字符串S代表你拥有的石头。S中每个字符代表了一种你拥有的石头的类型，
 * 你想知道你拥有的石头中有多少是宝石。
 * J中的字母不重复，J和S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * <p>
 * 示例 1:
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 * 示例 2:
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * 注意:
 * S和J最多含有50个字母。
 * J中的字符不重复。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/hash-table/xx2a0c/
 */
public class JewelsInStones {

    public int numJewelsInStones(String jewels, String stones) {
        char[] chars = jewels.toCharArray();
        char[] stone = stones.toCharArray();
        HashSet<Character> characters = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            characters.add(chars[i]);
        }
        int count = 0;
        for (int i = 0; i < stone.length; i++) {
            if (characters.contains(stone[i])) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }

}

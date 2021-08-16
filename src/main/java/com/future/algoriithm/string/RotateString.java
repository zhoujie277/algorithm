package com.future.algoriithm.string;

/**
 * 字符串轮转。
 * 给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成
 * 比如，waterbottle是erbottlewat旋转后的字符串）。
 *
 * @author jayzhou
 */
public class RotateString {

    public boolean isFlippedString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        String s = s1 + s1;
        return s.contains(s2);
    }

    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        boolean flippedString = new RotateString().isFlippedString(s1, s2);
        System.out.println(flippedString);
    }
}

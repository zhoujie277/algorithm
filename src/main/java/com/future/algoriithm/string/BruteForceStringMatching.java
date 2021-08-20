package com.future.algoriithm.string;

/**
 * 字符串暴力匹配
 *
 * @author jayzhou
 */
public class BruteForceStringMatching {

    public int indexOf(String text, String pattern) {
        if (text == null || pattern == null || text.length() < pattern.length()) return -1;
        if (pattern.length() == 0) return 0;
        char[] txt = text.toCharArray();
        char[] p = pattern.toCharArray();
        int tMax = txt.length - p.length;
        for (int ti = 0, pi; ti <= tMax; ti++) {
            for (pi = 0; pi < p.length; pi++) {
                if (p[pi] != txt[ti + pi]) break;
            }
            if (pi == p.length) return ti;
        }
        return -1;
    }

    public boolean contains(String text, String pattern) {
        if (text == null || pattern == null || text.length() < pattern.length()) return false;
        char[] txt = text.toCharArray();
        char[] p = pattern.toCharArray();
        int ti = 0, pi = 0;
        while (pi < p.length && ti - pi < txt.length) {
            if (txt[ti] == p[pi]) {
                ti++;
                pi++;
            } else {
                ti = ti - pi + 1;
                pi = 0;
            }
        }
        return pi == p.length;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static void main(String[] args) {

    }
}

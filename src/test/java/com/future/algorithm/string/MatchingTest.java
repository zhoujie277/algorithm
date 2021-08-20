package com.future.algorithm.string;

import com.future.algoriithm.string.KMP;
import org.junit.Test;

public class MatchingTest {


    @Test
    public void testKmp() {
//        BruteForceStringMatching matching = new BruteForceStringMatching();
        KMP matching = new KMP();
        System.out.println(matching.indexOf("abcfabczhoujie", "zhou"));
        System.out.println(matching.indexOf("abcfabczhoujie", "hello"));
        System.out.println(matching.indexOf("abcfabczhoujie", "a"));
        System.out.println(matching.indexOf("abcfabczhoujie", "e"));
        System.out.println(matching.indexOf("abcfabczhoujie", "zhu"));
        System.out.println("------");
        System.out.println(matching.indexOf2("abcfabczhoujie", "zhou"));
        System.out.println(matching.indexOf2("abcfabczhoujie", "hello"));
        System.out.println(matching.indexOf2("abcfabczhoujie", "a"));
        System.out.println(matching.indexOf2("abcfabczhoujie", "e"));
        System.out.println(matching.indexOf2("abcfabczhoujie", "zhu"));
    }
}

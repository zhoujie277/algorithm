package com.future.leetcode.hash;

import java.util.HashMap;

/**
 * 两数之和3
 * 设计并实现一个 TwoSum 类。他需要支持以下操作:add 和 find。
 * add -把这个数添加到内部的数据结构。
 * find -是否存在任意一对数字之和等于这个值
 *
 * @author jayzhou
 */
public class TwoSum3 {

    private HashMap<Integer, Integer> hashMap = new HashMap<>();

    /**
     * @param number: An integer
     */
    public void add(int number) {
        // write your code here
        hashMap.put(number, hashMap.getOrDefault(number, 0) + 1);
    }

    /**
     * @param value: An integer
     */
    public boolean find(int value) {
        // write your code here
        for (Integer integer : hashMap.keySet()) {
            int key = value - integer;
            Integer count = hashMap.get(key);
            if (count != null) {
                if (key == integer) return count > 1;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //add(1);add(3);add(5);
        //find(4)//返回true
        //find(7)//返回false
        TwoSum3 t = new TwoSum3();
        t.add(1);
        t.add(3);
        t.add(5);
        System.out.println(t.find(4));
        System.out.println(t.find(7));
        System.out.println(t.find(6));

    }
}

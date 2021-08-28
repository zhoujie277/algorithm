package com.future.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 喜欢的餐厅
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。
 * 你可以假设总是存在一个答案。
 * <p>
 * 两个列表的长度范围都在[1, 1000]内。
 * 两个列表中的字符串的长度将在[1，30]的范围内。
 * 下标从0开始，到列表的长度减1。
 * 两个列表都没有重复的元素。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/hash-table/xhfact/
 */
@SuppressWarnings("all")
class FavoriteRestaurant {

    /**
     * 要求输出两个列表最下的索引总和
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list2 == null || list1.length == 0 || list2.length == 0) return null;
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < list1.length; i++) {
            hashMap.put(list1[i], i);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            Integer integer = hashMap.get(list2[i]);
            if (integer != null) {
                int ts = integer + i;
                if (ts < min) {
                    min = ts;
                    strings.clear();
                    strings.add(list2[i]);
                } else if (ts == min) {
                    strings.add(list2[i]);
                }
            }
        }
        return strings.toArray(new String[0]);
    }

    public static void main(String[] args) {
        //输入:
        //["Shogun", "Tapioca Express", "Burger King", "KFC"]
        //["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
        //输出: ["Shogun"]
        //解释: 他们唯一共同喜爱的餐厅是“Shogun”
        //
        String[] s1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] s2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};

        FavoriteRestaurant restaurant = new FavoriteRestaurant();
        String[] restaurant1 = restaurant.findRestaurant(s1, s2);
        for (String s : restaurant1) {
            System.out.println(s);
        }
    }
}

package com.future.algoriithm.practice;

import com.future.utils.PrintUtils;

import java.util.LinkedList;

/**
 * 每日温度
 * 请根据每日气温列表temperatures，计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用0来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出:[1,1,4,2,1,1,0,0]
 * 示例 2:
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出:[1,1,1,0]
 * <p>
 * 提示：
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 *
 * <p>
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 *
 * @author jayzhou
 */
public class DailyTemperatures {

    /**
     * 倒推法
     * 时间复杂度：O(n)
     * 空间复杂度: O(1)
     */
    public int[] beta(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return temperatures;
        int[] result = new int[temperatures.length];
        int len = temperatures.length;
        for (int i = len - 2, j; i >= 0; i--) {
            j = i + 1;
            while (true) {
                if (temperatures[i] < temperatures[j]) {
                    result[i] = j - i;
                    break;
                } else if (result[j] == 0) {
                    result[i] = 0;
                    break;
                }
                //精简代码的操作 temperatures[i] == temperatures[j]的时候
                j += result[j];
            }
        }
        return result;
    }


    /**
     * 倒推法
     * 时间复杂度：O(n)
     * 空间复杂度: O(1)
     */
    public int[] beta1(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return temperatures;
        int[] result = new int[temperatures.length];
        int len = temperatures.length;
        for (int i = len - 2, j; i >= 0; i--) {
            j = i + 1;
            while (true) {
                if (temperatures[i] < temperatures[j]) {
                    result[i] = j - i;
                    break;
                } else if (result[j] == 0) {
                    result[i] = 0;
                    break;
                } else if (temperatures[i] == temperatures[j]) {
                    result[i] = result[j] + j - i;
                    break;
                }
                j += result[j];
            }
        }
        return result;
    }

    /**
     * 单调栈
     * 时间复杂度：当输入序列是单调递减的序列时，最坏时间复杂度：O(n^2)
     * 空间复杂度：当输入序列是单调递增的序列时，最坏空间复杂度：O(n)
     */
    public int[] alpha(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return temperatures;
        int[] result = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();
        Integer peek;
        for (int i = 0; i < temperatures.length; i++) {
            while ((peek = stack.peek()) != null && temperatures[i] > temperatures[peek]) {
                stack.pop();
                result[peek] = i - peek;
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 朴素法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int[] dev(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return temperatures;
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // [34,80,80,34,34,80,80,80,80,34]
        // [1,0,0,2,1,0,0,0,0,0]
        int[] array = new int[]{34, 80, 80, 34, 34, 80, 80, 80, 80, 34};
        DailyTemperatures instance = new DailyTemperatures();
        PrintUtils.println(instance.dev(array));
        PrintUtils.println(instance.alpha(array));
        PrintUtils.println(instance.beta1(array));
        PrintUtils.println(instance.beta(array));
    }
}

package com.future.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 每日温度
 * <p>
 * 请根据每日 气温 列表 temperatures，请计算在每一天需要等几天才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出:[1,1,4,2,1,1,0,0]
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/genw3/
 *
 * @author jayzhou
 */
public class DailyTemperature {

    public int[] dailyTemperatures2(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return temperatures;
        int[] ret = new int[temperatures.length];
        for (int i = temperatures.length - 2, j; i >= 0; i--) {
            j = i + 1;
            while (true) {
                if (temperatures[i] < temperatures[j]) {
                    ret[i] = j - i;
                    break;
                } else if (ret[j] == 0) {
                    ret[i] = 0;
                    break;
                } else if (temperatures[i] == temperatures[j]) {
                    ret[i] = ret[j] + j - i;
                    break;
                } else {
                    j += ret[j];
                }
            }
        }
        return ret;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return temperatures;
        int[] ret = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        Integer peekIndex;
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[(peekIndex = stack.peekLast())] < temperatures[i]) {
                stack.pollLast();
                ret[peekIndex] = i - peekIndex;
            }
            stack.addLast(i);
        }
        return ret;
    }

    public static void main(String[] args) {
//        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] temps = {34, 80, 80, 34, 34, 80, 80, 80, 80, 34};
        DailyTemperature temperature = new DailyTemperature();
        System.out.println(Arrays.toString(temperature.dailyTemperatures(temps)));
        System.out.println(Arrays.toString(temperature.dailyTemperatures2(temps)));
    }
}

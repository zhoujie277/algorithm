package com.future.leetcode.dfs;

import java.util.*;

/**
 * 组合总和2
 * <p>
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 * <p>
 * 示例1:
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例2:
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * 提示:
 * 1 <=candidates.length <= 100
 * 1 <=candidates[i] <= 50
 * 1 <= target <= 30
 * <p>
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class CombinationSum2 {

    private void dfs(int[] candidates, int depth, int prevSum, int target, Deque<Integer> path, List<List<Integer>> result) {
        if (prevSum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        int sum;
        for (int i = depth; i < candidates.length && (sum = prevSum + candidates[i]) <= target; i++) {
            if (i != depth && candidates[i] == candidates[i - 1]) continue;
            path.addLast(candidates[i]);
            //从上次搜索的位置 + 1 开始dfs
            dfs(candidates, i + 1, sum, target, path, result);
            path.removeLast();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, 0, target, path, result);
        return result;
    }

    public static void main(String[] args) {
        //30
        int[] nums = new int[]{10, 1, 2, 7, 6, 1, 5};
        int[] nums2 = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        CombinationSum2 sum2 = new CombinationSum2();
        System.out.println(sum2.combinationSum2(nums, 8));
        System.out.println(sum2.combinationSum2(nums2, 30));
    }
}

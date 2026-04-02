package one.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class combinationSum39 {
    public List<List<Integer>> combinationSum(int[] candidates,int target){
        List<List<Integer>> res=new ArrayList<>();

        //1.排序是剪枝的前提 能大幅提升效率
        Arrays.sort(candidates);

        //2.开始回溯
        // 参数：候选数组, 目标剩下多少, 当前路径的开始下标, 当前路径列表, 结果集
        backtrack(candidates, target, 0, new ArrayList<>(), res);

        return res;

    }

    /**
     * 回溯函数
     * @param candidates 候选数组
     * @param target     当前还需要凑多少钱 (target - sum)
     * @param begin      下一轮搜索的起点 (防止重复)
     * @param path       当前已经选了哪些数 (路径)
     * @param res        结果集
     */
    private void backtrack(int[] candidates, int target, int begin, List<Integer> path, List<List<Integer>> res){
        // --- 终止条件 ---

        // 1. 如果target刚好减到0 说明凑出来了
        if(target==0){
            res.add(new ArrayList<>(path));
            return;
        }

        // --- 选择列表 ---
        for(int i=begin;i<candidates.length;i++){
            // 剪枝：如果当前数字已经比剩下的 target 大了，后面的肯定更不行，直接结束循环
            if(target-candidates[i]<0){
                break;
            }

            //1. 做选择
            path.add(candidates[i]);

            //2. 递归进入下一层
            // 注意：因为可以重复使用，所以下一轮的起点还是 i，而不是 i + 1
            // 目标值变成了 target - candidates[i]
            backtrack(candidates, target - candidates[i], i, path, res);

            // 3. 撤销选择 (回溯的核心)
            // 这一步如果不做，path 就会只增不减
            path.remove(path.size() - 1);
        }



    }
}
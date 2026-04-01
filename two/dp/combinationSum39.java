package two.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class combinationSum39 {
    public List<List<Integer>> combinationSum(int[] candidates,int target){
        List<List<Integer>> res=new ArrayList<>();

        Arrays.sort(candidates);

        backtrack(candidates,target,0,new ArrayList<>(),res);

        return res;


    }

    /**
     *
     * @param candidates 候选数组
     * @param target 当前还需要凑多少
     * @param begin 下一轮搜索的起点
     * @param path 当前已经选择了哪些数
     * @param res 结果集
     */
    private void backtrack(int[] candidates,int target,int begin,List<Integer> path,List<List<Integer>> res){
        // ----- 终止条件----
        //1.如果target刚好减到0 返回
        if(target==0){
            res.add(path);
            return;
        }

        // ---- 选择列表
        for(int i=begin;i<candidates.length;i++){
            if(target-candidates[i]<0){
                break;
            }

            //1.做选择
            path.add(candidates[i]);
            //2.递归进入下一层
            backtrack(candidates,target-candidates[i],i,path,res);
            //3.撤销选择
            path.remove(path.size()-1);
        }
    }
}

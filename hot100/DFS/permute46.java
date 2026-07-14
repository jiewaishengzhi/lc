package hot100.DFS;

import java.util.ArrayList;
import java.util.List;

public class permute46 {
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        boolean[] used=new boolean[nums.length];
        backtrack(nums,used,path,res);

        return res;

    }

    private void backtrack(int[] nums,boolean[] used,List<Integer> path,List<List<Integer>> res){
        //当前路径已经包含所有数字 得到一个排列
        if(path.size()==nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        //尝试把每一个尚未使用的数字放到当前位置
        for(int i=0;i<nums.length;i++){
            if(used[i]){
                continue;
            }
            //选择
            path.add(nums[i]);
            used[i]=true;
            //进入下一层
            backtrack(nums,used,path,res);

            //撤销选择
            path.remove(path.size()-1);
            used[i]=false;
        }
    }
}

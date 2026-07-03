package hot100.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class findDisappearedNumbers448 {
    public List<Integer> findDisappearedNumbers(int[] nums){
        int n=nums.length;
        List<Integer> res=new ArrayList<>();
        Set<Integer> set=new HashSet<>();

        for(int num:nums){
            set.add(num);
        }

        for(int i=1;i<=n;i++){
            if(!set.contains(i)){
                res.add(i);
            }
        }
        return res;
    }

    //空间O(1)  原地取负标记法
    public List<Integer> findDisappearedNumbers2(int[] nums){
        List<Integer> res=new ArrayList<>();
        int n=nums.length;

        //第一遍 标记出现过的数字对应下标(标为负数)
        for(int i=0;i<n;i++){
            int x=Math.abs(nums[i]);
            if(nums[x-1]>0){
                nums[x-1]= -nums[x-1];
            }
        }

        //第二遍
        for(int i=0;i<n;i++){
            if(nums[i]>0){
                res.add(i+1);
            }
        }
        return res;
    }
}

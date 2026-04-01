package two.array;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class twoSum1 {
    public int[] twoSum(int[] nums,int target){
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum2(int[] nums,int target){
        // <nums[i] i>
        Map<Integer,Integer> hashMap=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(hashMap.containsKey(target-nums[i])){
                return new int[]{hashMap.get(target-nums[i]),i};
            }
            hashMap.put(nums[i],i);
        }
        return new int[0];
    }
}

package two.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class containsDuplicate217 {
    public boolean containDuplicate(int[] nums){
//        Set<Integer> set=new HashSet<Integer>();
//        for(int i=0;i<nums.length;i++){
//            if(!set.add(nums[i])){
//                return true;
//            }
//        }
//        return false;

        Map<Integer,Integer> hashMap=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(hashMap.containsKey(nums[i])){
                return true;
            }
            hashMap.put(nums[i],i);
        }
        return false;

    }

}

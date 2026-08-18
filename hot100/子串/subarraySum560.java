package hot100.子串;

import java.util.HashMap;
import java.util.Map;

public class subarraySum560 {
    public static int subarraySum(int[] nums, int k){
        //前缀和 遍历到 r，当前前缀和是 prefix[r] 时，只要之前出现过前缀和 prefix[r] - k，就找到一个和为 k 的子数组。
        //HashMap   key：某个前缀和   value：该前缀和出现过几次

        Map<Integer,Integer> map=new HashMap<>();

        //初始化 前缀和为0的个数是1
        map.put(0,1);

        int sum=0;
        int res=0;

        for(int num:nums){
            sum+=num;

            // 之前出现过 sum - k，就形成一个和为 k 的子数组
            res+=map.getOrDefault(sum-k,0);

            // 当前前缀和加入记录，供后面的元素使用
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1=new int[]{1,1,1};
        int k1=2;
        System.out.println(subarraySum(nums1,k1));
    }
}

//2026.8.17 注意初始化

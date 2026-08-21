package hot100.双指针;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 */
public class threeSum15 {
    /*排序+固定一个数+双指针
    1.先排序
    2.固定第一个数nums[i]
    3.在i后面的区间里 用左右双指针找另外两个数
     */
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> res=new ArrayList<>();

        //1.先排序
        Arrays.sort(nums);

        for(int i=0;i<nums.length-2;i++){
            //第一个数去重
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            //排序后 第一个数字大于0 后续不能凑出0
            if(nums[i]>0){
                break;
            }

            int left=i+1;
            int right=nums.length-1;

            while(left<right){
                int sum=nums[i]+nums[left]+nums[right];

                if(sum<0){
                    left++;
                }else if(sum>0){
                    right--;
                }else{
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    //已经找到了一个 然后left 和 right 都动
                    left++;
                    right--;

                    //第二个数去重
                    while(left<right&&nums[left]==nums[left-1]){
                        left++;
                    }
                    //第三个数去重
                    while(left<right&&nums[right]==nums[right+1]){
                        right--;
                    }
                }
            }

        }
    return res;
    }
}
//2026.7.25 过一遍
//2026.7.29 注意循环右边界  右指针去重条件
//2026.8.3 一遍过 注意第一个剪枝条件  可以是三个0 但不能下标相同
//2026.8.8 相等时候去重忘了加条件left<right  别的过
//2026.8.19 快手二面
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class threeSum15 {
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();

        //1.先排序
        Arrays.sort(nums);
        int n=nums.length;

        //2.遍历每一个数作为第一个数 nums[i]
        for(int i=0;i<n;i++){
            // 剪枝优化：如果第一个数已经大于0，后面全是正数，不可能加起来等于0，直接结束
            if(nums[i]>0) break;

            //核心去重 1：如果当前数和前一个数一样，跳过
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }


            // 3. 双指针寻找另外两个数
            int left=i+1;
            int right=n-1;

            while(left<right){
                int sum=nums[i]+nums[left]+nums[right];

                if(sum==0){
                    // 找到一组解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    //核心去重2 左指针遇到重复值跳过
                    while(left<right&&nums[left] == nums[left + 1]){
                        left++;
                    }
                    // 核心去重 3：右指针遇到重复值，跳过
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    //找到答案后 双指针同时收缩
                    left++;
                    right--;

                }else if(sum<0){
                    //和太小，需要大一点的数字 左指针右移
                    left++;
                }else{
                    //和太大了 需要小一点的数 右指针左移
                    right--;
                }
            }
        }
        return result;
    }
}

package two.array;

public class maxProduct152 {
    public int maxProduct(int[] nums){
        //1.初始化
        //maxF 以当前元素结尾的最大乘积
        //minf 以当前元素结尾的最小乘积

        int maxF=nums[0];
        int minf=nums[0];

        //ans 全局最大值
        int ans=nums[0];

        //2.从第二个元素开始遍历
        for(int i=2;i<nums.length;i++){
            int mx=maxF;
            int mn=minf;

            //3.核心状态转移方程
            //当前的最大值=max(当前值，当前值*旧最大值，当前值*旧最小值)
            maxF=Math.max(nums[i], Math.max(nums[i]*mx,nums[i]*mn));

            //当前最小值=min(当前值，当前值*旧最大值，当前值*旧最小值)
            minf=Math.min(nums[i], Math.min(nums[i]*mx,nums[i]*mn));

            //4.更新全局最大值
            ans=Math.max(ans,maxF);
        }
        return ans;
    }
}

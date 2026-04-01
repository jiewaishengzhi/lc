package dp;

public class rob198 {
    public int rob(int[] nums){
        //1.处理边界条件
        if(nums==null||nums.length==0)return 0;

        if(nums.length==1)return nums[0];

        //2.定义dp数组
        //dp[i]表示偷到第i间房时 能拿到的最高金额
        int[] dp=new int[nums.length];

        //3.初始化基础状态
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);

        //4.状态转移
        for(int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }

        return dp[nums.length-1];
    }

    //优化空间
    public int rob2(int[] nums){
        //1.处理边界条件
        if(nums==null||nums.length==0)return 0;

        if(nums.length==1)return nums[0];

        int first=nums[0];
        int second=Math.max(nums[0],nums[1]);

        for(int i=2;i<nums.length;i++){
            //当前最大值
            int current=Math.max(second,nums[i]+first);

            //滚动更新
            first=second;
            second=current;
        }
        return second;
    }


}

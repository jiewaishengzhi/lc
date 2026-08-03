package hot100.dp;
/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素
就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class rob198 {
    //动态规划dp
    public int rob(int[] nums){
        int n=nums.length;
        if(n==1)return nums[0];

        int[] dp=new int[n];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<n;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[n-1];
    }

    //滚动更新
    public int rob2(int[] nums) {
        int n=nums.length;
        if(n==1)return nums[0];

        int first=nums[0];
        int second=Math.max(nums[0],nums[1]);

        for(int i=2;i<n;i++){
            int temp=second;
            second=Math.max(first+nums[i],second);
            first=temp;
        }

        return second;
    }
}
//2026.8.3 一遍过

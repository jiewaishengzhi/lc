package one.dp;

public class rob198 {
    public int rob(int[] nums){
        //1.处理边界条件
        if(nums==null||nums.length==0) return 0;
        if(nums.length==1) return nums[0];

        //2.定义dp数组
        //dp[i]表示 偷到第i间房子时，能拿到的最高金额
        int[] dp=new int[nums.length];

        //3.初始化基础状态
        dp[0]=nums[0]; //第一间 偷
        dp[1]=Math.max(nums[0],nums[1]); //第二间

        //4.状态转移
        for(int i=2;i<nums.length;i++){
            // 核心公式：要么不偷这间(继承前一间)，要么偷这间(拿这一间的钱 + 前前一间的总资产)
            dp[i]=Math.max(dp[i-1],nums[i]+dp[i-2]);
        }

        return dp[nums.length-1];
    }
}
package two.dp;

public class canJump55 {
    //贪心
    public boolean canJump(int[] nums){
        //maxReach表示当前能跳到的最远位置
        int maxReach=0;
        int n=nums.length;

        for(int i=0;i<n;i++){
            //1.如果当前位置i已经超过了最远能达到的位置 失败
            if(i>maxReach){
                return false;
            }

            //2.更新最远覆盖范围
            //在位置i 能跳nums[i]步 最远到i+nums[i]
            maxReach=Math.max(maxReach,i+nums[i]);

            //3.剪枝优化 如果覆盖范围已经到了终点 提前结束
            if(maxReach>=n-1){
                return true;
            }
        }
        return false;
    }

    //动态规划
    public boolean canJump2(int[] nums){
        int n=nums.length;
        boolean[] dp=new boolean[n];

        dp[0]=true;//起点可达
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                //如果j可达 且能从j跳到i
                if(dp[j]&&j+nums[j]>=i){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n-1];
    }
}

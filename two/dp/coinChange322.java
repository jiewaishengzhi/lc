package two.dp;

import java.util.Arrays;

public class coinChange322 {
    public int coinChange(int[] coins,int amount){
        //dp[i] 凑出金额i所需要的最少硬币数
        int[] dp=new int[amount+1];
        Arrays.fill(dp,amount+1); //先将dp[i]初始化为比最大值还大的值 方便后面换
        dp[0]=0;

        for(int i=1;i<=amount;i++){
            for (int coin:coins){
                if(i-coin>=0){
                    dp[i]=Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }

}

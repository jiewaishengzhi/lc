package hot100.dp;

import java.util.Arrays;

public class coinChange322 {
    /*
    dp[i] 凑出金额i所需要的最少硬币数量
    dp[i]=min([dp[i-coin]+1)  i>=coin
    初始化为amount+1 表示无法凑出 dp[0]=0
     */
    public int coinChange(int[] coins,int amount){
        int[] dp=new int[amount+1];

        Arrays.fill(dp,amount+1);
        dp[0]=0;

        //枚举需要凑出的金额
        for(int i=1;i<=amount;i++){
            //枚举最后使用的硬币
            for(int coin:coins){
                if(i>=coin){
                    dp[i]=Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }

        return dp[amount]==amount+1?-1:dp[amount];
    }
}

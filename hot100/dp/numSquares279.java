package hot100.dp;

import java.util.Arrays;

public class numSquares279 {
    /*
    dp[i] 和为i时，最少需要几个完全平方数
    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);

     */
    public int numSquares(int n){
        int[] dp=new int[n+1];
        Arrays.fill(dp,n+1);

        dp[0]=0;

        for(int i=1;i<=n;i++){
            for(int j=1;j*j<=i;j++){
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
}

// 2026.7.21 类似322

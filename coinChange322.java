import java.util.Arrays;

public class coinChange322 {
    public int coinChange(int[] coins,int amount){
        //1.创建dp数组
        //长度为amount+1 因为要算到dp[amount]
        int[] dp=new int[amount+1];

        //2.初始化
        //填充一个比amount大的数 作为无穷大的标记
        //用 Integer.MAX_VALUE？因为后面要 +1，如果是 MAX_VALUE 会溢出变成负数
        int max=amount+1;
        Arrays.fill(dp,max);

        //凑成金额0需要0个硬币
        dp[0]=0;

        //3.外层循环 遍历每一个金额(从1到amount)
        for(int i=1;i<=amount;i++){
            //4.内层循环 遍历每一枚硬币
            for(int coin :coins){
                //如果当前硬币面值<=当前要凑的金额 说明能用这枚硬币
                if(i-coin>=0){
                    // 核心转移方程：
                    // 当前的最少硬币数 = min(之前的记录, 用这枚硬币后的步数)
                    dp[i]=Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }

        //5.检查结果
        //如果dp[amount]还是初始值，说明无法凑出这个金额
        return dp[amount]>amount?-1:dp[amount];
    }
}

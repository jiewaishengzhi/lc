package hot100.dp;
/*
给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。然而，你可以在 同一天 多次买卖该股票，但要确保你持有的股票不超过一股。

返回 你能获得的 最大 利润 。
 */
public class maxProfit122 {
    //贪心
    public int maxProfit(int[] prices){
        int profit=0;

        for(int i=1;i<prices.length;i++){
            //今天比昨天贵 就赚
            if(prices[i]>prices[i-1]){
                profit+=prices[i]-prices[i-1];
            }
        }
        return profit;
    }

    //动态规划
    public int maxProfit2(int[] prices){
        //dp0:第i天结束时 不持有股票的最大利润
        //dp1:第i天结束时 持有股票的最大利润

        int noStock=0;
        int hasStock=-prices[0];

        for(int i=1;i<prices.length;i++){
            int preNoStock=noStock;
            int preHasStock=hasStock;

            //今天不持有： 昨天不持有 or 昨天持有今天卖掉
            noStock= Math.max(preNoStock,preHasStock+prices[i]);

            //今天持有： 昨天持有 or 昨天不持有今天买入
            hasStock=Math.max(preHasStock,preNoStock-prices[i]);
        }
        return noStock;
    }
}
//20226.8.3 忘记了 学一遍  记持有不持有滚动就行了
//2026.8.5 一遍过

package hot100.dp;
/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class maxProfit123 {
    public int maxProfit(int[] prices){
        //buy1: 完成第一次买入后 手里有股票的利润
        //sell1: 完成第一次卖出后 手里没股票的利润
        //buy2: 完成第二次买入后 手里有股票的利润
        //sell2: 完成第二次卖出后 手里没股票的利润

        if(prices==null || prices.length==0){
            return 0;
        }

        //第一天结束时的四种状态
        int buy1=-prices[0];
        int sell1=0;

        int buy2=-prices[0];
        int sell2=0;

        for(int i=1;i<prices.length;i++){
            int price=prices[i];

            //保存昨天的状态 避免状态更新相互影响
            int preBuy1=buy1;
            int preSell1=sell1;
            int preBuy2=buy2;
            int preSell2=sell2;

            //第一次买入后 持有股票的最大利润
            buy1=Math.max(preBuy1,-price);

            //第一次卖出后，不持有股票的最大利润
            sell1=Math.max(preSell1   ,preBuy1+price);

            //第二次买入后 持有股票的最大利润
            buy2=Math.max(preBuy2,preSell1-price);

            //第二次卖出后 不持有股票的最大利润
            sell2=Math.max(preSell2,preBuy2+price);
        }
        return sell2;
    }
}
//2026.8.3已忘 学一遍  记录四个值 第一、二次买入和卖出
//2026.8.5 一遍过
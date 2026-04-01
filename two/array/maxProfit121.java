package two.array;

public class maxProfit121 {
    public int maxProfit(int[] prices){ //暴力 超时
        int max=0;
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                int curr=prices[j]-prices[i];
                if(curr>max){
                    max=curr;
                }
            }
        }
        return max;
    }

    public int maxProfit2(int[] prices){
        int minPrice=Integer.MAX_VALUE;
        int maxProfit=0;

        for(int price:prices){
            if(price<minPrice){
                minPrice=price;
            }else if(price-minPrice>maxProfit){
                maxProfit=price-minPrice;
            }
        }
        return maxProfit;
    }
}

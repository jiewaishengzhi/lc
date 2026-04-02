package one.dp;

public class climbStairs70 {
    public int climbStairs(int n){
        // 递归终止条件 (Base Case)
        if (n == 1) return 1;
        if (n == 2) return 2;

        //递归公式 f(n) = f(n-1) + f(n-2)
        // 缺点：会有极其严重的重复计算
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairs2(int n){
        //1.处理特殊情况
        if(n==1)return 1;

        //2.创建dp数组
        // 大小设为 n + 1 是为了让下标直接对应层数（dp[3] 就是第3层），方便理解
        int[] dp=new int[n+1];

        //3.初始化基础值
        dp[1]=1;
        dp[2]=2;

        //4.从第3层开始往上填表
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }

        //3.返回第n层的结果
        return dp[n];
    }

    public int climbStairs3(int n){
        //1.处理特殊情况
        if(n==1) return 1;
        if(n==2) return 2;

        //2.定义两个变量 分别代表前前一步和前一步
        int first=1;
        int second=2;

        //3.从第三层开始迭代
        for(int i=3;i<=n;i++){
            //算出当前层
            int current=first+second;

            //4.滚动更新
            first=second;
            second=current;
        }
        // 循环结束时，second 存的就是 f(n)
        return second;
    }
}
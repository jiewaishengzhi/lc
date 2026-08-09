package hot100.tree;

public class numTrees96 {
    public int numTrees(int n){
//        枚举根节点，左边的方案数 × 右边的方案数，然后把每个根节点的情况加起来。

        //dp[k] 表示 k 个节点能组成多少种 BST
        int[] dp=new int[n+1];
        dp[0]=1; //空树算一种
        dp[1]=1;

        //外层循环：遍历每个节点
        for(int i=2;i<=n;i++){
            //内层循环 哪个节点当根
            for(int j=1;j<=i;j++){
                int leftNodes=j-1;
                int rightNodes=i-j;

                dp[i]+=dp[leftNodes]*dp[rightNodes];
            }
        }
        return dp[n];
    }
}
//2026.8.9 思路记得 具体边界需要再刷

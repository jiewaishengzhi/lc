public class uniquePaths62 {
    public int uniquePaths(int m,int n){
        //dp[i][j]表示到达(i,j)的路径处
        int[][] dp=new int[m][n];

        //初始化
        // 虽然在循环里判断也可以，但为了性能，
        // 我们可以先把第一行和第一列单独处理，或者利用循环逻辑

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                //1.起点：第一行 第一列的情况
                if(i==0||j==0){
                    dp[i][j]=1;
                }
                else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}

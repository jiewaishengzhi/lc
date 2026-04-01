package two.dp;

public class uniquePaths62 {
    public int uniquePaths(int m,int n){
        //dp[i][j]表示到达[i,j]的路径数
        int[][] dp=new int[m][n];

        //初始化
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                //1.起点 第一行 第一列全为1
                if(i==0||j==0){
                    dp[i][j]=1;
                }
                //其他格子=上边加左边
                else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}

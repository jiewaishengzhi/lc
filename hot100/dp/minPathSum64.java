package hot100.dp;

public class minPathSum64 {
    /*
    dp[i][j] 表示从左上角走到 (i, j) 的最小路径和。
    到达 (i, j) 只有两个方向：从上方 (i - 1, j) 向下走；  从左方 (i, j - 1) 向右走。
     */
    public static int minPathSum(int[][] grid){
        int m=grid.length;
        int n=grid[0].length;

        int[][] dp=new int[m][n];

        dp[0][0]=grid[0][0];

        //第一行 只能从左边过来
        for(int j=1;j<n;j++){
            dp[0][j]=dp[0][j-1]+grid[0][j];
        }
        //第一列
        for(int i=1;i<m;i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }

        //其它位置
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] grid1={
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(minPathSum(grid1));

        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println(minPathSum(grid2)); // 12

        int[][] grid3 = {
                {5}
        };
        System.out.println(minPathSum(grid3)); // 5
    }
}
//2026.8.13 第一次写 二维dp
package hot100.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class uniquePaths62 {
    // 时间空间复杂度均为 O(m*n)
    public static int uniquePaths(int m,int n){
        //dp[i][j]表示到达(i,j)的路径数
        int[][] dp=new int[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0||j==0){
                    dp[i][j]=1;
                }else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine());

        int m=Integer.parseInt(st.nextToken());
        int n=Integer.parseInt(st.nextToken());
        System.out.println(uniquePaths(m,n));
    }
}
//2026.8.13 过
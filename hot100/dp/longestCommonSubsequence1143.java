package hot100.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class longestCommonSubsequence1143 {
    public static int longestCommonSubsequence(String text1, String text2){
        //二维动态规划  dp[i][j]    text1 的前 i 个字符，即 text1[0...i-1]  text2 的前 j 个字符，即 text2[0...j-1]

        int m=text1.length();
        int n=text2.length();

        int[][] dp=new int[m+1][n+1];

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                char c1=text1.charAt(i-1);
                char c2=text2.charAt(j-1);
                if(c1==c2){
                    //如果两个字符相等，它们可以作为公共子序列的最后一个字符
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    //不能同时选这两个字符，需要尝试舍弃其中一个：
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        //第一行text1
        String text1=br.readLine().trim();
        //第二行text2
        String text2=br.readLine().trim();

        System.out.println(longestCommonSubsequence(text1,text2));
    }
}

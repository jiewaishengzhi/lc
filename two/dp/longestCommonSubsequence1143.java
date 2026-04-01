package dp;

public class longestCommonSubsequence1143 {
    public int longestConmmonSubsequence(String text1,String text2){
        int m=text1.length();
        int n=text2.length();

//        dp[i][j]表示text[0...i-1]和text[0...j-1]的LCS下标长度
        int[][] dp=new int[m+1][n+1];

        for(int i=1;i<=m;i++){
            //获取text1的当前字符
            char c1=text1.charAt(i-1);

            for(int j=1;j<=n;j++){
                //获取text2的当前字符
                char c2=text2.charAt(j-1);

                if(c1==c2){
                    // 情况 1：字符相同 -> 左上角 + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    // 情况 2：字符不同 -> 取左边或上边的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

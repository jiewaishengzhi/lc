package one.dp;

public class numDecondings91 {
    public int numDecoding(String s){
        int n=s.length();
        //dp[i] 表示当前i个字符的解码方法总数
        int[] dp=new int[n+1];

        //1.初始化
        dp[0]=1; //空串算一种

        //2.遍历字符串 i从1开始 对应s中的第i个字符 s[i-1]
        for(int i=1;i<=n;i++){
            //获取当前字符
            char curr=s.charAt(i-1);

            // --- 情况 A：单独解码当前位 ---
            // 只要不是 '0'，就可以单独作为一个字母 A-I
            if(curr!='0'){
                dp[i]+=dp[i-1];
            }

            // --- 情况 B：和前一位组合解码 ---
            // 必须要有前一位存在 (i > 1)
            if(i>1){
                char prev=s.charAt(i-2);
                //组合数字val
                int val=(prev-'0')*10+(curr-'0');

                // 只有在 10 到 26 之间才是合法的字母 J-Z
                // 注意：像 "06" 这种，val是6，不在10-26范围内，会被自动过滤
                if(val>=10&&val<=26){
                    dp[i]+=dp[i-2];
                }
            }

        }
        return dp[n];
    }
}
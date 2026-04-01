package dp;

public class numDecoding91 {
    public int numDecoding(String s){
        int n=s.length();
        //dp[i]表示当前i个字符的解码方法总数
        int[] dp=new int[n+1];

        //1.初始化
        dp[0]=1; //空串算一种

        //2.遍历字符串 i从1开始 对应s中的第i个字符s[i-1]
        for(int i=1;i<=n;i++){
            //获取当前字符
            char curr=s.charAt(i-1);

            //情况A：单独解码当前位
            if(curr!='0'){
                dp[i]+=dp[i-1];
            }

            //情况B：和前一位组合解码  前一位必须存在
            if(i>1){
                char prev=s.charAt(i-2);
                int val=(prev-'0')*10+(curr-'0');
                //10-26才是合法字母 J-Z
                if(val>=10&&val<=26){
                    dp[i]+=dp[i-2];
                }
            }
        }
        return dp[n];
    }
}

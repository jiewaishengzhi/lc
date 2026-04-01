package string;

public class longestPalindrome5 {
    //动态规划 如果一个子串是回文 且两端字符相同 那么去掉两端后仍然是回文
    public String longestPalindrome(String s){
        //dp[i][j]表示s[i...j]是回文子串
        //dp[i][j]= s[i]==s[j] && dp[i+1][j-1]

        int n=s.length();
        if(n<2)return s;

        boolean[][] dp=new boolean[n][n];
        int maxLen=1;
        int start=0;

        //初始化 所有单字符都是回文
        for(int i=0;i<n;i++){
            dp[i][i]=true;
        }

        //按子串长度枚举
        for(int len=2;len<=n;len++){
            for(int i=0;i<=n-len;i++){
                int j=i+len-1; //子串右边界
                if(s.charAt(i)==s.charAt(j)){
                    if(len==2){
                        dp[i][j]=true;
                    }else{
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                if(dp[i][j]&&len>maxLen){
                    maxLen=len;
                    start=i;
                }
            }
        }
        return s.substring(start,start+maxLen);
    }

    //中心扩散
    public String longestPalindrome2(String s){
        if(s==null||s.length()<2)return s;

        int start=0;
        int maxLen=1;

        for(int i=0;i<s.length();i++){
            //以单字符为中心 (奇数长度)
            int len1=expandAroundCenter(s,i,i);
            //以双字符为中心(偶数长度)
            int len2=expandAroundCenter(s,i,i+1);

            int len=Math.max(len1,len2);
            if(len>maxLen){
                maxLen=len;
                start=i-(len-1)/2; //奇偶都是这个公式(需推导)
            }
        }
        return s.substring(start,start+maxLen);
    }

    /**
     * 从left到right开始向两边扩散 返回回文串长度
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int expandAroundCenter(String s,int left,int right){
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        //退出时 left和right都多走了一步
        return right-left-1;//回文长度
    }
}

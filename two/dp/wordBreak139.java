package dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class wordBreak139 {
    public boolean wordBreak(String s, List<String> wordDict){
        //1.将字典转为HashSet 利用O(1)的查找速度
        Set<String> wordSet=new HashSet<>(wordDict);

        int n=s.length();
        //2.dp[i] 表示s的前i个字符是否可拆分
        boolean[] dp=new boolean[n+1];

        //3.初始化 空串可以被拆分
        dp[0]=true;

        //4.外层循环 i表示当前子串的长度(从1到n)
        for(int i=1;i<=n;i++){
            //5.内层循环 j表示切分点
            //尝试在索引j处切一刀 看s[j...i-1]是否是单词
            for(int j=0;j<i;j++){
                //核心判断 如果前半部分dp[j]是true 且后半部分s.substring(j,i)在字典里
                if(dp[j]&&wordSet.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        //返回s整个字符的状态
        return dp[n];
    }
}

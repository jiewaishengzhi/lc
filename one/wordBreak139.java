import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class wordBreak139 {
    public boolean wordBreak(String s, List<String> wordDict){
        //1.将字典转换为HashSet 利用o(1)的查找速度
        Set<String> wordSet=new HashSet<>(wordDict);

        int n=s.length();
        //2.dp[i]表示s的前i个字符是否可以拆分
        // 长度设为n+1 因为要包含dp[0](空串)到dp[n]
        boolean[] dp=new boolean[n+1];

        //3.初始化:空串是可以被拆分的
        dp[0]=true;

        //4.外层循环 i表示当前子串的长度
        for(int i=1;i<=n;i++){
            //5.内层循环 j表示切分点
            //尝试在索引j处切一刀 看s[j...j-1]是否是单词
            for(int j=0;j<i;j++){
                // 核心判断：
                // 如果前半部分 dp[j] 是 true，且后半部分 s.substring(j, i) 在字典里
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    // 只要找到一种切法就行了，不需要再试别的 j 了，跳出内层循环
                    break;
                }
            }
        }
        // 返回 s 的前 n 个字符（即整个 s）的状态
        return dp[n];
    }
}

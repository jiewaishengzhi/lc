package hot100.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class wordBreak139 {
    public static boolean wordBreak(String s, List<String> wordDict){
        Set<String> set=new HashSet<>(wordDict);

        int n=s.length();
        //dp[i]表示 s 的前 i 个字符，即 s[0...i-1]，能否被字典单词拼接出来。
        boolean[] dp=new boolean[n+1];
        //空字符串可以拆分成功
        dp[0]=true;

        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                //切分为s[j,j-1]
                if(dp[j]&&set.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //第一行 字符串s
        String s=br.readLine().trim();

        //第二行 字典单词数量
        int n=Integer.parseInt(br.readLine().trim());

        //接下来n行 每一个字典单词
        List<String> wordDict=new ArrayList<>();
        for(int i=0;i<n;i++){
            wordDict.add(br.readLine().trim());
        }

        boolean res=wordBreak(s,wordDict);
        System.out.println(res);
    }
}
//2026.8.13 用集合放字典中的每个单词 dp[i]表示[0,i-1]是否可分 对于每个i 从0到i-1都分成两部分分别判断

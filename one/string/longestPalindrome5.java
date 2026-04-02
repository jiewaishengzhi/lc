package one.string;

public class longestPalindrome5 {
    public String longestPalindrome(String s){
        int len = s.length();
        if(len<2){
            return s;
        }

        int maxLen=1;
        int begin=0;

        //1.定义dp表
        //dp[i][j] 表示s[i..j]是否为回文串
        boolean[][] dp=new boolean[len][len];

        //2.初始化 所有长度为1的子串都是回文串
        for(int i=0;i<len;i++){
            dp[i][i]=true;
        }

        //3.开始填表
        //i 要从下往上找 (len-1 -> 0)
        for(int i=len-1;i>=0;i--){
            // j 要从 i 往右找
            for(int j=i+1;j<len;j++){
                // 核心判断逻辑
                if(s.charAt(i)!=s.charAt(j)){
                    dp[i][j]=false;
                }else{
                    // 如果字符相等，分两种情况：
                    // A. 长度小于 3 (例如 "aa", "aba")，不需要查内核，直接 true
                    // B. 长度 >= 3，查左下角的内核 dp[i+1][j-1]
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 4. 只要 dp[i][j] 是 true，就看看是不是最长的
                if (dp[i][j] && (j - i + 1 > maxLen)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }

        }
        return s.substring(begin, begin + maxLen);

    }

    public String longestPalindrome2(String s){
        if(s==null||s.length()<1){
            return "";
        }

        //记录最长回文子串的起始索引和结束索引
        int start=0;
        int end=0;

        for(int i=0;i<s.length();i++){
            // 1. 情况一：以 i 为中心扩散 (处理奇数长度，如 "aba")
            int len1 = expandAroundCenter(s, i, i);

            // 2. 情况二：以 i 和 i+1 为中心扩散 (处理偶数长度，如 "abba")
            int len2 = expandAroundCenter(s, i, i + 1);

            // 取两种情况中更长的那个
            int len = Math.max(len1, len2);

            // 如果找到了更长的回文串，更新 start 和 end
            if (len > end - start) {
                // 这里的计算逻辑稍微有点绕，画图最好理解：
                // 如果 len 是奇数(如3)，中心是 i。start = i - 1, end = i + 1
                // 如果 len 是偶数(如4)，中心是 i和i+1。start = i - 1, end = i + 2
                // 下面这两个公式是通用的：
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        // 注意 substring 是“包头不包尾”的，所以 end 要 +1
        return s.substring(start, end + 1);
    }

    /**
     * 辅助函数：从 left 和 right 开始向两边扩散
     * 返回扩散后的回文串长度
     */
    private int expandAroundCenter(String s,int left,int right){
        // 当索引没越界，且左右字符相等时，继续扩
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 循环结束时，left 和 right 已经分别多走了一步（到了不匹配的位置）
        // 比如最终停在 left=1, right=5，实际回文范围是 2..4
        // 长度计算公式：(right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }

}
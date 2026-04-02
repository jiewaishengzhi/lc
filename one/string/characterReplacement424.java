package one.string;

public class characterReplacement424 {
    public int characterReplace(String s,int k){
        // 用于记录窗口内每个字符出现的次数 (A-Z)
        int[] count=new int[26];

        int left=0;
        int maxCount=0; // 窗口内出现最多的字符的次数
        int maxLen=0; // 结果

        for(int right=0;right<s.length();right++){
            //1.进窗口 更新当前字符的计数
            int index=s.charAt(right)-'A';
            count[index]++;

            // 更新“历史”最大频率
            // 注意：这里其实只要维护“曾经出现过的最大频率”即可，下面会解释
            maxCount = Math.max(maxCount, count[index]);

            // 2. 检查窗口合法性
            // 窗口长度 - 出现最多的字符数量 > k，说明 k 不够用了
            if(right-left+1-maxCount>k){
                // 3. 出窗口：缩小左边界
                count[s.charAt(left)-'A']--;
                left++;
            }

            // 此时窗口一定是合法的（或者刚刚平移过，保持了最大合法长度）
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
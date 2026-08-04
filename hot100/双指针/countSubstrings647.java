package hot100.双指针;

public class countSubstrings647 {
    public int countSubstrings(String s){
        int count=0;

        for(int i=0;i<s.length();i++){
            // 以 i 为中心：奇数长度回文
            count+=extend(s,i,i);
            // 以 i 和 i + 1 之间为中心：偶数长度回文
            count+=extend(s,i,i+1);
        }
        return count;
    }

    //辅助函数 从centerLeft和centerRight像两边扩散
    //返回中这个中心能找到的回文串个数
    private int extend(String s,int left,int right){
        int count=0;
        //索引没越界 且左右字符相等 找到一个回文串
        while(left>=0&&right<s.length()
        && s.charAt(left)==s.charAt(right)){
            count++;
            left--;
            right++;
        }
        return count;
    }
}
//2026.8.4 第一次写
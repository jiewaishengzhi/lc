package one.string;

public class countSubstrings647 {
    public int countSubstring(String s){
        //统计回文子串的总数
        int count=0;

        for(int i=0;i<s.length();i++){
            //1. 奇数：以i为中心
            count+=extend(s,i,i);

            //2.偶数 以i和i+1为中心
            count+=extend(s,i,i+1);
        }
        return count;
    }

    private int extend(String s,int left,int right){
        int res=0;
        // 当索引没越界，且左右字符相等时，说明找到了一个回文串
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            res++; //计数器+1
            left--;
            right++;
        }
        return res;
    }
}
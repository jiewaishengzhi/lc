package string;

public class countSubstrings647 {
    public int countSubstrings(String s){
        //统计回文子串的长度
        int count=0;

        for(int i=0;i<s.length();i++){
            //1.以i为中心(奇数长度)
            count+=extend(s,i,i);

            //2.以i和i+1为中心(偶数长度)
            count+=extend(s,i,i+1);
        }
        return count;
    }

    /**
     * 从 centerLeft 和 centerRight 向两边扩散
     * @param s
     * @param left
     * @param right
     * @return 从这个中心能找到的回文串个数
     */
    private int extend(String s,int left,int right){
        int res=0;
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            res++;
            left--;
            right++;
        }
        return res;
    }
}

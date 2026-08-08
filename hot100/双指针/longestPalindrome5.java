package hot100.双指针;

public class longestPalindrome5 {
    public String longestPalindrome(String s){
        int start=0;
        int maxLen=1;

        for(int i=0;i<s.length();i++){
            //奇数回文长度  中心为i
            int len1=expand(s,i,i);

            //偶数回文长度
            int len2=expand(s,i,i+1);

            int len=Math.max(len1,len2);

            if(len>maxLen){
                maxLen=len;
                start=i-(len-1)/2;
            }
        }
        return s.substring(start,start+maxLen);
    }

    //返回以left right为中心能扩展出的最大回文长度
    private int expand(String s,int left,int right){
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        //循环结束后 left和right多走了一步
        return right-left-1;
    }
}
//2026.8.4 第一次写
//2026.8.8  循环是多加了一次的 返回长度为right-left-1  写成+1了  start=i-(len-1)/2 死记一下
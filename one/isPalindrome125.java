public class isPalindrome125 {
    public boolean isPalindrome(String s){
        if(s==null||s.length()==0){
            return true;
        }

        int left=0;
        int right=s.length()-1;

        while(left<right){
            char lChar=s.charAt(left);
            char rChar=s.charAt(right);

            //1.左指针 遇到非字母数字 跳过
            if(!Character.isLetterOrDigit(lChar)){
                left++;
            }
            //2.右指针 遇到非字母数字 跳过
            else if(!Character.isLetterOrDigit(rChar)){
                right++;
            }
            //3.都是合法字符 转为小写比较
            else{
                if(Character.toLowerCase(lChar)!=Character.toLowerCase(rChar)){
                    // 只要有一对对不上，就不是回文
                    return false;
                }
                // 这一对匹配成功，继续向中间逼近
                left++;
                right--;
            }
        }
        return true;
    }
}

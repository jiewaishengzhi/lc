package hot100.滑动窗口;

import java.util.ArrayList;
import java.util.List;

public class findAnagrams438 {

    /*核心思路：固定长度滑动窗口
    在 s 上维护一个长度固定为 p.length() 的窗口。
    只要窗口中每个字母的出现次数与 p 相同，就记录窗口左边界。
    使用长度为 26 的数组 count 统计 p 的字符频率  新进入窗口的字符：count[字符]--  移出窗口的字符：count[字符]++
    维护变量 valid：表示当前还有多少个字符没有匹配完成
     */
    public static List<Integer> findAnagrams(String s, String p){
        List<Integer> result=new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }

        int[] count=new int[26];
        for(char c:p.toCharArray()){
            count[c-'a']++;
        }
        int left=0;
        int valid=p.length();

        for(int right=0;right<s.length();right++){
            //右侧字符进入窗口
            int rightIndex=s.charAt(right)-'a';

            if(count[rightIndex]>0){
                valid--;
            }
            count[rightIndex]--;

            //如果窗口超过p的长度 左侧字符移出
            if(right-left+1>p.length()){
                int leftIndex=s.charAt(left)-'a';

                //如果移出前大于等于0 说明移走了一个原本匹配的字符
                if(count[leftIndex]>=0){
                    valid++;
                }

                //移走
                count[leftIndex]++;
                left++;
            }

            if(valid==0){
                result.add(left);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        // [0, 6]

        System.out.println(findAnagrams("abab", "ab"));
        // [0, 1, 2]

        System.out.println(findAnagrams("aaaa", "b"));
        // []
    }
}
//2026.8.14 不过 还需要刷

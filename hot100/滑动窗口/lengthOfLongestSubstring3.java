package hot100.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class lengthOfLongestSubstring3 {
    //无重复字符的最长子串（子串：连续）
    public int lengthOfLongestSubstring(String s){
        //用两个指针维护一个窗口 [left.right] 窗口内始终保持无重复字符
        //遍历字符串 让right不断右移、扩大窗口
            //如果新加入的字符没有重复 窗口合法 更新最大长度
            //如果新加入字符重复：移动left 缩小窗口 直到没有重复

        //HashMap 记录字符最后出现的位置
        Map<Character,Integer> lastIndex=new HashMap<>();

        int left=0;
        int maxLength=0;

        for(int right=0;right<s.length();right++){
            char c=s.charAt(right);
            //如果c在当前窗口出现过 left跳到它上次出现的下一位
            if(lastIndex.containsKey(c)){
                left=Math.max(left,lastIndex.get(c)+1); //上次的c可能已经被移除窗口 所以必须要大于left
            }
            //更新c最后一次出现的位置
            lastIndex.put(c,right);

            //更新最大窗口长度
            maxLength=Math.max(maxLength,right-left+1);

        }
        return maxLength;
    }
}
//2026.7.25 过一遍
//2026.7.26 过一遍 left新加入有重复移动  right往右遍历
//2026.8.3 一遍过 maxLen更新位置第一次写错
//2026.8.8 left更新位置为下一个，写成当前的了
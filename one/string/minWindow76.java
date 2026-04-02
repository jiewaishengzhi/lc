package one.string;

import java.util.HashMap;
import java.util.Map;

public class minWindow76 {
    public String minWindow(String s,String t){
        if(s==null ||s.length()==0||t==null||t.length()==0){
            return "";
        }

        //1.准备need账本 记录t中每个字符需要的数量
        Map<Character,Integer> need=new HashMap<>();
        for(char c:t.toCharArray()){
            // getOrDefault: 有就拿出来，没有就默认0，然后+1放回去
            need.put(c,need.getOrDefault(c,0)+1);
        }

        //2.准备window账本 记录当前窗口里有的字符数量
        Map<Character,Integer> window=new HashMap<>();

        int left=0,right=0;
        int valid=0; //记录有多少种字符已经达标

        //记录最小子串的起始位置和长度
        int start=0;
        int minLen=Integer.MAX_VALUE;

        while(right<s.length()){
            //c为将要移入窗口的字符
            char c=s.charAt(right);
            right++;

            // ---进行窗口内数据更新 ---
            // 只有当这个字符是 t 需要的，我们才关心
            if(need.containsKey(c)){
                //在window里记录数量
                window.put(c,window.getOrDefault(c,0)+1);

                // 核心判断：数量是否刚好达标？
                // 【注意坑点】这里 Integer 比较建议用 equals，
                // 虽然 LeetCode 的测试用例通常字符数不超 127 (在缓存池内)，
                // 但标准写法必须是 equals，否则大数会对比失败！
                if(window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            // ---判断左侧窗口是否要收缩 ---
            //valid==need.size() 说明所有要求的字符的种类都齐了
            while(valid==need.size()){
                //更新结果
                if(right-left<minLen){
                    start=left;
                    minLen=right-left;
                }

                // d是将要移除窗口的字符串
                char d=s.charAt(left);
                left++;

                // --- 进行窗口内数据更新 ---
                if(need.containsKey(d)){
                    // 如果移除前，数量刚好达标，移除后就不达标了，valid 减 1
                    if(window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    // 数量减 1
                    window.put(d,window.get(d)-1);
                }
            }
        }
        return minLen==Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
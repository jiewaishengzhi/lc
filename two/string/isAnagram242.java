package string;

import java.util.HashMap;
import java.util.Map;

public class isAnagram242 {
    public boolean isAnagram(String s,String t){
        //1.长度不同 返回false
        if(s.length()!=t.length()){
            return false;
        }

        //2.准备一个长度为26的账本
        int[] count=new int[26];

        for(int i=0;i<s.length();i++){
            count[s.charAt(i)-'a']++; //s中出现的字符+1
            count[t.charAt(i)-'a']--; //t中出现的字符-1
        }

        for(int c:count){
            if(c!=0)return false; //有不为0的说明字符数量不匹配
        }
        return true;
    }

    //支持unicode字符
    public boolean isAnagram2(String s,String t){
        if(s.length()!=t.length())return false;

        Map<Character,Integer> map=new HashMap<>();

        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)-1);
        }

        for(int count:map.values()){
            if(count!=0)return false;
        }
        return true;
    }

    //unicode优化
    public boolean isAnagram3(String s,String t){
        if(s.length()!=t.length())return false;

        Map<Character,Integer> map=new HashMap<>();

        for(int i=0;i<s.length();i++){
            char cs=s.charAt(i);
            char ct=t.charAt(i);

            map.put(cs,map.getOrDefault(cs,0)+1);
            if(map.get(cs)==0)map.remove(cs);//计数归0时移除

            map.put(ct,map.getOrDefault(ct,0)-1);
            if(map.get(ct)==0)map.remove(ct);
        }
        return map.isEmpty();
    }
}

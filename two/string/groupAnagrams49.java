package string;

import java.lang.reflect.Array;
import java.util.*;

public class groupAnagrams49 {
    //排序法
    public List<List<String>> groupAnagrams(String[] strs){
        //1.检查空输入
        if(strs==null||strs.length==0){
            return new ArrayList<>();
        }

        //2.准备哈希表 key为排序后的string value为所有单词List<String>
        Map<String,List<String>> map=new HashMap<>();

        //3.遍历每个单词
        for(String s:strs){
            // ---生成Key(排序) ---
            char[] chars=s.toCharArray();
            Arrays.sort(chars);
            String key=new String(chars);

            // ---放入Map ---
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }

            //把原始的单词s加入对应的名单
            map.get(key).add(s);
        }

        //4.返回Map中的所有value
        return new ArrayList<>(map.values());
    }

    //计数法
    public List<List<String>> groupAnagrams2(String[] strs){
        //1.检查空输入
        if(strs==null||strs.length==0){
            return new ArrayList<>();
        }

        //2.准备哈希表
        Map<String,List<String>> map=new HashMap<>();

        for(String s:strs){
            //1.统计词频
            int[] counts=new int[26];
            for(char c:s.toCharArray()){
                counts[c-'a']++;
            }

            //2.生成唯一key
            //把counts数组变成一个字符串
            // 规则 按顺序遍历a到z 将字符和它出现的次数拼起来 如a2b1
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<26;i++){
                if(counts[i]>0){
                    sb.append((char)('a'+i));
                    sb.append(counts[i]);
                }
            }
            String key=sb.toString();

            //3.放入map
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}

package hot100.哈希表;

import java.util.*;

public class groupAnagrams49 {
    //排序作为key
    public List<List<String>> groupAnagrams(String[] strs){
        // key：排序后的字符串
        // value：所有具有相同 key 的原字符串
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            //字符串转化为字符数组
            char[] chars=str.toCharArray();
            //字符数组设为有序
            Arrays.sort(chars);
            //构造key
            String key=new String(chars);

            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs){
        Map<String,List<String>> map=new HashMap<>();

        for(String str:strs){
            int[] count=new int[26];

            for(int i=0;i<str.length();i++){
                count[str.charAt(i)-'a']++;
            }

            //构造key
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<26;i++){
                sb.append("#");
                sb.append(count[i]);
            }
            String key=sb.toString();
            List<String> list=map.getOrDefault(key,new ArrayList<>());
            list.add(str);
            map.put(key,list);
        }
        return new ArrayList<>(map.values());
    }
}
//2026.7.21 构造key再记录一下  哈希表思想

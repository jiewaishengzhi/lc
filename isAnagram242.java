import java.util.HashMap;
import java.util.Map;

public class isAnagram242 {
    public boolean isAnagram(String s,String t){
        //1.长度不同 返回false4
        if(s.length()!=t.length()){
            return false;
        }

        //2.准备一个长度为26的 “账本”
        //record[0] 代表a的数量 record代表b....
        int[] record=new int[26];

        //3.遍历s 进行记账
        for(int i=0;i<s.length();i++){
            // s.charAt(i) - 'a' 可以把字符映射到 0-25 的下标
            record[s.charAt(i)-'a']++;
        }

        //4.遍历t 进行销账
        for(int i=0;i<t.length();i++){
            int index=t.charAt(i)-'a';
            record[index]--;

            // 核心剪枝：
            // 如果减完之后小于 0，说明 t 里这个字符比 s 里多，
            // 或者 t 里出现了 s 里没有的字符。直接判死刑。
            if(record[index]<0){
                return false;
            }
        }

        // 5. 因为长度相同且没有负数，说明刚好抵消完，一定是 true
        return true;
    }

    public boolean isAnagram2(String s,String t){
        if(s.length()!=t.length()){
            return false;
        }

        //使用HashMap 支持任意字符
        Map<Character,Integer> map=new HashMap<>();

        // 1. 遍历 s，统计词频
        for(char c:s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }

        // 2. 遍历 t，做减法
        for(char c:t.toCharArray()){
            // 如果 map 里压根没这个字，直接挂
            if(!map.containsKey(c)){
                return false;
            }

            // 减 1
            map.put(c,map.get(c)-1);

            // 优化：如果减这就变成了负数，直接挂
            if (map.get(c) < 0) {
                return false;
            }
        }
        return true;
    }
}

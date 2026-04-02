import java.util.HashMap;
import java.util.Map;

public class lengthOfLongestSubstring3 {
    public int lengthOfLongestSubstring(String s){
        if(s.length()==0) return 0;

        //map 记录字符最后一次出现的索引位置
        //Key 字符 Value下标
        Map<Character,Integer> map=new HashMap<>();

        int maxLen=0;
        int left=0; //左边界

        //right 串口右边界 主动往右滑
        for(int right=0;right<s.length();right++){
            char curr=s.charAt(right);

            //如果map里面已经有这个字符 说明重复了
            if(map.containsKey(curr)){
                // 更新左边界 left
                // 注意：为什么要取 max？
                // 因为 map 里存的那个重复字符可能在 left 的左边（已经被移出窗口了），
                // 我们不能让 left 倒回去，所以只能往右走。
                left=Math.max(map.get(curr)+1,left);
            }

            // 更新字符的位置（无论是否重复都要更新到最新的位置）
            map.put(curr,right);

            //计算当前无重复窗口的长度 并且更新全局最大值
            maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }

}

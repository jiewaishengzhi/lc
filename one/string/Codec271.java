package one.string;

import java.util.ArrayList;
import java.util.List;

/*设计一个算法，用来把一个字符串列表 List<String> 编码成一个字符串，
再把这个字符串解码回原来的字符串列表。你需要实现两个函数：
String encode(List<String> strs)
List<String> decode(String s)
要求：
编码后的结果必须能被正确还原
字符串中可能包含任意字符，比如数字、符号、空串等
不能依赖外部状态，decode(encode(strs)) 必须还原出原列表
示例：
输入: ["lint","code","love","you"]
编码: 某个字符串
解码后: ["lint","code","love","you"]

 */
public class Codec271 {
    /**
     * 编码：把字符串列表编码成一个字符串
     * @param strs
     * @return
     * 编码规则：
     *  每个字符串都编码成：
     *  字符串长度 + '#' + 字符串本身
     *  先读 '#' 前面的数字，知道后面的字符串长度
     * 然后严格按长度截取内容
     * 即使字符串内容里本身包含 '#'，也不会混淆
     */
    public String encode(List<String> strs){
        StringBuilder sb=new StringBuilder();
        //遍历每个字符串,按规则编码
        for(String str:strs){
            //先拼接字符串长度
            sb.append(str.length());
            //再拼接分隔符 #
            sb.append('#');
            //最后拼接字符串内容本身
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     *
     * @param s
     * @return
     * 解码思路：
     *      1. 从左到右扫描
     *      2. 先找到 '#'，取出前面的长度数字
     *      3. 根据这个长度，截取后面的字符串内容
     *      4. 重复直到扫描完整个字符串
     */
    public List<String> decode(String s){
        List<String> res=new ArrayList<>();

        //i表示当前扫描位置
        int i=0;
        //只要还没扫完整个字符串就继续解码
        while(i<s.length()){
            //j用来寻找当前这段长度信息后面的#
            int j=i;
            while(s.charAt(j)!='#'){
                j++;
            }

            //截取长度字符串 并且转换为整数
            int len=Integer.parseInt(s.substring(i,j));
            //字符串内容的起点在#后面一位
            int start=j+1;
            //字符串终点为 起点+长度
            int end=start+len;
            //按照长度截取原字符串
            res.add(s.substring(start,end));
            //更新i 跳到下一次编码的起点
            i=end;
        }
        return res;
    }
}

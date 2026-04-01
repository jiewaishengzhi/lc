package string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class isValid20 {
    public boolean isValid(String s){
        int n=s.length();

        //剪枝 奇数长度一定无效
        if(n%2==1) return false;

        //1.建立配对表 key是右括号 value是对应的左括号
        Map<Character,Character> pairs=new HashMap<>();
        pairs.put(')','(');
        pairs.put(']','[');
        pairs.put('}','{');

        Stack<Character> stack=new Stack<>();

        for(int i=0;i<n;i++){
            char ch=s.charAt(i);

            //2.判断当前字符
            if(pairs.containsKey(ch)){
                //------为右括号
                //此时栈必须非空 且栈顶元素必须是对应的左括号
                if(stack.isEmpty()||stack.peek()!=pairs.get(ch)){
                    return false;
                }
                stack.pop(); //匹配成功 把栈顶的左括号弹出
            }else{
                // -----为右括号
                //直接入栈 等待被匹配
                stack.push(ch);
            }
        }
        //3.最后栈必须非空才算完全匹配
        return stack.isEmpty();
    }

    public boolean isValid2(String s){
        //剪枝 如果是奇数长度 肯定无法配对
        if(s.length()%2!=0) return false;

        Stack<Character> stack=new Stack<>();

        for(char c:s.toCharArray()){
            if(c=='('){
                stack.push(')');
            }else if(c=='['){
                stack.push(']');
            }else if(c=='{'){
                stack.push('}');
            }else{
                //遇到右括号 进行两步判断
                //1.stack.isEmpty() 说明右括号读了
                //2.stack.pop!=c 说明不匹配
                if(stack.isEmpty()||stack.pop()!=c){
                    return false;
                }
            }
        }

        //最后必须栈空才算成功
        return stack.isEmpty();
    }
}

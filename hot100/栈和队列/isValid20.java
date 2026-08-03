package hot100.栈和队列;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class isValid20 {
    public boolean isValid(String s){
        Deque<Character> stack=new ArrayDeque<>();

        for(char c:s.toCharArray()){
            //遇到左括号压入栈
            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            }else{//遇到右括号 检查栈顶
                if(stack.isEmpty()){ //栈顶空 返回false;
                    return false;
                }
                char top=stack.pop();
                if((c==')'&&top!='(')
                ||(c==']'&&top!='[')
                ||c=='}'&&top!='{'){
                    return false;
                }
            }
        }
        //栈为空 说明括号全部匹配
        return stack.isEmpty();
    }

    //用哈希表存括号对
    public boolean isValid2(String s){
        //存入哈希表 key都为左括号 value为右括号
        Map<Character,Character> pairs=Map.of(
                '(',')',
                '[',']',
                '{','}'
        );

        Deque<Character> stack=new ArrayDeque<>();

        for(char c:s.toCharArray()){
            if(!pairs.containsKey(c)){ //左括号压栈
                stack.push(c);
            }else{//右括号判断栈顶元素
                if(stack.isEmpty()||stack.pop()!=pairs.get(c)){ //栈顶非空 且 匹配
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
//2026.7.25 过一遍
//2026.7.29 过一遍 注意栈和哈希表的职责
//2026.8.3 一遍过 右括号判断时忘了栈需要非空
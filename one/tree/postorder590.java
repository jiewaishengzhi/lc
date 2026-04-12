package one.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

// 给定一个 N 叉树的根节点 root ，返回其节点值的 后序遍历 。
// 后序遍历：先递归遍历所有子节点（从左到右），最后访问当前节点
public class postorder590 {
    class Node {
        public int val;
        public List<Node> children;
    }

    //------ 递归 ------

    public List<Integer> postorder(Node root) {
        List<Integer> result=new ArrayList<>();
        dfs(root,result);
        return result;
    }
    private void dfs(Node node,List<Integer> result){
        if(node==null)return;
        for(Node child:node.children){
            //先遍历所有子节点
            dfs(child,result);
        }
        result.add(node.val); //最后处理当前节点
    }


    //------ 迭代：前序变体 + 反转 ------
    // 前序遍历中从左到右压栈子节点，得到"根→右→左"风格序列
    // 反转后即为后序：子节点们 → 根
    public List<Integer> postorder2(Node root) {
        List<Integer> result=new ArrayList<>();
        if(root==null) return result;

        Deque<Node> stack=new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node node=stack.pop();
            result.add(node.val);//先收集
            for(Node child:node.children){
                stack.push(child);//子节点从左到右入栈  弹出时顺序相反
            }
        }
        Collections.reverse(result); //反转即为后序
        return result;
    }
}

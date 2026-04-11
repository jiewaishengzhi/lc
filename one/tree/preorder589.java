package one.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
// 给定一个 N 叉树的根节点 root，返回其节点值的前序遍历。
//  N 叉树的每个节点可以有多个子节点，子节点存储在 children 列表中。
public class preorder589 {
    class Node{
        public int val;
        public List<Node> children;//子节点列表
    }

    //------递归------
    private List<Integer> result=new ArrayList<>();

    public List<Integer> preorder(Node root){
        dfs(root);
        return result;
    }
    private void dfs(Node node){
        if(node==null)return ;
        result.add(node.val);  //先访问当前节点
        for(Node child:node.children){ //从左到右遍历每个节点
            dfs(child);
        }
    }

    // ---- 迭代 ----
    public List<Integer> preorder2(Node root){
        List<Integer> result=new ArrayList<>();
        if(root==null) return result;

        Deque<Node> stack=new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node node=stack.pop();
            result.add(node.val);  //处理当前节点

            //从右往左入栈 保证左边孩子先被弹出
            for(int i=node.children.size()-1;i>=0;i--){
                stack.push(node.children.get(i));
            }
        }
        return result;
    }
}

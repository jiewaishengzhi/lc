package one.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class preorderTraversal144 {
    //----- 递归 -----
    public List<Integer> result=new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root){
        dfs(root);
        return result;
    }
    private void dfs(TreeNode node){
        if(node==null) return;
        result.add(node.val);  //根
        dfs(node.left);  //左
        dfs(node.right); //右
    }

    //----- 迭代 -----  根左右
    public List<Integer> preorderTraversal2(TreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null) return result;
        Deque<TreeNode> stack=new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node=stack.pop();
            result.add(node.val); //处理当前节点

            //右孩子先入栈 后弹出
            if(node.right!=null) stack.push(node.right);
            //左孩子后入栈 后弹出
            if(node.left!=null) stack.push(node.left);
        }
        return result;
    }
}

package one.tree.bianli_carl;

import java.util.*;

public class a01preorderTraversal144 {
    // ----- dfs递归------
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> result=new ArrayList<>();
        dfs(root,result);
        return result;
    }
    private void dfs(TreeNode node,List<Integer> result){
        if(node==null) return;
        //前序 根 左 右
        result.add(node.val);
        dfs(node.left,result);
        dfs(node.right,result);
    }

    // ----- bfs迭代 ----
    public List<Integer> preorderTraversal2(TreeNode root){
        //前序 根 左右
        List<Integer> result=new ArrayList<>();
        if(root==null) return null;

        Deque<TreeNode> stack=new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node =stack.pop();
            result.add(node.val); //处理当前节点

            //入栈 右 左
            if(node.right!=null)stack.push(node.right);
            if(node.left!=null)stack.push(node.left);
        }
        return result;
    }
}

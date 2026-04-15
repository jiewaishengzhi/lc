package one.tree.bianli_carl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class a05inorderTraversal94 {
    // --- 递归 dfs ---
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null)return null;
        dfs(root,result);
        return result;
    }
    public void dfs(TreeNode node,List<Integer> result){
        // 左根右
        dfs(node.left,result);
        result.add(node.val);
        dfs(node.right,result);
    }


    // --- 迭代 bfs ---
    public List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null) return null;

        Deque<TreeNode> stack=new ArrayDeque<>();
        while(root!=null||!stack.isEmpty()){
            //一路向左压栈
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            //弹出栈顶
            root=stack.pop();
            result.add(root.val);

            //转向右子树
            root=root.right;
        }
        return result;
    }
}

package hot100.tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
//二叉树前序遍历  根左右
public class preorderTraversal144 {
    //----- 递归 -----
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> res=new ArrayList<>();
        dfs(root,res);
        return res;
    }
    private void dfs(TreeNode root,List<Integer> res){
        if(root==null)return;
        res.add(root.val);
        dfs(root.left,res);
        dfs(root.right,res);
    }


    //----- 迭代 -----  根左右
    public List<Integer> preorderTraversal2(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        Deque<TreeNode> stack=new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node=stack.pop();
            res.add(node.val);
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
        return res;
    }
}

//2026.7.8 一遍过
//2026.7.10 一遍过
//2026.7.23 一遍过
package one.tree;

import java.util.*;

public class postorderTraversal145 {
    //---- 递归 ----
    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> result=new ArrayList<>();
        dfs(root,result);
        return result;
    }

    private void dfs(TreeNode root,List<Integer> result){
        if(root==null){
            return ;
        }
        dfs(root.left,result);
        dfs(root.right,result);
        result.add(root.val);
    }

    //---- 双栈 迭代 ----
    public List<Integer> postorderTraveral(TreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null)return result;

        //第一个栈负责遍历  第二个栈负责反转顺序
        Deque<TreeNode> stack1=new ArrayDeque<>();
        Deque<TreeNode> stack2=new ArrayDeque<>();

        stack1.push(root);
        while(!stack1.isEmpty()){
            TreeNode node=stack1.pop();
            //stack2 接受 中右左
            stack2.push(node);
            //stack1 压栈顺序 左右    出栈 右左
            if(node.left!=null){
                stack1.push(node.left);
            }
            if(node.right!=null){
                stack1.push(node.right);
            }
        }
        //stack2 弹出 左右中
        while(!stack2.isEmpty()){
            result.add(stack2.pop().val);
        }
        return result;
    }

}

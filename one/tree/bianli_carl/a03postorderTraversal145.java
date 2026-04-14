package one.tree.bianli_carl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class a03postorderTraversal145 {
    //---- dfs递归 ----
    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null)return null;
        dfs(root,result);
        return result;

    }
    private void dfs(TreeNode node,List<Integer> result){
        //后续 左右 根
        if(node==null) return;
        dfs(node.left,result);
        dfs(node.right,result);
        result.add(node.val);
    }

    // ---- bfs迭代 -----
    public List<Integer> postorderTraversal2(TreeNode root){
        //后续 左右中
        List<Integer> result=new ArrayList<>();
        if(root==null)return result;

        //第一个栈负责遍历 第二个栈负责反转
        Deque<TreeNode> stack1=new ArrayDeque<>();
        //stack2 入栈顺序 中右左
        Deque<TreeNode> stack2=new ArrayDeque<>();

        stack1.push(root);

        while(!stack1.isEmpty()){
            TreeNode node=stack1.pop();
            stack2.push(node);
            //出栈要求右左   ->入栈顺序 左右
            if(node.left!=null)stack1.push(node);
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


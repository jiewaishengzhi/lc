package hot100.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
//二叉树后序遍历 左右根
public class postorderTraversal145 {
    //---- 递归 ----
    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res=new ArrayList<>();
        dfs(root,res);
        return res;
    }
    private void dfs(TreeNode root,List<Integer> res){
        if(root==null)return;
        dfs(root.left,res);
        dfs(root.right,res);
        res.add(root.val);

    }


    //---- 双栈 迭代 ----
    //先得到根 右 左   再反过来得到左右根
    public List<Integer> postorderTraveral(TreeNode root){
        List<Integer> res=new ArrayList<>();

        if(root==null)return res;

        Deque<TreeNode> stack1=new ArrayDeque<>();
        Deque<TreeNode> stack2=new ArrayDeque<>();

        stack1.push(root);
        while(!stack1.isEmpty()){
            TreeNode node=stack1.pop();

            //先放入stack2
            stack2.push(node);

            //先压左 再压右  stack1弹出是根右左
            if(node.left!=null){
                stack1.push(node.left);
            }
            if(node.right!=null){
                stack2.push(node.right);
            }
        }
        while (!stack2.isEmpty()){
            res.add(stack2.pop().val);
        }
        return res;
    }

}

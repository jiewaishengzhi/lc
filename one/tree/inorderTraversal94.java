package one.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
// 二叉树中序遍历
public class inorderTraversal94 {
    // ----  递归 -----
    private List<Integer> result=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root){
        dfs(root);
        return result;
    }
    private void dfs(TreeNode node){
        if(node==null) return;
        dfs(node.left);
        result.add(node.val);
        dfs(node.right);
    }

    // -----  迭代 -----
    public List<Integer> inordrTraversal2(TreeNode root){
        List<Integer> result=new ArrayList<>();
        Deque<TreeNode> stack=new ArrayDeque<>();

        while(root!=null||!stack.isEmpty()){
            //一路向左压栈
            while(root!=null){
                stack.push(root);
                root=root.left;
            }

            //弹出栈顶 处理
            root=stack.pop();
            result.add(root.val);

            //转向右子树
            root=root.right;
        }
        return result;
    }
}

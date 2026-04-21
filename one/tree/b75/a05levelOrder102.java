package one.tree.b75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
public class a05levelOrder102 {
    //dfs递归
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res=new ArrayList<>();
        if(root==null)return null;
        dfs(root,0,res);
        return res;
    }
    private void dfs(TreeNode node,int depth,List<List<Integer>> res){
        if(node==null)return;
        if(res.size()==depth){
            res.add(new ArrayList<>());
        }
        //把当前节点放到对应层
        res.get(depth).add(node.val);

        dfs(node.left,depth+1,res);
        dfs(node.right,depth+1,res);
    }

    //bfs迭代层序遍历
    public List<List<Integer>> levelOrder2(TreeNode root){
        List<List<Integer>> res=new ArrayList<>();
        if(root==null) return null;

        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize=queue.size();
            List<Integer> level=new ArrayList<>();

            for(int i=0;i<levelSize;i++){
                TreeNode node=queue.poll();

                level.add(node.val);

                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }
}

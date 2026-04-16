package one.tree.b75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一个二叉树 root ，返回其最大深度。
//二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
public class a01maxDepth104 {
    //dfs递归
    public int maxDepth(TreeNode root){
        List<Integer> res=new ArrayList<>();
        res.add(0);

        if(root==null)return 0;

        dfs(root,1,res);
        return res.get(0);
    }
    private void dfs(TreeNode node,int depth,List<Integer> res){
        if(node==null) return;
        res.set(0,Math.max(res.get(0),depth));
        dfs(node.left,depth+1,res);
        dfs(node.right,depth+1,res);
    }

    //递归
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

    public int maxDepth3(TreeNode root){
        if(root==null)return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int depth=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
            //当前层处理完 深度+1
            depth++;
        }
        return depth;
    }

}

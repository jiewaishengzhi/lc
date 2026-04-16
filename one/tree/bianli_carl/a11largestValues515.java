package one.tree.bianli_carl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
public class a11largestValues515 {
    //----DFS递归----
    public List<Integer> largestValues(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null)return null;
        dfs(root,0,res);
        return res;
    }
    private void dfs(TreeNode node,int depth,List<Integer> result){
        if(node==null)return;

        if(depth==result.size()){
            result.add(node.val);
        }else{
            result.set(depth,Math.max(result.get(depth),node.val));
        }

        dfs(node.left,depth+1,result);
        dfs(node.right,depth+1,result);
    }

    //---BFS迭代---
    public List<Integer> largestValues2(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null)return null;

        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize=queue.size();
            int sum=Integer.MIN_VALUE;

            for(int i=0;i<levelSize;i++){
                TreeNode node=queue.poll();
                sum=Math.max(sum,node.val);

                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
            res.add(sum);
        }
        return res;
    }
}

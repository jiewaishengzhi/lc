package one.tree.bianli_carl;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//给定一个二叉树的根节点root 想象自己站在它的右侧 按照从顶部到底部的顺序 返回从右侧能看到的节点值

//层序遍历取每层最后一个
public class a09rightSideView199 {
    //---- 递归 DFS ----
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null)return null;

        dfs(root,0,res);
        return res;
    }
    private void dfs(TreeNode node,int depth,List<Integer> res){
        if(node==null)return;
        if(depth==res.size()){
            res.add(node.val);
        }
        dfs(node.right,depth+1,res);
        dfs(node.left,depth+1,res);
    }

    //BFS递归
    public List<Integer> rightSideView2(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null)return null;

        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize=queue.size();
            for(int i=0;i<levelSize;i++){
                TreeNode node=queue.poll();
                if(i==levelSize-1){
                    res.add(node.val);
                    if(node.left!=null)queue.offer(node.left);
                    if(node.right!=null)queue.offer(node.right);
                }
            }
        }
        return res;
    }
}

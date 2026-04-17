package one.tree.b75;

import java.util.LinkedList;
import java.util.Queue;

//给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
public class a03invertTree226 {
    public TreeNode invertTree(TreeNode root){
        if(root==null) return null;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    //迭代
    public TreeNode invertTree2(TreeNode root){
        if(root==null)return null;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node =queue.poll();

            TreeNode temp=node.left;
            node.left=root.right;
            root.right=temp;

            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return root;
    }


}

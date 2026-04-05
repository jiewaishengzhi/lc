package one.tree;

import java.util.LinkedList;
import java.util.Queue;

//翻转二叉树
public class invertTree226 {
    //递归
    public TreeNode invertTree(TreeNode root){
        //1.递归终止条件 当前节点为空
        if(root==null){
            return null;
        }
        //2.交换当前节点的左右孩子
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;

        //3.翻转递归左右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    //迭代
    public TreeNode invertTree2(TreeNode root){
        if(root==null)return null;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();

            //交换左右孩子
            TreeNode temp=node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}

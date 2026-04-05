package one.tree;

import java.util.LinkedList;
import java.util.Queue;

/*
求二叉树的最大深度
 */
public class maxDepth104 {
    //DFS递归
    public int maxDepth(TreeNode root){
        //1.递归终止条件 当前节点为空
        if(root==null){
            return 0;
        }
        //2.递归计算左子树的最大深度
        int leftDepth=maxDepth(root.left);
        //3.递归计算右子树的最大深度
        int rightDepth=maxDepth(root.right);
        //4.当前节点的最大深度
        return Math.max(leftDepth,rightDepth)+1;
    }

    //BFS层序遍历
    public int maxDepth2(TreeNode root){
        if(root==null){
            return 0;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int depth=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            //处理当前这一层的所有节点
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            //当前层处理完 深度+1
            depth++;
        }
        return depth;
    }

}

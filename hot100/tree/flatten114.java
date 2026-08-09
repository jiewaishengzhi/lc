package hot100.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class flatten114 {
    //迭代前序遍历
    public void flatten(TreeNode root){
        if(root==null)return;

        Deque<TreeNode> stack=new ArrayDeque<>();
        stack.push(root);
        TreeNode prev=null;
        while(!stack.isEmpty()){
            TreeNode curr=stack.pop();

            if(prev!=null){
                prev.left=null;
                prev.right=curr;
            }
            if(curr.right!=null){
                stack.push(curr.right);
            }
            if(curr.left!=null){
                stack.push(curr.left);
            }
            prev=curr;

        }
    }

    public void flatten2(TreeNode root){
        flattenTree(root);
    }
    private TreeNode flattenTree(TreeNode root){
        // 将root为根的树展开 并返回展开后链表的尾节点
        if(root==null)return null;

        //1.分别展开左右子树
        TreeNode leftTail=flattenTree(root.left);
        TreeNode rightTail=flattenTree(root.right);

        //2.如果有左子树：
            //root的右边接在左边
            //左链表的尾部接在原来的右链表
        if(root.left!=null){
            leftTail.right=root.right;
            root.right=root.left;
            root.left=null;
        }

        //3.返回当前展开链表的尾节点
            // 有右子树时，最终尾节点在右侧
        if(rightTail!=null)return rightTail;
            // 只有左子树时，最终尾节点在左侧
        if(leftTail!=null)return leftTail;
            // 左右都没有 最终尾节点就是自己
        return root;
    }
}
//2026.8.7  第一次写  迭代前序遍历比较简单
//2026.8.9 看了迭代前序遍历  递归不想看

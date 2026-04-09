package one.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class kthSmallest230 {
    //递归中序遍历
    private int count=0;
    private int result=0;
    public int kthSmallest(TreeNode root,int k){
        inorder(root,k);
        return result;

    }
    private void inorder(TreeNode node,int k){
        if(node==null)return;

        inorder(node.left, k);

        count++;
        if(count==k){
            result=node.val;
            return ;//剪枝 不再继续
        }
        inorder(node.right,k);
    }

    //迭代中序遍历
    public int kthSmallest2(TreeNode root,int k){
        //用arrayDeque模拟递归调用栈
        Deque<TreeNode> stack=new ArrayDeque<>();

        while(root!=null||stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }

            root=stack.pop();
            k--;
            if(k==0){
                return root.val;
            }

            root=root.right;
        }
        return -1;

    }

}

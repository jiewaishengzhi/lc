package one.tree.b75;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
public class a10kthSmallest230 {
    // --- 递归中序遍历  ----
    private int count=0;
    private int result=0;
    public int kthSmallest(TreeNode root,int k){
        count=0;
        result=0;

        inorder(root,k);
        return result;
    }

    private void inorder(TreeNode node,int k){
        if(node==null) return;

        inorder(node.left,k);

        count++;
        if(count==k){
            result=node.val;
            return;
        }
        inorder(node.right,k);
    }

    // ---- 迭代中序遍历 ---
    public int kthSmallest2(TreeNode root,int k){
        Deque<TreeNode> stack=new ArrayDeque<>();
        while(!stack.isEmpty()||root!=null){
            //左
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            //根
            root=stack.pop();
            k--;
            if(k==0)return root.val;

            root=root.right;
        }
        return -1;
    }
}

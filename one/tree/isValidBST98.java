package one.tree;
//给定一个二叉树的根节点root 判断其是不是一颗合法的二叉搜索树BST
//BST:左子树所有节点都 小于 当前节点
// 右子树所有节点都 大于 当前节点
// 左右子树也都必须分别是 BST

public class isValidBST98 {
    //递归
    public boolean isValidBST(TreeNode root){
        return dfs(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    private boolean dfs(TreeNode node,long lower,long upper){
        if(node==null){
            return true;
        }
        if(node.val<=lower||node.val>=upper){
            return false;
        }
        return dfs(node.left,lower,node.val)
                &&dfs(node.right,node.val,upper);
    }

    //中序遍历
    private long prev=Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode root){
        return inorder(root);
    }
    private boolean inorder(TreeNode node){
        if(node==null){
            return true;
        }
        if(!inorder(node.left)){
            return false;
        }
        if(node.val<=prev){
            return false;
        }
        prev=node.val;

        return inorder(node.right);
    }
}

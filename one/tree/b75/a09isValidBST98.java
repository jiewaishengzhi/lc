package one.tree.b75;
/*
给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
有效 二叉搜索树定义如下：
节点的左子树只包含 严格小于 当前节点的数。
节点的右子树只包含 严格大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
 */
public class a09isValidBST98 {
    //----- 递归 限制上下限 -----
    public boolean isValidBST(TreeNode root){
        return dfs(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node,long lower,long upper){
        if(node==null) return true;
        if(node.val<=lower||node.val>=upper){
            return false;
        }

        return dfs(node.left,lower,node.val)
                &&dfs(node.right,node.val,upper);
    }

    //---- 递归中序遍历 ----
    private long prev=Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode root){
        return inorder(root);
    }
    private boolean inorder(TreeNode node){
        // 左 根 右
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

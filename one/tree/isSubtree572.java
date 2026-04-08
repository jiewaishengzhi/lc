package one.tree;

public class isSubtree572 {
    public boolean isSubtree(TreeNode root,TreeNode subRoot){
        //空树是任何树的子树
        if(subRoot==null) return true;
        //SubRoot非空 但root为空 不可能匹配
        if(root==null) return false;

        //检查 当前节点开始是否匹配
        return isSame(root,subRoot)
                ||isSubtree(root.left,subRoot)
                ||isSubtree(root.right,subRoot);

    }
    //判断两棵树是否完全相同
    private boolean isSame(TreeNode p,TreeNode q){
        if(p==null&&q==null) return true;
        if(p==null||q==null) return false;

        return p.val==q.val
                &&isSame(p.left,q.left)
                &&isSame(p.right,q.right);
    }
}

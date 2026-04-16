package one.tree.b75;

/*
给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class a02isSameTree100 {
    public boolean isSameTree(TreeNode p,TreeNode q){
        if(p==null&&q==null)return true;
        //其中一个为空 不相同
        if(p==null||q==null)return false;
        //值不相同 不相同
        if(p.val!=q.val)return false;

        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}

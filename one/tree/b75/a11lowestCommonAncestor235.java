package one.tree.b75;
/*
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class a11lowestCommonAncestor235 {
    //----- 递归 ----
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        if(root==null) return null;
        //p q 都在右子树
        if(p.val>root.val&&q.val>root.val){
            return lowestCommonAncestor(root.right,p,q);
        }

        //p q 都在左子树
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        //一左一右 或者其中一个为root
        return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root,TreeNode p,TreeNode q){
        //从根节点开始往下找
        while (root!=null){
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }
            else { //一左一右 或者其中一个为root
                return root;
            }
        }
        return null;
    }
}

package one.tree;
/*
给定一个BST和两个节点p q 找到它们的最近公共祖先
公共祖先 一个节点同时是p和q的祖先（自己是自己的祖先）
最近：离p q最近的那个祖先
 */
public class lowestCommonAncestor235 {
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        //p q都在右子树
        if(p.val>root.val&&q.val> root.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        //p q都在左子树
        if(p.val< root.val&&q.val<root.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        //一左一右 或者其中一个等于root
        return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root,TreeNode p,TreeNode q){
        TreeNode curr=root;
        while(curr!=null){
            if(p.val>curr.val&&q.val>curr.val){
                curr=curr.right;
            }else if(p.val<curr.val&&q.val<curr.val){
                curr=curr.left;
            }else{
                return curr;
            }
        }
        return null;
    }
}

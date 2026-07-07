package hot100.tree;
/*
二叉搜索树的最近公共祖先
1.p.val q.val 都小于当前curr.val 说明p q都在当前节点左子树
2.p.val q.val 都大于当前curr.val 说明p q都在当前右子树
3.一左一右(含相等)  当前节点就是p和q的最近公共祖先
 */
public class lowestCommonAncestor235 {
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        TreeNode curr=root;

        while(curr!=null){
            if(p.val<curr.val&&q.val<curr.val){
                //都小于当前节点 LCA在左子树
                curr=curr.left;
            }else if(p.val>curr.val&&q.val>curr.val){
                curr=curr.right;
            }else{
                //一左一右 当前节点是p或q
                return curr;
            }
        }
        return null; //一定存在 不会走到这里




//        //递归
//        if(root==null) return null;
//
//        if(p.val<root.val&&q.val<root.val){
//            return lowestCommonAncestor(root.left,p,q);
//        }else if(p.val>root.val&&q.val>root.val){
//            return lowestCommonAncestor(root.right,p,q);
//        }else{
//            return root;
//        }
    }
}

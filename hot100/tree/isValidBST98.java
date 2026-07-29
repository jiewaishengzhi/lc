package hot100.tree;

public class isValidBST98 {
    //递归维护上下界
    public boolean isValidBST(TreeNode root){
        return validate(root,Long.MIN_VALUE,Long.MAX_VALUE);

    }
    private boolean validate(TreeNode node,long low,long high){
        if(node==null)return true;

        if(node.val<=low||node.val>=high){
            return false;
        }

        return validate(node.left,low,node.val)&&validate(node.right,node.val,high);
    }
}
//2026.7.25 过一遍
//2026.7.29 递归函数：节点为空 返回true  不满足条件返回false 然后递归判断

package one.tree.b75;
//给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
public class a03invertTree226 {
    public TreeNode invertTree(TreeNode root){
        if(root==null) return null;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

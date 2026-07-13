package hot100.tree;

public class diameterOfBinaryTree243 {
    int diameter=0;
    public int diameterOfBinaryTree(TreeNode root){
        depth(root);
        return diameter;
    }
    private int depth(TreeNode node){
        if(node==null){
            return 0;
        }
        int leftDepth=depth(node.left);
        int rightDpeth=depth(node.right);
        //经过当前节点的最长路径长度=左深度+右深度
        diameter=Math.max(diameter,leftDepth+rightDpeth);

        //返回当前子树的最大深度给父节点
        return Math.max(leftDepth,rightDpeth)+1;
    }
}

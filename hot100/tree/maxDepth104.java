package hot100.tree;

public class maxDepth104 {
    // DFS递归
    public int maxDepth(TreeNode root){
        if(root==null)return 0;
        int left=maxDepth(root.left);
        int right=maxDepth(root.right);

        return Math.max(left,right)+1;
    }

    //BFS迭代直接层序遍历记录层数
}

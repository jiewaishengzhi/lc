package hot100.tree;

public class rob337 {
    /*
    定义递归函数返回 int[] dfs: res[0]:偷当前节点时的最大值  res[1]:不偷当前子树的最大值
    1.偷当前节点rob=node.val+left[1]+right[1]
    2.不偷当前节点notRob=max(left[0],left[1]) + max(right[0],right[1])
     */
    public int rob(TreeNode root){
        int[] res=dfs(root);

        return Math.max(res[0],res[1]);
    }

    /*
    返回长度为 2 的数组：
    result[0]：偷当前节点时的最大金额
    result[1]：不偷当前节点时的最大金额
     */
    private int[] dfs(TreeNode node){
        if(node==null)return new int[]{0,0};

        int[] left=dfs(node.left);
        int[] right=dfs(node.right);

        //偷当前节点
        int robCurrent=node.val+left[1]+right[1];

        //不偷当前节点
        int notRobCurrent=Math.max(left[0],left[1]) + Math.max(right[0],right[1]);

        return new int[]{robCurrent,notRobCurrent};
    }
}

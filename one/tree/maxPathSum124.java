package one.tree;

/*
题目描述
  给你一棵二叉树的根节点 root，返回其最大路径和。
  路径：从树中任意节点出发，沿父节点-子节点连接，到达任意节点的序列。路径至
  少包含一个节点，且不需要经过根节点。
 */
public class maxPathSum124 {
    private int maxSum=Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root){
        dfs(root);
        return maxSum;
    }

    /**
     * 返回以node为起点的最大单链贡献值
     * 同时计算以node为分叉点的路径和 更新maxSum
     * @param node
     * @return
     */
    private int dfs(TreeNode node){
        if(node==null) return 0;

        //递归获取左右子树的贡献值
        //max(0,)表示负贡献值的不要
        int leftGain=Math.max(0,dfs(node.left));
        int rightGain=Math.max(0,dfs(node.right));

        //以当前节点为分叉点的路径和 左+自己+右
        int price=node.val+leftGain+rightGain;
        maxSum=Math.max(maxSum,price);

        //返回向上贡献的值 自己+max(左，右)
        //只能选一条路 不能分叉
        return node.val+Math.max(leftGain,rightGain);
    }
}

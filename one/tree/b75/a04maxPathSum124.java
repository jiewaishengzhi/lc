package one.tree.b75;
/*
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
同一个节点在一条路径序列中至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
路径和是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class a04maxPathSum124 {
    //全局遍历 记录整棵树中的最大路径和
    private int maxSum=Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root){
        dfs(root);
        return maxSum;
    }

    /**
     *
     * @param node
     * @return 从node出发 向下走一条路径能获得的最大路径和
     */
    private int dfs(TreeNode node){
        //空节点对路径没有贡献
        if(node==null)return 0;

        //递归计算左子树能提供的最大贡献 负数丢弃
        int leftGain=Math.max(dfs(node.left),0);

        //递归计算右子树能提供的最大贡献 负数丢弃
        int rightGain=Math.max(dfs(node.right),0);

        //以当前节点作为最高拐点的路径和
        int currentPathSum=node.val+leftGain+rightGain;

        maxSum=Math.max(maxSum,currentPathSum);

        //返回当前节点向父节点能提供的最大贡献值
        return node.val+Math.max(leftGain,rightGain);
    }
}

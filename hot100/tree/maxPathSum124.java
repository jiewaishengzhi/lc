package hot100.tree;

/*
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
路径和 是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和 。

示例 1：
输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6

示例 2：
输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 */
public class maxPathSum124 {
    private int maxSum=Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root){
        dfs(root);
        return maxSum;
    }

    //返回以 node 为根的子树的单侧最大路径和（可以为0，代表不向下延伸）
    private int dfs(TreeNode node){
        if(node==null)return 0;

        //递归计算左右子树的单侧最大贡献 若为负则取0
        int leftGain=Math.max(dfs(node.left),0);
        int rightGain=Math.max(dfs(node.right),0);

        //以当前节点为最高节点的路径和为 当前值+左单侧+右单侧
        int currentPathSum=node.val+leftGain+rightGain;

        //更新全局最大和
        maxSum=Math.max(maxSum,currentPathSum);

        //向上返回单侧最大贡献(只能选一边 或者不选)
        return node.val+Math.max(leftGain,rightGain);
    }
}

//2026.7.10 有点卡 写的出来

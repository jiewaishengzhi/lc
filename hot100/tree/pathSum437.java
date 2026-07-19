package hot100.tree;

import java.util.HashMap;
import java.util.Map;

public class pathSum437 {
    // ------------------- 双递归 -------------------
    public int pathSum(TreeNode root, int targetSum){
        if(root==null){
            return 0;
        }
        //以当前节点为起点的路径数+左子树的路径数+右子树的路径数
        return dfs(root,targetSum)+pathSum(root.left,targetSum)+pathSum(root.right,targetSum);
    }

    //从该起点出发，向下寻找所有可能的路径，计算路径和，统计等于 targetSum 的数量。
    private int dfs(TreeNode node,long targetSum){
        if(node==null)return 0;
        int count=0;
        if(node.val==targetSum){
            count++;
        }
        //向下延申
        count+=dfs(node.left,targetSum-node.val);
        count+=dfs(node.right,targetSum-node.val);
        return count;
    }

    //----------------- 前缀和+哈希表-----------------
    /*
    每个几点的处理：
    1.更新currentSum
    2.查询currentSum-targetSum出现次数
    3.把currentSum放进map
    4.递归左右子树
    5.从map中撤销currentSum
     */
    public int pathSum2(TreeNode root,int targetSum){
        Map<Long,Integer> prefixCount=new HashMap<>();
        prefixCount.put(0L,1);
        return dfs(root,0L,targetSum,prefixCount);
    }
    private int dfs(TreeNode node,long currentSum,int targetSum,Map<Long,Integer> prefixCount){
        if(node==null)return 0;

        //1.加上当前节点值 得到根到当前节点的前缀和
        currentSum+=node.val;

        //2.找到所有以当前节点为结尾的合法路径
        long need=currentSum-targetSum;
        int count=prefixCount.getOrDefault(need,0);

        //3.当前前缀和加入哈希表
        prefixCount.put(currentSum,prefixCount.getOrDefault(currentSum,0)+1);

        //4.分别统计左右子树
        count+=dfs(node.left,currentSum,targetSum,prefixCount);
        count+=dfs(node.right,currentSum,targetSum,prefixCount);

        //5.回溯
        prefixCount.put(currentSum,prefixCount.get(currentSum)-1);

        return count;
    }
}
//2026.7.18 不太会
//2026.7.20 过


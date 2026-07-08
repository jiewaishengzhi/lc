package hot100.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class levelOrder102 {
    //BFS 使用队列逐层处理
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res=new ArrayList<>();

        if(root==null) return res; //空树返回空列表

        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int levelSize=queue.size();
            List<Integer> currentLevel=new ArrayList<>();

            //处理当前层的节点
            for(int i=0;i<levelSize;i++){
                TreeNode node=queue.poll();
                currentLevel.add(node.val);

                //将下一层节点入队
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(currentLevel);
        }
        return res;
    }

    //DFS 递归 传深度
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    private void dfs(TreeNode node,int depth,List<List<Integer>> res){
        if(node==null) return;

        //如果结果列表大小等于当前深度 说明需要新建该层的列表
        if(res.size()==depth){
            res.add(new ArrayList<>());
        }

        //将当前节点值加入对应层列表
        res.get(depth).add(node.val);

        //递归处理左右子树
        dfs(node.left,depth+1,res);
        dfs(node.right,depth+1,res);
    }
}

/*2026.7.8递归迭代都一遍过
学了queue三种实现:ArrayDeque循环数组  LinkedList双向链表  PriorityQueue最大最小堆
 */

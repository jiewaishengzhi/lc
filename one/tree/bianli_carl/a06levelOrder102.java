package one.tree.bianli_carl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class a06levelOrder102 {
    public List<List<Integer>> levelOrder(TreeNode root){
        //BFS 使用队列

        List<List<Integer>> result=new ArrayList<>();
        if(root==null)return null;

        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize=queue.size(); //记录当前节点数量
            List<Integer> level=new ArrayList<>();

            for(int i=0;i<levelSize;i++){
                TreeNode node=queue.poll();
                level.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    //--- 递归层序遍历 ----
    public List<List<Integer>> levelOrder2(TreeNode root){
        List<List<Integer>> res=new ArrayList<>();
        if(root==null)return res;
        dfs(root,0,res);
        return res;
    }
    private void dfs(TreeNode node,int level,List<List<Integer>> res){
        if(node==null)return;
        if(res.size()==level) {//当前层不存在 创建该层的列表
            res.add(new ArrayList<>());
        }
        //把当前节点放入对应层
        res.get(level).add(node.val);

        dfs(node.left,level+1,res);

        dfs(node.right,level+1,res);
    }
}

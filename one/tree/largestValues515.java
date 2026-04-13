package one.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class largestValues515 {
    //DFS递归
    public List<Integer> largestValues515(TreeNode root){
        List<Integer> result=new ArrayList<>();
        dfs(root,0,result);
        return result;
    }

    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if(node==null) return;

        if(depth==result.size()){
            result.add(node.val);
        }else{
            result.set(depth,Math.max(result.get(depth),node.val));
        }

        dfs(node.left,depth+1,result);
        dfs(node.right,depth+1,result);
    }

    //BFS迭代
    public List<Integer> largestValues2(TreeNode root){
        //用来存放每一层的最大值
        List<Integer> result=new ArrayList<>();

        //空树直接返回空列表
        if(root==null){
            return result;
        }

        //队列用于层序遍历
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size=queue.size();

            int maxValue=Integer.MIN_VALUE;

            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                maxValue=Math.max(maxValue,node.val);

                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            result.add(maxValue);
        }
        return result;
    }

}

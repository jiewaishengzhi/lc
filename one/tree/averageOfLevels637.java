package one.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一棵非空二叉树的根节点 root，返回一个数组，表示每一层节点值的平均值。
public class averageOfLevels637 {
    //DFS递归
    public List<Double> averageOfLevels(TreeNode root){
        List<Long> sums=new ArrayList<>();
        List<Integer> counts=new ArrayList<>();

        dfs(root,0,sums,counts);

        List<Double> result=new ArrayList<>();
        for(int i=0;i<sums.size();i++){
            result.add((double)sums.get(i)/counts.get(i));
        }
        return result;
    }
    private void dfs(TreeNode node,int depth,List<Long> sums,List<Integer> counts){
        if(node==null)return;

        //第一次到达这一层
        if(depth==sums.size()){
            sums.add((long)node.val);
            counts.add(1);
        }else{
            sums.set(depth,sums.get(depth)+node.val);
            counts.set(depth,counts.get(depth)+1);
        }
        dfs(node.left,depth+1,sums,counts);
        dfs(node.right, depth + 1, sums, counts);
    }





    //BFS迭代
    public List<Double> averageOfLevels2(TreeNode root){
        List<Double> result=new ArrayList<>();
        if(root==null){
            return result;
        }

        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size=queue.size();
            double sum=0;

            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                sum+=node.val;

                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            result.add(sum/size);
        }
        return result;
    }
}

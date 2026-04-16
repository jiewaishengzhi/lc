package one.tree.bianli_carl;

import com.sun.corba.se.impl.orbutil.LogKeywords;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。
// 与实际答案相差 10^-5 以内的答案可以被接受。
public class a10averageOfLevels637 {
    public List<Double> averageOfLevels(TreeNode root){
        List<Long> sums=new ArrayList<>();
        List<Integer> counts=new ArrayList<>();

        dfs(root,0,sums,counts);
        List<Double> result=new ArrayList<>();
        for(int i=0;i< sums.size();i++){
            result.add((double)sums.get(i)/counts.get(i));
        }
        return result;
    }
    private void dfs(TreeNode node,int depth,List<Long> sums,List<Integer> counts){
        if(node==null)return;

        if(depth==sums.size()){
            sums.add((long)node.val);
            counts.add(1);
        }else{
            sums.set(depth,sums.get(depth)+node.val);
            counts.set(depth,counts.get(depth)+1);
        }
        dfs(node.left,depth+1,sums,counts);
        dfs(node.right,depth+1,sums,counts);
    }


    public List<Double> averageOfLevels2(TreeNode root){
        List<Double> res=new ArrayList<>();
        if(res==null)return null;
        Queue<TreeNode> queue=new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize=queue.size();
            double sum=0;

            for(int i=0;i<levelSize;i++){
                TreeNode node=queue.poll();
                sum+=node.val;

                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
            res.add((double)sum/levelSize);
        }
        return res;
    }
}

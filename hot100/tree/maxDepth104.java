package hot100.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class maxDepth104 {
    // DFS递归
    public int maxDepth(TreeNode root){
        if(root==null)return 0;
        int left=maxDepth(root.left);
        int right=maxDepth(root.right);

        return Math.max(left,right)+1;
    }

    //BFS迭代直接层序遍历记录层数
    public int maxDepth2(TreeNode root){
        Deque<TreeNode> queue=new ArrayDeque<>();
        queue.offer(root);
        int depth=0;

        while(!queue.isEmpty()){
            int levelSize=queue.size();
            for(int i=0;i<levelSize;i++){
                TreeNode node=queue.poll();
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
//2026.8.9 过

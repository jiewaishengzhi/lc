package one.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 给定一个二叉树的 根节点 root，想象自己站在它的右侧，
// 按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
// 本质：每层最右边的节点
public class rightSideView199 {

    //------ BFS 层序遍历：取每层最后一个节点 ------
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        if(root==null)return result;

        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize=queue.size();//当前层节点树
            for(int i=0;i<levelSize;i++){
                TreeNode node= queue.poll();
                //当前层最后一个节点
                if(i==levelSize-1){
                    result.add(node.val);
                }
                //左右孩子入队
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
        }
        return result;
    }

    //------ DFS 递归：根→右→左，每层首次出现的即为最右节点 ------
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        dfs(root,0,result);
        return result;
    }

    /**
     *
     * @param node
     * @param depth
     * @param result
     */
    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if(node==null)return;
        //depth==result.size()说明这一层还没记录过节点
        //先遍历右子树 首次遇到的就是最右的节点
        if(result.size()==depth){
            result.add(node.val);
        }
        dfs(node.right,depth+1,result);
        dfs(node.left,depth+1,result);
        
    }
}

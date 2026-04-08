package one.tree;

import java.util.LinkedList;
import java.util.Queue;

/*
给你两个二叉树 p 和 q，判断它们是不是同一棵树。
这里的“相同”不是只看值一样，而是要同时满足：
对应位置的节点值相同
树的结构也相同
 */
public class isSameTree100 {
    /*
    同时递归比较两棵树的对应节点
    任意两个节点 p 和 q，如果它们想相同，必须满足：
        p.val == q.val
        p.left 和 q.left 相同
        p.right 和 q.right 相同
     */
    public boolean isSameTree(TreeNode p,TreeNode q){
        //1.如果两个节点都为空 说明这两个位置是一样的
        if(p==null&&q==null){
            return true;
        }
        //2.如果一个为空 另一个不为空 说明结构不同
        if(p==null||q==null){
            return false;
        }
        //3.如果两个节点都存在 但值不相同 也不是同一棵树
        if(p.val!=q.val){
            return false;
        }
        //4.当前节点值相同  继续比较左右子书
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

    //迭代
    public boolean isSameTree2(TreeNode p,TreeNode q){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while(!queue.isEmpty()){
            TreeNode node1= queue.poll();
            TreeNode node2= queue.poll();

            if(node1==null&&node2==null){
               continue;
            }
            if (node1 == null || node2 == null) {
                return false;
            }
            if (node1.val != node2.val) {
                return false;
            }

            queue.offer(node1.left);
            queue.offer(node2.left);
            queue.offer(node1.right);
            queue.offer(node2.right);

        }
        return true;
    }
}

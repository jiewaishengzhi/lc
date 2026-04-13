package one.tree;

import java.util.LinkedList;
import java.util.Queue;

/*
给你一棵 完美二叉树 的根节点 root。
完美二叉树的特点是：
每个父节点都有两个孩子
所有叶子节点都在同一层

要求你把每个节点的 next 指针指向它右边的那个节点。
如果右边没有节点，就指向 null。
 */
public class connect116 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }



    //BFS层序遍历
    public Node connect(Node root){
        //空树直接返回
        if(root==null){
            return null;
        }

        //队列用于层序遍历
        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            Node pre=null;

            for(int i=0;i<size;i++){
                Node node=queue.poll();

                if(pre!=null){
                    pre.next=node;
                }
                pre=node;

                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            //当前层最后一个节点的next默认是null
        }
        return root;
    }

    //完美二叉树 node.left.next=node.right  node.right.next=node.next.left
    public Node connect2(Node root){
        //空树直接返回
        if(root==null)return null;

        //leftMost表示当前层的最左边节点
        Node leftMost=root;

        // 因为是完美二叉树
        // 只要当前层还有左孩子，就说明下一层存在
        while(leftMost.left!=null){
            //cur用来横向遍历当前层
            Node cur=leftMost;

            while(cur!=null){
                // 1. 连接同一个父节点的左右孩子
                cur.left.next = cur.right;
                // 2. 连接跨父节点的两个孩子
                //    当前节点的右孩子 -> 右侧相邻节点的左孩子
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                // 在当前层继续向右移动
                cur = cur.next;
            }

            //当前层处理完后 进入下一层
            leftMost=leftMost.left;
        }
        return root;
    }
}

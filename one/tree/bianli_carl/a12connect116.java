package one.tree.bianli_carl;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
初始状态下，所有 next 指针都被设置为 NULL。
 */
public class a12connect116 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }
    }

    //----BFS层序遍历 ----
    public Node connect(Node root){
        if(root==null) return null;
        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            Node pre=null;

            for(int i=0;i<size;i++){
                Node node =queue.poll();
                if(pre!=null){
                    pre.next=node;
                }
                pre=node;

                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
            //每层最后一个节点的next默认是null
        }
        return root;
    }

    //完美二叉树 node.left.next=node.right  node.right.next=node.next.left
    public Node connect2(Node root){
        if(root==null) return null;

        Node leftMost=root;
        while (leftMost.left!=null){
            //cur来横向遍历当前层
            Node cur=leftMost;
            while(cur!=null){
                //1.连接同一个父节点的左右孩子
                cur.left.next=cur.right;
                //2.连接跨父节点的两个孩子
                if(cur.next!=null){
                    cur.right.next=cur.next.left;
                }
                //在当前层继续向右移动
                cur=cur.next;
            }
            leftMost=leftMost.left;
        }
        return root;
    }

}

package one.tree.bianli_carl;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二叉树：
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
初始状态下，所有 next 指针都被设置为 NULL 。
 */
public class a13connect117 {
    class Node{
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }
    }

    // BFS迭代
    public Node conect(Node root){
        if(root==null) return null;

        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            Node pre=null;

            for(int i=0;i<size;i++){
                Node node=queue.poll();
                if(pre!=null){
                    pre.next=node;
                }
                pre=node;

                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
        }
        return root;
    }

    public Node connect2(Node root){
        if(root==null) return null;

        Node curr=root; //curr遍历当前层
        while(curr!=null){
            Node dummy=new Node();
            Node tail=dummy; //tail用于在下一层尾部追加节点

            while(curr!=null){
                //把当前节点的左节点串到tail后面
                if(curr.left!=null){
                    tail.next=curr.left;
                    tail=tail.next;
                }
                //把当前节点的右节点串到tail后面
                if(curr.right!=null){
                    tail.next=curr.right;
                    tail=tail.next;
                }
                //当前节点继续向右
                curr=curr.next;
            }
            //当前层遍历完后 curr跳到下一层
            curr=dummy.next; //dummy.next 即为tail的第一个节点 也就是下一层第一个
        }
        return root;
    }
}

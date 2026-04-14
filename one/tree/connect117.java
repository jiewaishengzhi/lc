package one.tree;

import java.util.LinkedList;
import java.util.Queue;

// 填充每个节点的下一个右侧节点指针 II
// 与116的区别：不是完美二叉树，节点可能只有左孩子或只有右孩子
public class connect117 {
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

    //------ BFS 层序遍历（与116完全一样）------
    public Node connect(Node root) {
        if(root==null)return null;

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
                pre = node;

                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
            //每层最后一个节点的next默认是null

        }
        return root;
    }

    //------ O(1) 空间：用 dummy 虚拟头节点构建下一层的 next 链表 ------
    public Node connect2(Node root) {
        if(root==null)return null;
        Node curr=root; //curr用于遍历当前层

        while(curr!=null){
            Node dummy=new Node(); //虚拟头节点
            Node tail=dummy; //tail用于在 下一层 尾部追加节点

            while(curr!=null){
                //把当前节点的左孩子串到tail后面
                if(curr.left!=null){
                    //改变真实节点
                    tail.next=curr.left;
                    tail=tail.next;
                }
                //把当前节点的右孩子串到tail后面
                if(curr.right!=null){
                    tail.next=curr.right;
                    tail=tail.next;
                }
                //当前层继续向右
                curr=curr.next;
            }
            //当前层遍历完 cur跳到下一层
            curr=dummy.next;
        }
        return root;
    }
}

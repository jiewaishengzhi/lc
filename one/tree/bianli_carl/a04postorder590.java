package one.tree.bianli_carl;

import java.util.*;

public class a04postorder590 {
    class Node{
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<a02preorder589.Node> children) {
            val = _val;
            children = children;
        }
    }
    // ---- 递归 dfs ----
    public List<Integer> postorder(Node root){
        List<Integer> result=new ArrayList<>();
        if(root==null)return null;
        dfs(root,result);
        return result;
    }
    private void dfs(Node node,List<Integer> result){
        //后续 左右根
        if(node==null)return;
        for(Node node1:node.children){
            dfs(node1,result);
        }
        result.add(node.val);
    }

    //  ---- 迭代 bfs 前序反转----
    public List<Integer> postorder2(Node root){
        //需要左右根
        List<Integer> result=new ArrayList<>();
        if(root==null) return null;

        Deque<Node> stack=new ArrayDeque<>();
        stack.push(root);

        //根右左放入
        while (!stack.isEmpty()){
            Node node=stack.pop();
            result.add(node.val);
            for(Node child:node.children){
                stack.push(child);//子节点从左到右入栈  弹出时顺序相反
            }
        }

        Collections.reverse(result);
        return result;

    }


}

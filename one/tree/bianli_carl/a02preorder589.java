package one.tree.bianli_carl;

import java.util.ArrayList;
import java.util.List;

public class a02preorder589 {
    class Node{
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> children) {
            val = _val;
            children = children;
        }
    }
    // ----- dfs递归 -----
    public List<Integer> preorder(Node root){
        List<Integer> result=new ArrayList<>();
        if(root==null) return null;
        return result;
    }
    private void dfs(Node node,List<Integer> result){
        //根 左 右
        if(node==null)return;
        result.add(node.val);
        for(Node node1: node.children){
            dfs(node1,result);
        }
    }

    // ---- bfs迭代 ----

}

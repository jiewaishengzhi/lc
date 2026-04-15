package one.tree.bianli_carl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class a08levelOrder429 {
    public List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> result=new ArrayList<>();
        if(root==null) return null;

        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize=queue.size();
            List<Integer> level=new ArrayList<>();
            for(int i=0;i<levelSize;i++){
                Node node=queue.poll();
                level.add(node.val);
                if(node.children!=null){
                    for(Node child:node.children){
                        queue.offer(child);
                    }
                }
            }
            result.add(level);
        }
        return result;
    }
}

package one.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class levelOrder429 {
    class Node{
        public int val;
        public List<Node> children;
    }
    public List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> result=new ArrayList<>();
        //空树直接返回空
        if(root==null){
            return result;
        }
        //队列用于层序遍历BFS
        //哪个节点先入队 就先出队 符合一层一层从左到右处理的顺序
        Queue<Node> queue=new LinkedList<>();
        queue.offer(root); //先处理根节点

        //只要队列不为空 就继续处理
        while (!queue.isEmpty()){
            int size=queue.size(); //记录循环的该层节点数

            //存当前层的节点值
            List<Integer> level=new ArrayList<>();

            //只处理当前层的size个节点
            for(int i=0;i<size;i++){
                //取出队头节点
                Node node=queue.poll();
                //记录当前节点值
                level.add(node.val);

                // 把当前节点的所有孩子加入队列
                // 这些孩子会在下一轮 while 中作为“下一层”被处理
                if (node.children != null) {
                    for (Node child : node.children) {
                        queue.offer(child);
                    }
                }
            }
            //当前层处理完后 把这层加入最终结果
            result.add(level);
        }
        return result;
    }
}

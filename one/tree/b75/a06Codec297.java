package one.tree.b75;

import java.util.LinkedList;
import java.util.Queue;

public class a06Codec297 {
    private static final String NULL="null";
    private static final String SEP=",";

    /**
     * 序列化 把一棵二叉树转换为字符串 前序遍历 根左右
     * @param root
     * @return
     */
    public String serialize(TreeNode root){
        StringBuilder sb=new StringBuilder();
        serializeDfs(root,sb);

        return sb.toString();
    }
    /**
     * 前序遍历序列化辅助函数
     */
    private void serializeDfs(TreeNode node,StringBuilder sb){
        //如果当前节点为空 用null表示
        if(node==null){
            sb.append(NULL).append(SEP);
            return;
        }
        //1.记录当前节点值
        sb.append(node.val).append(SEP);
        //2.序列化左子树
        serializeDfs(node.left,sb);
        //3.最后序列化右子树
        serializeDfs(node.right,sb);
    }

    public TreeNode deserialize(String data){
        //按逗号切分 得到前序遍历结果
        String[] arr=data.split(SEP);

        //用队列保存节点值 方便从前往后依次取
        Queue<String> queue=new LinkedList<>();

        for(String s:arr)queue.offer(s);

        //按前序顺序重建二叉树
        return deserializeDfs(queue);
    }

    /**
     * 按前序顺序重建二叉树
     * @param queue 前序遍历存的值
     * @return
     */
    private TreeNode deserializeDfs(Queue<String> queue){
        //根
        String val=queue.poll();

        if(val.equals(NULL))return null;

        TreeNode node=new TreeNode(Integer.parseInt(val));

        //前序顺序 依次构造左右子树
        node.left=deserializeDfs(queue);
        node.right=deserializeDfs(queue);

        return node;
    }
}

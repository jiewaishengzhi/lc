package one.tree;
/*
设计一个算法 实现二叉树的序列化 树->字符串
反序列化：字符串->树
 */
public class serialize297 {
    // ----   序列化 树 -> 字符串  ----
    public String serialize(TreeNode root){
        StringBuilder sb=new StringBuilder();
        serializeHelper(root,sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if(node==null){
            sb.append("null,");//空节点用null标记
            return;
        }
        sb.append(node.val).append(","); //根
        serializeHelper(node.left,sb); //左
        serializeHelper(node.right,sb); //右
    }

    // ---- 反序列化 树-> 字符串 ----
    public TreeNode deserialize(String data){
        String[] vals=data.split(",");
        int[] index={0}; //用数组包装 方便递归中修改
        return dfs(vals,index);
    }
    private TreeNode dfs(String[] vals,int[] index){
        String val=vals[index[0]++];

        if (val.equals("null")) {
            return null;
        }

        TreeNode node=new TreeNode(Integer.parseInt(val));
        node.left=dfs(vals,index);
        node.right=dfs(vals,index);
        return node;

    }
}

package one.tree.b75;

import java.util.HashMap;
import java.util.Map;

//根据前序遍历和中序遍历构造二叉树
public class a08buildTree105 {
    //中序遍历  <节点值，下标>
    private Map<Integer,Integer> inorderIndexMap;

    private int preIndex=0; //标识当前要处理的前序遍历元素

    public TreeNode buildTree(int[] preorder,int[] inorder){
        //前序 根 左 右  当前子树的根节点 是前序数组第一个
        //中序 左 根 右  中序数组中根节点左右为左右子树


        //记录中序遍历 每个值对应的下标 <值 下标>
        inorderIndexMap=new HashMap<>();
        for(int i=0;i< inorder.length;i++){
            inorderIndexMap.put(inorder[i],i);
        }

        return build(preorder,0,inorder.length-1);

    }

    /**
     * 用inorder[inLeft,inRight]这一段范围构造当前子树
     * @param preorder
     * @param inLeft
     * @param inRight
     * @return 构造的子树的根节点
     */
    private TreeNode build(int[] preorder,int inLeft,int inRight){
        //1.递归终止条件
        if(inLeft>inRight)return null; //中序区间无效

        //2.前序遍历当前指向的元素 就是当前子树的根节点
        int rootVal=preorder[preIndex];
        preIndex++;

        //3.创建根节点
        TreeNode root=new TreeNode(rootVal);

        //4.在中序遍历中找到根节点位置
        int inRootIndex=inorderIndexMap.get(rootVal);

        //5.递归构造左子树
        root.left=build(preorder,inLeft,inRootIndex-1);
        //6.递归构造右子树
        root.right=build(preorder,inRootIndex+1,inRight);

        //7.返回当前构造好的根节点
        return root;
    }
}

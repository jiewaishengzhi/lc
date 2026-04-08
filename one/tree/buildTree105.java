package one.tree;

import java.util.HashMap;
import java.util.Map;

public class buildTree105 {
    public Map<Integer,Integer> inorderIndexMap;
    private int preIndex=0;
    public TreeNode buildTree(int[] preorder ,int[] inorder){
        //前序 根左右 第一个元素一定是根节点
        //中序 左根右 根结点将数组分为左右两部分

        //构建inorder值 索引的映射 O(1)查找根位置
        inorderIndexMap=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            inorderIndexMap.put(inorder[i],i);
        }
        return build(preorder,0,inorder.length-1);
    }

    /**
     * inleft和inright表示当前子书在inorder中的范围
     * @param preorder
     * @param inLeft
     * @param inRight
     * @return
     */
    private TreeNode build(int[] preorder,int inLeft,int inRight){
        if(inLeft>inRight) return null;
        //前序当前元素作为根
        int rootVal=preorder[preIndex++];
        TreeNode root=new TreeNode(rootVal);

        // 在中序中找到根的位置
        int inRootIdx = inorderIndexMap.get(rootVal);

        // 先递归左子树，再递归右子树（顺序重要！）
        root.left = build(preorder, inLeft, inRootIdx - 1);
        root.right = build(preorder, inRootIdx + 1, inRight);

        return root;
    }
}

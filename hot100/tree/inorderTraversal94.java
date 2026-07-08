package hot100.tree;

/*
核心思路：
用一个指针 curr 从根节点开始，将路径上的所有左子节点依次压栈，直到最左节点。然后弹出栈顶节点（相当于访问根），记录其值，然后将 curr 指向它的右子树，重复上述过程。

步骤：

初始化一个空栈 stack，一个指针 curr = root。

当 curr != null 或栈非空时，循环：
    内层循环：如果 curr != null，就将 curr 压入栈，并让 curr = curr.left，一直向左走到底。
    当 curr == null 时，说明走到了最左端，此时弹出栈顶元素 node = stack.pop()，并访问它（即加入结果集）。
    然后转向右子树：curr = node.right，继续下一轮循环。
循环结束后，返回结果。
这种方法的遍历顺序正好是左—根—右。
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
// 二叉树中序遍历
public class inorderTraversal94 {
    // ----  递归 -----
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res=new ArrayList<>();
        dfs(root,res);
        return res;
    }
    private void dfs(TreeNode node,List<Integer> res){
        if(node==null)return;
        dfs(node.left,res);//左
        res.add(node.val);//根
        dfs(node.right,res);//右
    }


    // -----  迭代 -----
    public List<Integer> inordrTraversal2(TreeNode root){
        List<Integer> res=new ArrayList<>();
        Deque<TreeNode> stack=new ArrayDeque<>();
        TreeNode curr=root;

        while(curr!=null||!stack.isEmpty()){
            //不断向左走 沿途节点压栈
            while (curr!=null){
                stack.push(curr);
                curr=curr.left;
            }
            //此时curr为null 弹出栈顶节点(最左)
            curr=stack.pop();
            res.add(curr.val);
            //转向右子树
            curr=curr.right;
        }
        return res;
    }
}

//2026.7.8 中序遍历迭代卡了下

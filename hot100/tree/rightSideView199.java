package hot100.tree;

import java.util.*;

public class rightSideView199 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val=val;
        }
    }

    public static List<Integer> rightSideView(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null){
            return res;
        }

        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();

                if(i==size-1){
                    res.add(node.val);
                }
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

    public static TreeNode buildTree(Integer[] nums){
        if(nums==null||nums.length==0||nums[0]==null){
            return null;
        }
        TreeNode root=new TreeNode(nums[0]);
        Deque<TreeNode> queue=new ArrayDeque<>();
        queue.offer(root);

        int index=1;
        while(!queue.isEmpty()&&index<nums.length){
            TreeNode node=queue.poll();
            if(index<nums.length&&nums[index]!=null){
                node.left=new TreeNode(nums[index]);
                queue.offer(node.left);
            }
            index++;
            if(index<nums.length&&nums[index]!=null){
                node.right=new TreeNode(nums[index]);
                queue.offer(node.right);
            }
            index++;
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] test1={1,2,3,null,5,null,4};
        TreeNode root1=buildTree(test1);
        List<Integer> res=rightSideView(root1);
        System.out.println(res);
    }
}
//2026.8.18 京东后端开发一面

package one.tree;
//给你一个整数 n，求恰由 n 个节点组成且节点值从 1 到 n
// 互不相同的二叉搜索树（BST）的种数
public class numTrees96 {
    public int numTrees(int n){
        int[] G=new int[n+1];
        G[0]=1; //空树一种
        G[1]=1;//一个节点树一种

        for(int i=2;i<=n;i++){
            //枚举每个节点j作为根
            for(int j=1;j<=i;j++){
                G[i]=G[j-1]*G[i-j];
                //   左子树中枢*右子树种树
            }
        }
        return G[n];

    }
}

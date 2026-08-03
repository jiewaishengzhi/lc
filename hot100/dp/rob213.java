package hot100.dp;
/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈 ，
这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 */
public class rob213 {
    public int rob(int[] nums){
        int n=nums.length;
        if(n==1)return nums[0];

        int max1=robRange(nums,0,n-2);
        int max2=robRange(nums,1,n-1);
        return Math.max(max1,max2);
    }

    private int robRange(int[] nums,int left,int right){
        if(left==right)return nums[left];
        int first=nums[left];
        int second=Math.max(nums[left],nums[left+1]);

        for(int i=left+2;i<=right;i++){
            int temp=second;
            second=Math.max(first+nums[i],second);
            first=temp;
        }
        return second;
    }
}
//2026.7.28 一遍过 泪目 上次是三四月份写的吧
//2026.8.3 一遍过 写的是dp数组 下次可以滚动更新

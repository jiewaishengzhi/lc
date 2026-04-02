package one.dp;

public class rob213 {
    public int rob(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0]; // 只有一个房子，直接偷

        int n=nums.length;

        //核心拆解
        //1. 不偷最后一个 ->范围[0-n-2]
        int max1=robRange(nums,0,n-2);

        //2. 不偷第一个 ->范围[1-n-1]
        int max2=robRange(nums,1,n-1);

        //取两者中的最大值
        return Math.max(max1,max2);
    }

    private int robRange(int[] nums,int start,int end){
        int first=0; //dp[i-2]
        int second=0;  //dp[i-1]

        for(int i=start;i<=end;i++){
            int current=Math.max(second,first+nums[i]);

            first=second;
            second=current;
        }
        return second;
    }
}
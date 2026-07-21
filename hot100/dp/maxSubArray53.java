package hot100.dp;

public class maxSubArray53 {
    //dp[i] 表示以nums[i]为结尾的连续子数组的最大和
    //dp[i]=max(nums[i],dp[i-1]+nums[i])
    public int maxSubArray(int[] nums){
        int n=nums.length;
        int[] dp=new int[n];
        int max=nums[0];
        dp[0]=nums[0];
        for(int i=1;i<n;i++){
            dp[i]=Math.max(nums[i],dp[i-1]+nums[i]);
            max=Math.max(dp[i],max);
        }
        return max;
    }

    public int maxSubArray2(int[] nums){
        int currMax=nums[0];
        int maxSum=nums[0];
        for(int i=1;i<nums.length;i++){
            currMax=Math.max(nums[i],currMax+nums[i]);
            maxSum=Math.max(currMax,maxSum);
        }
        return maxSum;
    }
}
// 2026.7.21 过

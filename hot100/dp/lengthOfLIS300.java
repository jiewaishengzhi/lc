package hot100.dp;

public class lengthOfLIS300 {
    /*动态规划
    dp[i]表示以nums[i]为结尾的最长递增子序列的长度 初始状态dp[i]=1
    对于每个i, 遍历j从0到i-1 若nums[j]<nums[i] 则nums[i]可以接在nums[j]后面
    更新dp[i]=Math.max(dp[i],dp[j]+1);
     */
    public int lengthOfLIS(int[] nums){
        if(nums==null||nums.length==0)return 0;
        int n=nums.length;
        int[] dp=new int[n];
        int maxLen=1;

        for(int  i=0;i<n;i++){
            dp[i]=1; //自身长度为1
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxLen=Math.max(dp[i],maxLen);
        }
        return maxLen;
    }

    /*贪心+二分
    维护一个数组 tails，其中 tails[k] 表示当前找到的长度为 k+1 的递增子序列的最小末尾元素。
    遍历 nums 中的每个数字 num：如果 num 大于 tails 中所有元素，则将其追加到 tails 末尾，相当于找到了一条更长的递增子序列。
    否则，在 tails 中二分查找第一个 大于等于 num 的位置，并用 num 替换该位置的值。这样做的目的是让该长度的子序列末尾尽可能小，为后续延长创造更多机会。
     */
    public int lengthOfLIS2(int[] nums){
        if(nums==null||nums.length==0)return 0;
        int n=nums.length;
        int[] tails=new int[n];
        int size=0; //tails的有效长度

        for(int num:nums){
            int left=0,right=size; //左闭右开[left,right)
            //二分查找第一个>=num的位置
            while(left<right){
                int mid=left+(right-left)/2;
                if(tails[mid]<num){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
            tails[left]=num; //替换或追加
            if(left==size)size++; //追加的时候 长度+1
        }
        return size;
    }
}

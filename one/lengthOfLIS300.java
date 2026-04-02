import java.util.Arrays;

public class lengthOfLIS300 {
    public int lengthOfLIS(int[] nums){
        int n=nums.length;
        if(n==0)return 0;

        //dp[i]表示以nums[i]结尾的最长递增子序列长度
        int[] dp=new int[n];
        //初始化 每个数字本身至少可以构成长度为1的子序列
        Arrays.fill(dp,1);

        int maxAns=1;

        for(int i=1;i<n;i++){
            //往回找 看看能接在谁后面
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    // 如果能接上，就尝试更新最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //记录全局最大值
            maxAns=Math.max(maxAns,dp[i]);
        }
        return maxAns;
    }

    public int lengthOfLIS2(int[] nums){
        //tails[i]存储的长度为i+1的递增子序列的最小末尾值
        int[] tails=new int[nums.length];
        //res是当前tails数组的有效长度 也是目前找到的最长子序列长度
        int res=0;

        for(int num:nums){
            //二分查找 在tails[0...res-1]中找第一个>=nums的位置
            int i=0,j=res;
            while(i<j){
                int mid=i+(j-1)/2;
                if(tails[mid]<num){
                    i=mid+1;
                }else{
                    j=mid;
                }
            }

            //如果i==res 说明num比tails里的所有数都大
            //这是一个新的最长序列的结尾
            if(i==res){
                tails[res]=num;
                res++;
            }else{
                //否则 用num替换掉tails[i]
                //含义 长度为i+1的子序列 现在有了一个更小的结尾num
                tails[i]=num;
            }
        }
        return res;
    }
}

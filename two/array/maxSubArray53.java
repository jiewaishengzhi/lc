package two.array;

public class maxSubArray53 {
    public int maxSubArray(int[] nums){
        int n=nums.length;
        //初始化最大值为极小值 防止结果全为负数
        int maxAns=Integer.MIN_VALUE;

        //第一层循环 起点i
        for(int i=0;i<n;i++){
            int currentSum=0;
            for(int j=i;j<n;j++){
                currentSum+=nums[j];
                maxAns=Math.max(maxAns,currentSum);
            }
        }
        return maxAns;
    }

    //动态规划
    public int maxSubArray2(int[] nums){
        int pre=0;//记录以当前数字结尾的最大子数和
        int maxAns=nums[0];//记录全局最大和

        for(int x:nums){
            // 核心决策：
            // 如果 pre > 0，则 pre + x 会更大 -> 带上之前的
            // 如果 pre <= 0，则 x 本身会更大 -> 扔掉之前的，从 x 重新开始
            pre=Math.max(pre+x,x);

            maxAns=Math.max(pre,maxAns);
        }
        return maxAns;
    }

}

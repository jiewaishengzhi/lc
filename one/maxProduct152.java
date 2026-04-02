public class maxProduct152 {
    public int maxProduct(int[] nums){
        //1.初始化
        //maxF 以当前元素结尾的最大乘积
        //maxf 以当前元素结尾的最小乘积
        int maxF=nums[0];
        int minF=nums[0];
        //ans: 全局最大值
        int ans=nums[0];

        //2.从第二个元素开始遍历
        for(int i=1;i<nums.length;i++){
            // 重点：由于计算 maxF 时会改变 maxF 的值，
            // 而计算 minF 时还需要用到旧的 maxF，所以必须先存起来
            int mx=maxF;
            int mn=minF;

            //3.核心状态转移
            // 当前的最大值 = max(当前值, 当前值*旧最大, 当前值*旧最小)
            maxF=Math.max(nums[i],Math.max(nums[i] * mx, nums[i] * mn));

            //当前的最小值 = min(当前值, 当前值*旧最大, 当前值*旧最小)
            minF=Math.min(nums[i], Math.min(nums[i] * mx, nums[i] * mn));

            //更新全局结果
            ans=Math.max(ans,maxF);
        }
        return ans;
    }
}

public class canJump55 {
    public boolean canJump(int[] nums){
        //maxReach表示当前能跳到的最远位置
        int maxReach=0;
        int n=nums.length;

        for(int i=0;i<n;i++){
            //1.如果当前位置i已经超过了最远能到达的位置
            //说明中间断了 过不来 失败
            if(i>maxReach){
                return false;
            }

            //2.更新最远覆盖范围
            // 在位置 i，我能跳 nums[i] 步，所以最远能到 i + nums[i]
            // 我们取之前的 maxReach 和现在算出来的最大值
            maxReach=Math.max(maxReach,i+nums[i]);

            //3. 剪枝优化：如果覆盖范围已经到了终点，提前结束
            if(maxReach>=n-1){
                return true;
            }
        }
        return false;
    }
}

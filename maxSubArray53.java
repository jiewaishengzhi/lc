public class maxSubArray53 {
    public int maxSubArray(int[] nums){
        int n=nums.length;
        //初始化最大值为极小值 防止结果全是负数
        int maxAns=Integer.MIN_VALUE;

        //第一层循环 起点i
        for(int i=0;i<n;i++){
            int currentSum=0;

            //2.第二层循环 终点j
            // j 从 i 开始，表示子数组长度至少为 1
            for(int j=i;j<n;j++){
                currentSum+=nums[j];
                // 更新最大值
                maxAns=Math.max(maxAns,currentSum);
            }
        }
        return maxAns;
    }

    public int maxSubArray2(int[] nums){
        //pre记录以当前数字结尾的最大子数组和
        int pre=0;
        //maxAns记录全局最大和
        int maxAns=nums[0];

        for(int x:nums){
            // 核心决策：
            // 如果 pre > 0，则 pre + x 会更大 -> 带上之前的
            // 如果 pre <= 0，则 x 本身会更大 -> 扔掉之前的，从 x 重新开始
            pre=Math.max(pre+x,x);

            maxAns=Math.max(pre,maxAns);
        }
        return maxAns;
    }

    public int maxSubArray3(int[] nums){
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    //分治主函数
    private int divideAndConquer(int[] nums,int left,int right){
        //递归终止条件 只有一个元素时 最大和就是它自己
        if(left==right){
            return nums[left];
        }

        int mid=left+(right-left)/2;

        //1.递归计算左半边的最大子数组和
        int leftMax=divideAndConquer(nums,left,mid);

        //2.递归计算右半边最大子数组和
        int rightMax=divideAndConquer(nums,mid+1,right);

        //3.计算横跨中间的最大子数组和（分治的重点）
        int crossMax=getCrossMax(nums,left,right,mid);

        //4.返回三者中的最大值
        return Math.max(leftMax, Math.max(rightMax,crossMax));

    }

    private int getCrossMax(int[] nums,int left,int right,int mid){
        // ---向左扫描---
        int leftSum=0;
        int maxLeftSum=Integer.MIN_VALUE;

        //从mid开始倒着往左边加
        for(int i=mid;i>=left;i--){
            leftSum+=nums[i];
            maxLeftSum=Math.max(maxLeftSum,leftSum);
        }

        // ---向右扫描
        int rightSum=0;
        int maxRightSum=Integer.MIN_VALUE;

        //从mid+1开始往右加
        for(int i=mid+1;i<=right;i++){
            rightSum+=nums[i];
            maxRightSum=Math.max(maxRightSum,rightSum);
        }

        return maxLeftSum+maxRightSum;

    }


}

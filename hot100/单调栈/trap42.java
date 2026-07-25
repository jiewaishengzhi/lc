package hot100.单调栈;

public class trap42 {
    public int trap(int[] height){
        int n=height.length;

        //少于3根柱子 不可能形成凹槽接水
        if(n<3)return 0;

        //leftMax[i] 从下标0到i之间最高的柱子
        int[] leftMax=new int[n];
        //rightMax[i] 从下标i到n-1之间最高的柱子
        int[] rightMax=new int[n];

        leftMax[0]=height[0];
        //从左往右计算每个位置左边最高的柱子
        for(int i=1;i<n;i++){
            leftMax[i]=Math.max(leftMax[i-1],height[i]);
        }

        rightMax[n-1]=height[n-1];
        //从右往左计算每个位置右边的最高柱子
        for(int i=n-2;i>=0;i--){
            rightMax[i]=Math.max(rightMax[i+1],height[i]);
        }

        int water=0;

        //对每个位置计算能够接住的雨水
        for(int i=0;i<n;i++){
            water+= Math.min(leftMax[i],rightMax[i])-height[i];
        }
        return water;
    }

    //双指针
    public int trap2(int[] height){
        if(height==null||height.length<3)return 0;

        int left=0;
        int right=height.length-1;

        int leftMax=0;
        int rightMax=0;

        int water=0;

        while(left<right){
            leftMax=Math.max(leftMax,height[left]);
            rightMax=Math.max(rightMax,height[right]);

            // 左侧最高墙更低，left 位置的水量由 leftMax 决定
            if(leftMax<rightMax){
                water+=leftMax-height[left];
                left++;
            }else{
                // 右侧最高墙更低，right 位置的水量由 rightMax 决定
                water+=rightMax-height[right];
                right--;
            }
        }
        return water;
    }
}

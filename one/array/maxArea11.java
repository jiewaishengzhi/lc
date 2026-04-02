package one.array;

public class maxArea11 {
    public int maxArea(int[] height){
        //1.定义双指针
        int left=0;
        int right=height.length-1;

        int maxAns=0;

        //2.向中间靠拢
        while(left<=right){
            //计算当前木桶短板的高度
            int minH=Math.min(height[left],height[right]);

            //计算当前面积 宽*高
            int currentArea=(right-left)*minH;

            //更新历史最大值
            maxAns=Math.max(maxAns,currentArea);

            //3.核心决策 移动短处的指针
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxAns;
    }
}
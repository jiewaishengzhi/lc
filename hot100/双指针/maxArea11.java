package hot100.双指针;

public class maxArea11 {
    public int maxArea(int[] height){
        int n=height.length;

        int left=0;
        int right=n-1;
        int max=0;
        while(left<right){
            int minHeight=Math.min(height[left],height[right]);
            int currentArea=(right-left)*minHeight;
            max=Math.max(currentArea,max);
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }
}
//2026.8.4 第一次一遍过
//2026.8.8 过 minH*(right-left)  第一次多 +1

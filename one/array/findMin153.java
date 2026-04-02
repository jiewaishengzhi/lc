package one.array;

public class findMin153 {
    public int findMin(int[] nums){
        if(nums.length==1) return nums[0];
        int count=0;
        while(nums[0]>nums[nums.length-1]){
            nums=f(nums);
            count++;
        }
        return nums[0];
    }
    public int[] f(int[] nums){
        int[] res=new int[nums.length];
        for(int i=0;i<nums.length-1;i++){
            res[i+1]=nums[i];
        }
        res[0]=nums[nums.length-1];

        return res;
    }

    public int findMin2(int[] nums){
        int left=0;
        int right=nums.length-1;

        // 循环条件 left < right
        // 当 left == right 时，我们就找到了最小值，循环结束
        while(left<right){
            int mid=left+(left+right)/2;

            if (nums[mid] < nums[right]) {
                // 情况 A：右边有序，最小值在左边（或者就是 mid）
                // 此时 mid 可能是最小值，所以不能排除 mid
                right = mid;
            } else {
                // 情况 B：mid 比 right 大，说明 mid 在“高坡”上
                // 最小值一定在 mid 的右边
                // 此时 mid 肯定不是最小值，可以排除
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
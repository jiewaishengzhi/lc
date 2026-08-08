package hot100.二分查找;

public class searchInsert35 {
    public int searchInsert(int[] nums, int target){
        //左闭右开 [left,right)
        int left=0;
        int right=nums.length;

        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left;
    }

    public int searchInsert2(int[] nums, int target){
        //左闭右闭 [left,right]
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return left;
    }
}
/*
[left,right)  left<right  mid=right
[left,right]  left<=right  mid=right-1
 */

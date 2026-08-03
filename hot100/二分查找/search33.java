package hot100.二分查找;

public class search33 {
    /*
    旋转后只有一个断点，除了断点处，其余相邻元素是递增的  把当前区间拆成两半，断点在其中一半，所以另外一半是有序的
    1. 计算 mid
    2. 若 nums[mid] == target，直接返回
    3. 判断左边或右边，哪一半是有序的
    4. 判断 target 是否位于“有序的那一半”
        - 在：进入这半边
        - 不在：去另一半
     */
    public int search(int[] nums, int target){
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;

            if(nums[mid]==target){
                return mid;
            }

            //左半部分[left,mid]有序
            if(nums[left]<=nums[mid]){
                //target是否在左侧有序区间中
                if(nums[left]<=target && target<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }
            //右半部分[mid,right]有序
            else{
                //target是否在右侧有序区间中
                if(nums[mid]<target && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }
}
//2026.7.30 第一遍
//2026.8.2 一遍过

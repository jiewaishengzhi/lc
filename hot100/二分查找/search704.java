package hot100.二分查找;

public class search704 {
    public int search(int[] nums,int target){
        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid=left+(right-left)/2;
            int num=nums[mid];
            if(num==target){
                return mid;
            }else if(num<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return -1;
    }
}
//2026.8.2 一遍过

public class search33 {
    public int search(int[] nums,int target){
        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid=left+(left+right)/2;

            //1.nums[mid]直接为target
            if(nums[mid]==target){
                return mid;
            }

            //2.判断哪一半是有序的
            if(nums[left]<=nums[mid]){
                //  ---左半边[left...mid]是有序的---

                //查target是不是在左边
                if(nums[left]<=target && target<nums[mid]){
                    //target在左半边
                    right=mid-1;
                }else{
                    //target不在左边
                    left=mid+1;
                }
            }else{
                // --- 左半边无序 那右半边[mid...right]必定是有序的 ---

                //查target是不是在右边
                if(nums[mid]<=target &&target<=nums[right]){
                    //target在右边
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        //没找到
        return -1;

    }
}

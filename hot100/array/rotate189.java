package hot100.array;

import java.util.ArrayList;

public class rotate189 {
    public static void rotate(int[] nums, int k) {
        k=k%nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    private static void reverse(int[] nums,int left,int right){
        while(left<right){
            int temp=nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4,5,6,7};
        rotate(nums,3);
        System.out.print('[');
        for(int i=0;i<nums.length-1;i++){
            System.out.print(nums[i]);
            System.out.print(',');
        }
        System.out.print(nums[nums.length-1]);
        System.out.println(']');
    }
}

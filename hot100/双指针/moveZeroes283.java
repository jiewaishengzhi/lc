package hot100.双指针;

import java.util.PriorityQueue;

public class moveZeroes283 {
    public static void moverZeroes(int[] nums){
        //左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
        int slow=0;

        for(int fast=0;fast<nums.length;fast++){
            if(nums[fast]!=0){
                int temp=nums[fast];
                nums[fast]=nums[slow];
                nums[slow]=temp;

                slow++;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums1=new int[]{0,1,0,3,12};
        moverZeroes(nums1);
        print(nums1);

        int[] nums2=new int[]{0};
        moverZeroes(nums2);
        print(nums2);
    }

    private static void print(int[] nums){
        System.out.print("[");
        for(int i=0;i<nums.length-1;i++){
            System.out.print(nums[i]);
            System.out.print(",");
        }
        System.out.print(nums[nums.length-1]);
        System.out.println("]");
    }
}
//2026.8.14 左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部 遇到非0就和左指针位置数交换 左指针前移
//2026.8.16 过
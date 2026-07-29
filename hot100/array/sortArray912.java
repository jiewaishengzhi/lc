package hot100.array;

import java.util.Random;
/*
给你一个整数数组 nums，请你将该数组升序排列。

你必须在 不使用任何内置函数 的情况下解决问题，时间复杂度为 O(nlog(n))，并且空间复杂度尽可能小。
 */
public class sortArray912 {

    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    //------------------------Lomuto partition单指针-------------------

    private void quickSort(int[] nums,int left,int right){
        if(left>=right)return;
        int pivot=partition(nums,left,right);
        quickSort(nums,left,pivot-1);
        quickSort(nums,pivot+1,right);
    }

    /*寻找基准元素
    选 pivot
    把 pivot 放到最右边
    用 storeIndex 维护“小于 pivot 区域”的边界
    最后把 pivot 放回最终位置
    返回 pivot 最终下标
 */
    private int partition(int[] nums,int left,int right){
        Random random=new Random();
        int randomIndex=left+random.nextInt(right-left+1);
        swap(nums,randomIndex,right);

        int pivot=nums[right];
        int i=left;  //i指向第一个大于pivot的位置
        for(int j=left;j<right;j++){
            if(nums[j]<pivot){
                swap(nums,i,j);
                i++;
            }
        }
        swap(nums,i,right);
        return i;
    }

    private void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

//------------------------Hoare partition双指针-----------------

    public void quickSort2(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        _quickSort2(nums, 0, nums.length - 1);
    }

    private void _quickSort2(int[] nums, int left, int right) {
        if (left >= right) return;

        int boundary = partition2(nums, left, right);

        // Hoare 返回的是分界点，不是 pivot 最终位置
        _quickSort2(nums, left, boundary);
        _quickSort2(nums, boundary + 1, right);
    }
/*
双指针 i、j
i 从左往右找 >= pivot 的数
j 从右往左找 <= pivot 的数
如果 i < j，就交换
如果 i >= j，返回 j
 */
    private int partition2(int[] nums,int left,int right){
        Random random=new Random();
        int randomIndex=left+random.nextInt(right-left+1);
        int pivot=nums[randomIndex]; //面试手撕可以先换到left

        int i=left-1;
        int j=right+1;
        while (true){
            do{
                i++;
            }while(nums[i]<pivot);
            do{
                j--;
            }while(nums[j]>pivot);

            if(i>=j){
                return j; //j为左区间的最后一个位置
            }
            swap(nums,i,j);
        }
    }
}

//2026.7.29 过一下

package hot100.array;

import java.util.Random;

public class quickSort {
// ------------------------Lomuto partition单指针-------------------
    private final Random random=new Random();
    public void quickSort1(int[] nums){
        if(nums==null||nums.length<=1)return;
        _quickSort1(nums,0,nums.length-1);

    }
    private void _quickSort1(int[] nums,int left,int right){
        if(left>=right)return;
        int pivotIndex=partition1(nums,left,right);
        // Lomuto 返回的是 pivot 最终位置，所以 pivotIndex 可以排除
        _quickSort1(nums,left,pivotIndex-1);
        _quickSort1(nums,pivotIndex+1,right);

    }
    /*
    选 pivot
    把 pivot 放到最右边
    用 storeIndex 维护“小于 pivot 区域”的边界
    最后把 pivot 放回最终位置
    返回 pivot 最终下标
 */
    private int partition1(int[] nums,int left,int right){
        //随机选一个pivot 把它放到最右边
        int randomIndex=left+random.nextInt(right-left+1);
        swap(nums,randomIndex,right);

        int pivot=nums[right];
        int storeIndex=left; //storeIndex维护小于pivot的边界
        for(int i=left;i<right;i++){
            if(nums[i]<pivot){
                swap(nums,storeIndex,i);
                storeIndex++;
            }
        }
        //把pivot放到最终位置
        swap(nums,storeIndex,right);
        return storeIndex;
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


    private void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}

package hot100.array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/*
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4

提示：
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
 */
public class findKthLargest215 {

    private final Random random=new Random();
    public int findKthLargest(int[] nums,int k){
//        //一.---------排序后取k大-----------
//        Arrays.sort(nums);
//        return nums[nums.length-k];

        // 二. ------------最小根堆-------------
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();  //默认小顶堆  大根：PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for(int num:nums){
            minHeap.offer(num);
            if(minHeap.size()>k){
                minHeap.poll(); //移除最小的 保持堆大小为k
            }
        }
        return minHeap.peek();
    }


    //三. -------------快排-------------
    //第k大 即为有序数组索引为n-k的数字

    public int findKthLargest2(int[] nums,int k){
        return quickSelect(nums,0,nums.length-1,nums.length-k);
    }

    private int quickSelect(int[] nums,int left,int right,int targetIndex){
        if(left==right)return nums[left];

        int pivotIndex=partition(nums,left,right);
        if(pivotIndex==targetIndex){
            return nums[pivotIndex];
        }else if(pivotIndex<targetIndex){
            return quickSelect(nums,pivotIndex+1,right,targetIndex);
        }else{
            return quickSelect(nums,left,pivotIndex-1,targetIndex);
        }
    }



//    private void quickSort(int[] nums,int left,int right){
//        if(left>=right) return;
//
//        int pivotIndex=partition(nums,left,right);
//        quickSort(nums,left,pivotIndex-1);
//        quickSort(nums,pivotIndex+1,right);
//    }


    //划分函数 选一个基准，划分后左侧均小于基准 右侧均大于基准 返回基准的正确下标
    private int partition(int[] nums,int left,int right){
        //随机化 随机选一个元素与最右侧元素交换 避免最坏情况

        int randomIndex=left+random.nextInt(right-left+1);
        swap(nums,randomIndex,right);

        int pivot=nums[right];
        int i=left; //i指向第一个大于pivot的位置

        for(int j=left;j<right;j++){
            if(nums[j]<=pivot){
                swap(nums,i,j);
                i++;
            }
        }
        swap(nums,i,right);
        return i;
    }

    private int quickSelect2(int[] nums, int left, int right, int targetIndex) {
        if (left == right) return nums[left];

        int pivotIndex = partition2(nums, left, right);

        if (targetIndex <= pivotIndex) {
            return quickSelect2(nums, left, pivotIndex, targetIndex);
        } else {
            return quickSelect2(nums, pivotIndex + 1, right, targetIndex);
        }
    }
    //划分函数 双指针优化  返回的是分界点 不是最终位置
    private int partition2(int[] nums,int left,int right){
        int randomIndex=left+random.nextInt(right-left+1);
        int pivot=nums[randomIndex];

        int i=left-1;
        int j=right+1;
        while(true){
            do {
                i++;
            }while(nums[i]<pivot);

            do{
                j--;
            }while(nums[j]>pivot);

            //如果指针相遇或者交错 划分完成
            if(i>=j)return j;
            swap(nums,i,j);
        }


    }

    private void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

}

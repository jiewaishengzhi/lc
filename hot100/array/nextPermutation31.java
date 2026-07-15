package hot100.array;
/*
整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。

必须 原地 修改，只允许使用额外常数空间。

示例 1：
输入：nums = [1,2,3]
输出：[1,3,2]

示例 2：
输入：nums = [3,2,1]
输出：[1,2,3]
示例 3：

输入：nums = [1,1,5]
输出：[1,5,1]
 */
public class nextPermutation31 {
    public  void nextPermutation(int [] nums) {
        int n=nums.length;
        
        //1.从右向左找到第一个下降点i
        int i=n-2;
        while(i>=0&&nums[i]>=nums[i+1]){ //取等于是排除的
            i--;
        }
        //如果i>=0 说明存在更大的排列
        if(i>=0){
            //2.在i右侧从右向左找第一个大于num[i]的数
            int j=n-1;
            while(j>i&&nums[j]<=nums[i]){
                j--;
            }
            //交换nums[i] nums[j]
            swap(nums,i,j);
        }

        //3.反转i+1到末尾 (包含i=-1)
        reverse(nums,i+1,n-1);

    }
    
    public void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    
    public void reverse(int[] nums,int left,int right){
        while (left<right){
            swap(nums,left,right);
            left++;
            right--;
        }
    }
}
/*2026.7.11
1.从右往左找到第一个降序的位置i
2.在i右边找到第一个大于nums[i]的位置j
3.将i,j位置互换
4.翻转i+1到末尾
 */

//2026.7.15 过

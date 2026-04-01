package two.array;
/*
给你一个整数数组 nums，返回 数组 answer ，
其中 nswer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。
题目数据保证数组 nums之中任意元素的全部前缀元素和后缀的乘积都在32位整数范围内。
请不要使用除法，且在 O(n) 时间复杂度内完成此题。

输入: nums = [1,2,3,4]输出: [24,12,8,6]
 */
public class productExceptionSelf238 {
    public int[] productExceptionSelf(int[] nums){
        int n=nums.length;
        //1.定义两个辅助函数
        int[] L=new int[n];//存前缀积
        int[] R=new int[n];//存后缀积
        int[] arr=new int[n];

        //2.填充左侧列表L L[i]为索引i左侧所有元素的乘积
        L[0]=1;
        for(int i=1;i<n;i++){
            L[i]=L[i-1]*nums[i-1];
        }

        //3.填充右侧列表R R[i]为索引i右侧所有元素的乘积
        R[n-1]=1;
        for(int i=n-2;i>=0;i--){
            R[i]=R[i+1]*nums[i+1];
        }

        //4.最终计算
        for(int i=0;i<n;i++){
            arr[i]=L[i]*R[i];
        }
        return arr;
    }

    public int[] productExceptSelf2(int[] nums){
        int n=nums.length;
        int []answer=new int[n];

        answer[0]=1;
        for(int i=1;i<n;i++){
            answer[i]=answer[i-1]*nums[i-1];

        }
        int R=1;
        for(int i=n-1;i>=0;i--){
            answer[i]=answer[i]*R;
            R=R*nums[i];
        }
        return answer;
    }
}

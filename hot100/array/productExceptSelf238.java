package hot100.array;
/*
给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。
题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
请 不要使用除法，且在 O(n) 时间复杂度内完成此题。

示例 1:
输入: nums = [1,2,3,4]
输出: [24,12,8,6]

示例 2:
输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]

提示：
2 <= nums.length <= 105
-30 <= nums[i] <= 30
输入 保证 数组 answer[i] 在  32 位 整数范围内

进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 */
public class productExceptSelf238 {
    public int[] productExceptSelf(int[] nums){
        int n=nums.length;
        int[] left=new int[n];
        int[] right=new int[n];
        int[] res=new int[n];

        left[0]=1;
        for(int i=1;i<n;i++){
            left[i]=nums[i-1]*left[i-1];
        }

        right[n-1]=1;
        for(int i=n-2;i>=0;i--){
            right[i]=right[i+1]*nums[i+1];
        }

        for(int i=0;i<n;i++){
            res[i]=left[i]*right[i];
        }

        return res;
    }

    public int[] productExceptSelf2(int[] nums){
        int n=nums.length;
        int[] res=new int[n];

        res[0]=1;
        for(int i=1;i<n;i++){
            res[i]=res[i-1]*nums[i-1];
        }

        int r=1; //记录右缀积
        for(int i=n-1;i>=0;i--){
            res[i]=res[i]*r;
            r=r*nums[i];
        }

        return res;
    }
}
//2026.7.12 小卡

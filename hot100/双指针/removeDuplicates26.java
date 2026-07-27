package hot100.双指针;
/*
给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，
返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。

考虑 nums 的唯一元素的数量为 k。去重后，返回唯一元素的数量 k。

nums 的前 k 个元素应包含 排序后 的唯一数字。下标 k - 1 之后的剩余元素可以忽略。
 */
public class removeDuplicates26 {
    //快慢双指针 slow指向当前已去重部分的最后一个元素  fast遍历数组，寻找不重复元素
    public int removeDuplicates(int[] nums){
        if(nums.length==0) return 0;

        //slow指向已去重部分的最后一个位置
        int slow=0;

        //fast用来遍历数组
        for(int fast=1;fast<nums.length;fast++){
            //找到一个新的、不重复的元素
            if(nums[fast]!=nums[slow]){
                slow++;
                //将新元素放到已去重部分的末尾
                nums[slow]=nums[fast];
            }
        }
        //slow是最后一个不重复元素的下标
        return slow+1;
    }
}

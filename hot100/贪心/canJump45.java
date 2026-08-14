package hot100.贪心;

public class canJump45 {
    public static int jump(int[] nums){
        int steps=0;
        int end=0;  // 当前这一步最远能覆盖到哪里
        int farthest=0;  // 在当前覆盖范围内，下一步最远能到哪里

        //最后一个下标不需要跳了
        for(int i=0;i<nums.length-1;i++){
            //当前跳跃范围内 计算下一步能到达的最远位置
            farthest=Math.max(farthest,i+nums[i]);

            //已遍历完当前这一步能覆盖的范围
            if(i==end){
                steps++;
                end=farthest;

                if(end>=nums.length-1){
                    break;
                }
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println(jump(nums1)); // 2

        int[] nums2 = {2, 3, 0, 1, 4};
        System.out.println(jump(nums2)); // 2

        int[] nums3 = {1, 1, 1, 1};
        System.out.println(jump(nums3)); // 3
    }
}
//2026.8.13  记录每次能走的最大距离，走到就+1次 更新最大距离 判断是否到达终点
//2026.8.14 过
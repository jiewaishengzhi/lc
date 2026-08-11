package hot100.哈希表;

import java.util.HashSet;
import java.util.Set;

public class longestConsecutive128 {
    // 核心函数：返回最长连续序列的长度
    public static int longestConsecutive(int[] nums){
        Set<Integer> set=new HashSet<>();

        for(int num:nums){
            set.add(num);
        }

        int maxLen=0;

        for(int num:nums){
            //num-1不存在时 num才可能是某个连续序列的起点
            if(!set.contains(num-1)){
                int currentNum=num;
                int currentLength=1;
                //从起点开始不断寻早下一个连续数字
                while(set.contains(currentNum+1)){
                    currentNum++;
                    currentLength++;
                }
                maxLen=Math.max(maxLen,currentLength);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums1={100,3,200,1,3,2};
        System.out.println(longestConsecutive(nums1));

        int[] nums2={0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(nums2)); // 9

        int[] nums3 = {};
        System.out.println(longestConsecutive(nums3)); // 0
    }
}

package hot100.array;

import java.util.HashMap;
import java.util.Map;

/*
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1：

输入：nums = [3,2,3]
输出：3
示例 2：

输入：nums = [2,2,1,1,1,2,2]
输出：2

提示：
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
输入保证数组中一定有一个多数元素。


进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class majorityElement169 {
    public int majorityElement(int[] nums){
        Map<Integer,Integer> counts=new HashMap<>();
        int n=nums.length;
        for(int num:nums){
            counts.put(num,counts.getOrDefault(num,0)+1);
            if(counts.get(num)>n/2){
                return num;
            }
        }
        return -1;
    }

    /*
    摩尔投票法
核心思想
多数元素比其他所有元素的总和还要多。想象一场“投票”：
设定一个 候选者 candidate 和一个 计数器 count。
遍历数组：
若 count == 0，当前元素成为新的 candidate，count = 1。
若当前元素等于 candidate，count++；
否则 count--（相当于用两个不同的元素相互抵消）。
遍历结束后，candidate 就是多数元素。
原理：由于多数元素出现次数 > n/2，它比其他所有元素出现的总次数还多。每次遇到不同元素就“同归于尽”，最后活下来的必然是多数的那个“帮派”。
     */
    public int majorityElement2(int[] nums){
        int candidate=0;
        int count=0;
        for(int num:nums){
            if(count==0)candidate=num;
            count+=(num==candidate)?1:-1;
        }
        return candidate;
    }
}
//2026.7.12 又忘了
//2026.7.15 过

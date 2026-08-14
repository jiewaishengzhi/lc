package hot100.堆;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class topKFrequent347 {

    public static int[] topKFrequent(int[] nums,int k){
        Map<Integer,Integer> map=new HashMap<>();

        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        //小根堆
        PriorityQueue<int[]> heap=new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));

        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            int num=entry.getKey();
            int count=entry.getValue();

            heap.offer(new int[]{num,count});

            if(heap.size()>k){
                heap.poll();
            }
        }
        int[] res=new int[k];
        for(int i=0;i<k;i++){
            res[i]=heap.poll()[0];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(nums1, 2)));

        int[] nums2 = {1};
        System.out.println(Arrays.toString(topKFrequent(nums2, 1)));

        int[] nums3 = {4, 1, -1, 2, -1, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(nums3, 2)));
    }
}

//2026.8.14 Integer.compare(a[1],b[1])

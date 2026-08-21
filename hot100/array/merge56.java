package hot100.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class merge56 {
    public static int[][] merge(int[][] intervals){
        if(intervals==null||intervals.length==0){
            return new int[0][];
        }
        //1.先按区间的起点排序
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        // 保存合并后的区间
        List<int[]> res=new ArrayList<>();

        //把第一个区间作为当前待合并的区间
        int left=intervals[0][0];
        int right=intervals[0][1];

        //从第二个区间开始遍历
        for(int i=1;i<intervals.length;i++){
            int[] current=intervals[i];

            //重叠：当前左边界<=已经合并区间的右边界
            if(current[0]<=right){
                right=Math.max(right,current[1]);
            }else{
                res.add(new int[]{left,right});
                left=current[0];
                right=current[1];
            }
        }
        // 循环结束后，最后一个待合并区间还没有加入结果集
        res.add(new int[]{left,right});

        //List<int[]>  转化为int[][]
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) throws IOException {
        int[][] intervals={
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };
        //把标准输入包装成一个“按行读取”的对象
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        int n=Integer.parseInt(br.readLine().trim());
//        int[][] intervals = new int[n][2];
//
//        for(int i=0;i<n;i++){
//            //StringTokenizer 默认以空格、制表符等空白符切分
//            StringTokenizer st=new StringTokenizer(br.readLine());
//            intervals[i][0]=Integer.parseInt(st.nextToken());
//            intervals[i][1]=Integer.parseInt(st.nextToken());
//        }

        int[][] res=merge(intervals);
        System.out.print("[");
        for(int i=0;i<res.length-1;i++){
            System.out.print(Arrays.toString(res[i]));
            System.out.print(",");
        }
        System.out.print(Arrays.toString(res[res.length-1]));
        System.out.print("]");
    }
}
//2026.8.10 快手java一面
//2026.8.13 重刷
//2026.8.17 记住：Comparator.comparingInt(a->a[0])  res.toArray(new int[res.size()][])
//2026.8.21 记住：Comparator.comparingInt(a->a[0])  res.toArray(new int[res.size()][])

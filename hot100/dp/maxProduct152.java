package hot100.dp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class maxProduct152 {
    public static int maxProduct(int[] nums){
        int minF=nums[0];
        int maxF=nums[0];

        int res=nums[0];

        for(int i=1;i<nums.length;i++){
            int mx=maxF;
            int mn=minF;

            maxF=Math.max(nums[i],Math.max(nums[i]*mx,nums[i]*mn));
            minF=Math.min(nums[i],Math.min(nums[i]*mx,nums[i]*mn));

            res=Math.max(res,maxF);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        int[] nums=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        System.out.println(maxProduct(nums));
    }
}
//2026.8.13 还可以
//2026.8.17 过
package hot100.贪心;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class canJump55 {
    public static boolean canJump(int[] nums){
        //maxReach表示当前能跳到的最远位置
        int maxReach=0;
        int n=nums.length;

        for(int i=0;i<n;i++){
            if(i>maxReach){
                return false;
            }
            maxReach=Math.max(maxReach,i+nums[i]);
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //第一行 数组长度
        int n=Integer.parseInt(br.readLine().trim());
        //第二行 数组元素
        int[] nums=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        System.out.println(canJump(nums));
    }
}
//2026.8.13 需要先判断当前i位置是否可达

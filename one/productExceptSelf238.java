public class productExceptSelf238 {
    public int[] productExceptSelf(int[] nums){
        int n=nums.length;

        //1.定义两个辅助数组
        int[] L=new int[n]; //存前缀积
        int[] R=new int[n]; //存后缀积
        int[] arr=new int[n];

        //2.填充左侧列表L L[i]为索引i左侧所有元素的乘积
        L[0]=1;
        for(int i=1;i<n;i++){
            L[i]=nums[i-1]*L[i-1];
        }

        //3.填充右侧列表R R[i]为索引i右侧所有元素的乘积
        R[n-1]=1;
        for(int i=n-2;i>=0;i--){
            R[i]=nums[i+1]*R[i+1];
        }

        //4.最总计算
        for(int i=0;i<n;i++){
            arr[i]=L[i]*R[i];
        }
        return arr;
    }

    public int[] productExceptSelf2(int[] nums){
        int n=nums.length;
        int []answer=new int[n];

        // 1. 第一轮：计算“前缀积”（左边所有数的乘积）
        // answer[i] 表示 i 左边所有元素的乘积
        // 对于索引 0，左边没有元素，初始化为 1
        answer[0]=1;
        for(int i=1;i<n;i++){
            answer[i]=answer[i-1]*nums[i-1];
        }
        // 此时 answer 数组里存的是：[1, nums[0], nums[0]*nums[1], ...]

        // 2. 第二轮：计算“后缀积”并直接得出最终结果
        // R 是一个滚动变量，用来记录右边元素的乘积
        int R=1;
        for(int i=n-1;i>=0;i--){
            answer[i]=answer[i]*R;
            R=R*nums[i];
        }
        return answer;
    }
}

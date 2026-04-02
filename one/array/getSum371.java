package one.array;

public class getSum371 {
    public int getSum(int a,int b){
        //当进位不为0时，说明还有数没加完，继续循环
        while(b!=0){
            //1.算进位
            int carry=(a&b)<<1;

            //2.算本位(不进位加法)：只关心0+1或1+0的位置
            a=a^b;

            //3.将进位赋值给b 准备下一轮循环把它加到a里去
            b=carry;
        }
        // 当 b 为 0，说明没有进位了，a 就是最终结果
        return a;
    }
}
package hot100.array;

public class reverse7 {
    public static int reverse(int x){
        int res=0;
        while (x!=0){
            if(res<Integer.MIN_VALUE/10 || res> Integer.MAX_VALUE/10){
                return 0;
            }
            int digit=x%10;
            x=x/10;
            res=res*10+digit;
        }
        return res;
    }
}

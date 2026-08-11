package hot100.dp;

public class climbStairs70 {
    public static int climbStairs(int n){
        if(n==1)return 1;
        if(n==2)return 2;

        int first=1;
        int second=2;
        for(int i=3;i<=n;i++){
            int temp=first+second;

            first=second;
            second=temp;
        }
        return second;
    }

    public static void main(String[] args) {
        int n1=2;
        int n2=3;
        System.out.println(climbStairs(n1));
        System.out.println(climbStairs(n2));
    }
}

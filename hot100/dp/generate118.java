package hot100.dp;

import java.util.ArrayList;
import java.util.List;

public class generate118 {
    public static List<List<Integer>> generate(int numRows){
        List<List<Integer>> res=new ArrayList<>();

        for(int i=0;i<numRows;i++){
            List<Integer> currentRow=new ArrayList<>();
            //每一行第一个元素都是1
            currentRow.add(1);

            //填充中间元素
            if(i>=2){
                List<Integer> pre=res.get(i-1);
                for(int j=1;j<i;j++){
                    int value=pre.get(j-1)+pre.get(j);
                    currentRow.add(value);
                }
            }
            //第二行之后，最后一个元素也是1
            if(i>=1){
                currentRow.add(1);
            }
            res.add(currentRow);
        }
        return res;
    }

    public static void main(String[] args) {
        print(generate(1));
        System.out.println();

        print(generate(3));
        System.out.println();

        print(generate(5));
    }
    public static void print(List<List<Integer>> triangle){
        for(List<Integer> row:triangle){
            System.out.println(row);
        }
    }
}
//2026.8.14 分三部分  第一个 中间的 最后一个
//2026.8.16 思路记得 要从0开始
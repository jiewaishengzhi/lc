package hot100.linklist;
/*
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

示例 1：
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]

示例 2：
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
 */
public class reverseKGroup25 {
    /*
    每组都要做三件事:
    1.判断后面是否还有k个节点
    2.翻转这k个节点
    3.把翻转后的这一组接回原链表
     */
    public ListNode reverseKGroup(ListNode head, int k){
        ListNode dummy=new ListNode(0);
        dummy.next=head;

        /*
        pre：当前要翻转这一组的前一个节点
        start：当前组的第一个节点
        end：当前组的最后一个节点
        next：下一组的第一个节点
         */

        //pre指向当前要反转的这一组的前一个节点
        ListNode pre=dummy;
        ListNode end=dummy;

        while(true){
            //1.找到当前这一组的k个节点 也就是end 不足k个直接结束
            for(int i=0;i<k&&end!=null;i++){
                end=end.next;
            }
            //不足k个直接结束
            if(end==null)break;

            //2.记录这一组的起点和下一组的起点
            ListNode start=pre.next;
            ListNode next=end.next;

            //3.先把这一组断开
            end.next=null;

            //4.翻转这一组
            pre.next=reverse(start);

            //5.翻转后 连接：start变成这一组的尾节点
            start.next=next;

            // 6. 移动 pre 和 end，准备处理下一组
            pre=start;
            end=pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head){
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
}
//2026.7.11 不会啊mad 记得一点思路，一点
//2026.7.13 又没写出来还有救吗 依旧一点思路
//2026.7.14 写出来了(昨晚写过)

package hot100.linklist;
/*
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
示例 1：
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：
输入：head = [1], n = 1
输出：[]

示例 3：
输入：head = [1,2], n = 1
输出：[1]
 */
public class removeNthFromEnd19 {
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy=new ListNode(0,head);
        ListNode fast=dummy;
        ListNode slow=dummy;

        for(int i=0;i<=n;i++){//快指针移n+1位
            fast=fast.next;
        }

        while(fast!=null){ //慢指针到倒数n-1处(倒数第n的前驱)
            fast=fast.next;
            slow=slow.next;
        }

        slow.next=slow.next.next;

        return dummy.next;

    }
}
/*2026.7.11
1.哑节点
2.快指针先走n+1   找到倒数第n+1个
 */

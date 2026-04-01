package linklist;

public class removeNthFromEnd19 {
    public ListNode removeNthFromEnd(ListNode head,int n){
        //1.创建虚拟头节点 指向head
        ListNode dummy=new ListNode(0,head);

        //2.定义快慢指针
        ListNode slow=dummy;
        ListNode fast=dummy;

        //3.让fast先领先n步
        for(int i=0;i<n;i++){
            fast=fast.next;
        }

        //4.快慢指针同时往前走 直到fast指向链表的最后一个节点
        //此时slow刚好停在待删除节点的前一个
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }

        //5.执行删除操作
        slow.next=slow.next.next;

        //6.返回
        return dummy.next;
    }
}

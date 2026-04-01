package linklist;


public class reverseList206 {
    public ListNode reverseList(ListNode head){
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            //1.先把curr的下一个节点存起来 防止丢了
            ListNode nextTemp=curr.next;
            //2.核心动作 反转指针 让当前节点指向前一个节点
            curr.next=prev;
            //3.两个指针整体后移一步
            prev=curr; //prev变成当前的curr
            curr=nextTemp;
        }
        //循环结束后 curr是null，prev刚好停在新的头节点上
        return prev;
    }

    public ListNode reverseList2(ListNode head){
        ListNode newHead=null; //新链表的头 初始为空

        while(head!=null){
            ListNode nextemp=head.next; //1.保存下一个节点
            head.next=newHead;  //2.当前节点插入新链表头部
            newHead=head; //3.更新新链表头
            head=nextemp;//4.继续遍历原链表
        }
        return newHead;
    }
}

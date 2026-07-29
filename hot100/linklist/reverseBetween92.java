package hot100.linklist;

public class reverseBetween92 {
    public ListNode reverseBetween(ListNode head,int left,int right){
        ListNode dummy=new ListNode(0);
        dummy.next=head;

        //1.找到第left-1个节点
        ListNode pre=dummy;
        for(int i=1;i<left;i++){
            pre=pre.next;
        }
        //2.记录第left个节点
        ListNode leftNode=pre.next;
        //3.反转left到right这段链表
        ListNode prev=null;
        ListNode curr=leftNode;
        for(int i=0;i<=right-left;i++){
            ListNode nextTemp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nextTemp;
        }
        //4.将反转后的部分重新接回原链表
        pre.next=prev;
        leftNode.next=curr;

        return dummy.next;
    }
}
//2026.7.25 过一遍
//2026.7.26 注意left-1 移动多少位
//2026.7.29 整体流程记得 注意下移动位数
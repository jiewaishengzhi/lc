package hot100.linklist;

public class reverseList206 {
    public ListNode reverseList(ListNode head){
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            // 1. 先把 curr 的下一个节点存起来，防止丢了
            ListNode nextTemp=curr.next;
            // 2. 【核心动作】反转指针：让当前节点指向前一个节点
            curr.next=prev;
            // 3. 两个指针整体向后移动一步
            prev=curr;// prev 变成当前的 curr
            curr=nextTemp;//curr 变成刚才存的 nextTemp
        }
        return prev;
    }
}

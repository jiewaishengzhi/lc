package hot100.linklist;

public class detectCycle142 {
    public ListNode detectCycle(ListNode head){
        ListNode slow=head;
        ListNode fast=head;

        //1.判断是否有环 并找到快慢指针相遇点
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next;

            if(slow==fast){
                //2.一个指针回到头节点
                ListNode p=head;
                //两个指针每次都走一步
                while(p!=slow){
                    p=p.next;
                    slow=slow.next;
                }

                //再次相遇的位置就是环入口
                return p;
            }
        }
        //fast到null 说明无环
        return null;
    }
}
//2026.7.28  第二次要先判断再移动  或者直接while(slow!=p) 循环结束后slow==p
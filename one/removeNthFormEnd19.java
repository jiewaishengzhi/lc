public class removeNthFormEnd19 {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val=val;
        }
        ListNode(int val, ListNode next){
            this.val=val;
            this.next=next;
        }

    }

    public ListNode removeNthFromEnd(ListNode head,int n){
        //1.创建虚拟头节点 指向head
        //  为了方便处理删除头节点的情况 [1],n=1
        ListNode dummy=new ListNode(0,head);

        //2.定义快慢指针，一开始都站在虚拟头节点上
        ListNode slow=dummy;
        ListNode fast=dummy;

        //3.让 fast 先领先 n 步
        for(int i=0;i<n;i++){
            fast=fast.next;
        }

        //4.快慢指针同步向前走 直到fast指向链表最后一个节点
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

package hot100.linklist;

import java.time.chrono.MinguoDate;

public class sortList148 {
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) return head;
        //1.找中点
        ListNode mid=getMid(head);
        ListNode rightHead=mid.next;
        mid.next=null;

        //2.左右递归排序
        ListNode left=sortList(head);
        ListNode right=sortList(rightHead);

        //3.合并
        return mergeTwoLists(left,right);

    }

    private ListNode getMid(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    private ListNode mergeTwoLists(ListNode l1,ListNode l2){
        ListNode dummy=new ListNode(-1);
        ListNode curr=dummy;

        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                curr.next=l1;
                l1=l1.next;
            }else{
                curr.next=l2;
                l2=l2.next;
            }
            curr=curr.next;
        }
        if(l1!=null){
            curr.next=l1;
        }else{
            curr.next=l2;
        }


        return dummy.next;
    }

}

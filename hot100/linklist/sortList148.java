package hot100.linklist;

import java.time.chrono.MinguoDate;

public class sortList148 {
    public ListNode sortList(ListNode head){
        if(head==null||head.next==null)return head;

        //1.找中点
        ListNode mid=getMid(head);
        ListNode rightHead=mid.next;
        mid.next=null;

        //2.递归左右排序
        ListNode left=sortList(head);
        ListNode right=sortList(rightHead);

        //3.合并
        return mergeTwoLists(left,right);
    }

    //找到中间节点的前一个节点
    private ListNode getMid(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;
        while(head!=null&&head.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }


    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

}

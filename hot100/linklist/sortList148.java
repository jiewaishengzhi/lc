package hot100.linklist;
/*
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
示例 1：
输入：head = [4,2,1,3]
输出：[1,2,3,4]

示例 2：
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]

示例 3：
输入：head = []
输出：[]
 */
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
//2026.7.11 略卡
//2026.7.14 递归出口return head

package hot100.linklist;

import java.util.ArrayList;
import java.util.List;

public class isPalindrome234 {
    //将链表的值放入数组 通过数组判断
    public boolean isPalindrome(ListNode head){
        List<Integer> list=new ArrayList<>();

        //1.将链表的值放入数组中
        ListNode curr=head;
        while(curr!=null){
            list.add(curr.val);
            curr=curr.next;
        }

        //2.双指针判断是否回文
        int left=0;
        int right=list.size()-1;
        while(left<right){
            if(list.get(left)!=list.get(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head){
        if(head==null||head.next==null)return true;

        //1.找到前半段末尾
        ListNode slow=head;
        ListNode fast=head.next;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        //2.反转后半部分
        ListNode rightHead=reverse(slow.next);

        //3.比较前半部分和反转后后半部分
        ListNode p1=head;
        ListNode p2=rightHead;
        while(p2!=null){
            if(p1.val!=p2.val){
                return false;
            }
            p1=p1.next;
            p2=p2.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
//2026.8.21 过
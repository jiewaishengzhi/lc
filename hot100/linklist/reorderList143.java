package hot100.linklist;

import java.util.ArrayList;
import java.util.List;

/*
给定一个单链表 L 的头节点 head ，单链表 L 表示为：
L0 → L1 → … → Ln - 1 → Ln
请将其重新排列后变为：
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1：
输入：head = [1,2,3,4]
输出：[1,4,2,3]

示例 2：
输入：head = [1,2,3,4,5]
输出：[1,5,2,4,3]

提示：
链表的长度范围为 [1, 5 * 104]
1 <= node.val <= 1000
 */
public class reorderList143 {
    public void reorderList(ListNode head){
        //空链表 只有一个节点 只有两个节点 都不需要重排
        if(head==null||head.next==null||head.next.next==null);
        //1.找到链表中点
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        ListNode second=slow.next;
        slow.next=null;

        //2.反转后半部分
        second=reverse(second);

        //3.交替合并后半部分
        ListNode first=head;

        while(second!=null){//后半段会比前半段短
            //保存下一步要走的节点 防止改指针后丢失
            ListNode temp1=first.next;
            ListNode temp2=second.next;

            //first后面接second
            first.next=second;
            //second后面接first的下一个节点
            second.next=temp1;
            //两条链表往后移动
            first=temp1;
            second=temp2;
        }

    }

    private ListNode reverse(ListNode head){
        ListNode prev=null;
        ListNode curr=head;

        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public void reorderList2(ListNode head){
        if(head==null||head.next==null)return;

        //用arraylist装链表
        List<ListNode> list=new ArrayList<>();
        ListNode curr=head;
        while (curr!=null){
            list.add(curr);
            curr=curr.next;
        }
        int left=0;
        int right=list.size()-1;

        while (left<right){
            //前连后
            list.get(left).next=list.get(right);
            left++;

            if(left==right)break;

            //后连递增过的前
            list.get(right).next=list.get(left);
            right--;
        }
        //最后节点置空
        list.get(left).next=null;
    }
}

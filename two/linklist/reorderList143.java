package linklist;

import java.util.ArrayList;
import java.util.List;

public class reorderList143 {
    public void reorderList(ListNode head){
        if(head==null)return;

        //1.把所有节点放入ArrayList
        List<ListNode> list=new ArrayList<>();
        ListNode curr=head;
        while(curr!=null){
            list.add(curr);
            curr=curr.next;
        }
        //2.双指针向中间靠拢
        int i=0,j=list.size()-1;
        while(i<j){
            //前连后
            list.get(i).next=list.get(j);
            i++;

            //如果i==j 说明相遇
            if(i==j)break;

            //后连前
            list.get(j).next=list.get(i);
        }
        //3.处理尾巴 最后一个节点的next清空
        list.get(i).next=null;
    }

    //切分 反转 穿针
    public void reorderList2(ListNode head){
        if(head==null||head.next==null)return;

        //---1.找中点(快慢指针)
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next;
        }

        //此时slow为中点
        //为了把链表断成两截 我们需要记录后半段的头 并把slow.next置空
        ListNode secondHead=slow.next;
        slow.next=null; //切断

        // ---2.反转后半段
        secondHead=reverseList(secondHead);

        // ---3.交叉合并merge
        ListNode firstHead=head;

        //后半段比前半段短或者相等 所以后半段为循环结束条件
        while(secondHead!=null){
            //1.先存下两个链表的下一步 防止断
            ListNode temp1=firstHead.next;
            ListNode temp2=secondHead.next;

            //2.交叉连接
            firstHead.next=secondHead; //前-> 后
            secondHead.next=temp1; //后-> 前的next

            //3.移动指针
            firstHead=temp1;
            secondHead=temp2;
        }
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode nextTemp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nextTemp;
        }
        return prev;
    }
}

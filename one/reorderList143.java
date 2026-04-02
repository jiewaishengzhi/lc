import java.util.ArrayList;
import java.util.List;

public class reorderList143 {
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

    public void reorderList(ListNode head){
        if(head==null) return;

        //1.把所有的节点放入ArrayList
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
            i++;//右移

            //如果i==j说明相遇
            if(i==j)break;

            //后连前(递增过的
            list.get(j).next=list.get(i);
            j--;
        }
        //3.处理尾巴 最后一个节点的next置空
        list.get(i).next=null;
    }

    public void reorderList2(ListNode head){
        if(head==null||head.next==null)return;

        // ---第一步 快慢指针找中点 ---
        ListNode slow=head;
        ListNode fast=head;

        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        //此时slow为中点
        //为了把链表断成两截，我们需要记录后半段的头，并把 slow.next 置空
        ListNode secondHead=slow.next;
        slow.next=null;

        // --- 第二部 反转后半段 ---
        secondHead=reverseList(secondHead);

        // --- 第三步 交叉合并 ---
        ListNode firstHead=head;
        while(secondHead!=null){
            //1.先存下两个链表的下一步 防止断链
            ListNode temp1=firstHead.next;
            ListNode temp2=secondHead.next;

            //2.交叉连接
            firstHead.next=secondHead; //前->后
            secondHead.next=temp1; //后->前的next

            //3.移动指针
            firstHead=temp1;
            secondHead=temp2;
        }
    }

    // 辅助函数：反转链表
    private ListNode reverseList(ListNode head){
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

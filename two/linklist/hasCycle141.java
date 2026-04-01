package linklist;

import java.util.HashSet;
import java.util.Set;

public class hasCycle141 {
    //1.哈希表
    public boolean hasCycle(ListNode head){
        //1.准备一个本子 用来记录走过的节点
        Set<ListNode> seen=new HashSet<>();

        while(head!=null){
            //2.查重：这个节点之前见过吗
            if(seen.contains(head)){
                return true; //如果见过了 说明绕回来了 有环
            }

            //3.登记：没见过 记录下来
            seen.add(head);

            //4.继续往下走
            head=head.next;
        }

        //5.走到null了说明是直路 没环
        return false;
    }

    //2.龟兔赛跑
    public boolean hasCycle2(ListNode head){
        //1.特殊情况 空链表或只有一个节点 肯定没环
        if(head==null||head.next==null){
            return false;
        }

        //2.定义快慢指针
        ListNode slow=head;
        ListNode fast=head;

        //3.开始赛跑
        while(fast!=null&&fast.next!=null){
            slow=slow.next; //慢走一步
            fast=fast.next; //快走两步

            //4.检查是否相遇
            if(slow==fast){
                return true; //兔子追上乌龟了，有环
            }
        }
        //5.兔子跑到了尽头 说明没有环
        return false;
    }
}

package hot100.linklist;
/*
给你一个链表的头节点 head ，判断链表中是否有环。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
如果链表中存在环 ，则返回 true 。 否则，返回 false 。

 */
public class hasCycle141 {
    public boolean hasCycle(ListNode head){
        // 1. 特殊情况处理：空链表或只有一个节点，肯定没环
        if(head==null ||head.next==null){
            return false;
        }

        //2.定义快慢指针
        ListNode slow=head;
        ListNode fast=head;

        //3.开始赛跑
        // 只要兔子没跑到终点 (fast != null) 且没跑到断头路 (fast.next != null)
        while(fast!=null&&fast.next!=null){
            slow=slow.next;    // 乌龟走 1 步
            fast=fast.next.next;   // 兔子走 2 步

            // 4. 检查是否相遇
            if (slow == fast) {
                return true; // 兔子追上乌龟了，有环！
            }
        }
        // 5. 兔子跑到了尽头，说明没有环
        return false;
    }
}

//2026.7.11 一遍过

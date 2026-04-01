import java.util.PriorityQueue;

public class mergeKLists23 {
    public ListNode mergeKLists(ListNode[] lists){
        if(lists==null||lists.length==0) return null;

        //1.定义小顶堆 PriorityQueue
        //这里的(a,b)->a.val-b.val 是lambda表达式，告诉堆怎么排大小
        PriorityQueue<ListNode> queue=new PriorityQueue<>(lists.length,(a, b) -> a.val - b.val);

        //2.把所有链表的头节点先扔进堆里
        //相当于比赛开始 所有跑道的起跑选手就位
        for(ListNode node:lists){
            if(node!=null){
                queue.offer(node);
            }
        }

        //3.准备虚拟头节点
        ListNode dummy=new ListNode(-1);
        ListNode curr=dummy;

        //4.开始
        while(!queue.isEmpty()){
            //拿出堆顶最小的节点 (出队)
            ListNode minNode=queue.poll();

            //接在curr后面
            curr.next=minNode;
            curr=curr.next;

            //如果这个节点后面还有人，就把下一个人扔进堆里
            if(minNode.next != null){
                queue.offer(minNode.next);
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists){
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    //辅助函数 分治归并
    private ListNode merge(ListNode[] lists,int left,int right){
        //Base Case:只有一个链表 不需要合并，直接返回
        if(left==right) return lists[left];
        //越界保护
        if(left>right) return null;

        //1.找中点
        int mid=left+(right-left)/2;

        //2.递归 左边合成一个大链表 右边合成一个大链表
        ListNode l1=merge(lists,left,mid);
        ListNode l2=merge(lists,mid+1,right);

        // 3. 【复用】把这两个大链表合并 (直接调用上一题的函数)
        return mergeTwoLists(l1, l2);
    }
    // 这是你刚才写的代码 (完全没变)
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
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
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        return dummy.next;
    }
}

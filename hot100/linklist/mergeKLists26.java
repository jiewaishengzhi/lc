package hot100.linklist;

import java.awt.*;
import java.util.PriorityQueue;

public class mergeKLists26 {
    public ListNode mergeKLists(ListNode[] lists){
        if(lists==null||lists.length==0)return null;

        //创建小顶堆 按节点值排序
        PriorityQueue<ListNode> minHeap=new PriorityQueue<>((a,b)->a.val-b.val);

        //每个链表的头节点放入堆中
        for(ListNode node:lists){
            if(node!=null){
                minHeap.offer(node);
            }
        }
        ListNode dummy=new ListNode(-1);
        ListNode curr=dummy;

        while(!minHeap.isEmpty()){
            //取出当前最小节点
            ListNode minNode=minHeap.poll();
            curr.next=minNode;
            curr=curr.next;

            if(minNode.next!=null){
                minHeap.offer(minNode.next);
            }
        }
        return dummy.next;

        //分治
//        if(lists==null||lists.length==0)return null;
//        return merge(lists,0,lists.length-1);
    }

    // 分治递归合并区间 [l, r] 的链表
    private ListNode merge(ListNode[] lists,int l,int r){
        if(l==r)return lists[l];
        if(l>r)return null;
        int mid=l+(r-l)/2;
        ListNode left=merge(lists,1,mid);
        ListNode right=merge(lists,mid+1,r);
        return mergeTwoLists(left,right);
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

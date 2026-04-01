package linklist;

import java.util.PriorityQueue;

public class mergeKList2 {
    //合并链表数组中的k个升序链表
    //1.优先队列
    public ListNode mergeKLists(ListNode[] lists){
        if(lists==null||lists.length==0) return null;

        //1.定义小顶堆 PriorityQueue
        PriorityQueue<ListNode> queue=new PriorityQueue<>(lists.length,(a, b) -> a.val - b.val);

        //2.把所有链表的头节点先扔进堆里
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
            //拿出堆顶的最小节点(出队)
            ListNode minNode=queue.poll();

            //接在curr后面
            curr.next=minNode;
            curr=curr.next;

            //如果这个节点后面还有 就把下一个扔进堆里
            if(minNode.next!=null){
                queue.offer(minNode.next);
            }
        }
        return dummy.next;
    }

    //2.分治法

    private ListNode mergeKLists2(ListNode[] lists){
        if(lists==null||lists.length==0)return null;
        return merge(lists,0,lists.length-1);
    }

    private ListNode merge(ListNode[] lists,int left,int right){
        //只有一个链表 不需要合并 返回
        if(left==right) return lists[left];
        //越界保护
        if(left>right) return null;

        //1.找中点
        int mid=left+(right-left)/2;

        //2.递归 左边合成一个大链表 右边合成一个大链表
        ListNode l1=merge(lists,left,mid);
        ListNode l2=merge(lists,mid+1,right);

        //3.服用 把这两个大链表合并
        return mergeTwoLists(l1,l2);
    }

    /**
     * 合并两个升序链表
     * @param l1
     * @param l2
     * @return 升序链表
     */
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
        if(l1!=null)curr.next=l1;
        if(l2!=null)curr.next=l2;

        return dummy.next;
    }
}

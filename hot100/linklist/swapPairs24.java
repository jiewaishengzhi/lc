package hot100.linklist;

public class swapPairs24 {
    static class ListNode{
        int val;
        ListNode next;

        ListNode(int val){
            this.val=val;
        }
    }
    public static ListNode swapPairs(ListNode head){
        ListNode dummy=new ListNode(0);
        dummy.next=head;

        //pre指向当前待交换两个节点的前一个节点
        ListNode prev=dummy;
        //当前和下一个节点都存在时，才能交换
        while(prev.next!=null&& prev.next.next!=null){
            ListNode first=prev.next;
            ListNode second=first.next;

            //prev->first->second
            //变为 prev->second->first
            prev.next=second;
            first.next=second.next;
            second.next=first;

            prev=first;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head1=buildList(new int[]{1,2,3,4});
        printList(head1);
        System.out.print(" -> ");
        printList(swapPairs(head1));
        System.out.println();

        ListNode head2=buildList(new int[]{1});
        printList(head2);
        System.out.print(" -> ");
        printList(swapPairs(head2));
        System.out.println();

        ListNode head3=buildList(new int[]{});
        printList(head3);
        System.out.print(" -> ");
        printList(swapPairs(head3));
    }

    //打印链表
    public static void printList(ListNode head){
        System.out.print("[");
        while(head!=null){
            System.out.print(head.val);
            if(head.next!=null){
                System.out.print(" ");
            }
            head=head.next;
        }
        System.out.print("]");
    }

    //根据数组创建链表
    public static ListNode buildList(int[] nums){
        ListNode dummy=new ListNode(0);
        ListNode tail=dummy;

        for(int num:nums){
            tail.next=new ListNode(num);
            tail=tail.next;
        }
        return dummy.next;
    }
}

//2026.8.11 百度java一面
//2026.8.14 过
//2026.8.16 过
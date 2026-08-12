package hot100.linklist;

import java.util.List;

public class getIntersectionNode160 {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val=val;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode pA=headA;
        ListNode pB=headB;

        while(pA!=pB){
            pA=pA==null?headB:pA.next;
            pB=pB==null?headA:pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        // 用例 1：相交于值为 8 的节点
        ListNode common1 = buildList(new int[]{8, 4, 5});
        ListNode headA1 = connect(buildList(new int[]{4, 1}), common1);
        ListNode headB1 = connect(buildList(new int[]{5, 6, 1}), common1);
        print(getIntersectionNode(headA1, headB1)); // 8

        // 用例 2：相交于值为 2 的节点
        ListNode common2 = buildList(new int[]{2, 4});
        ListNode headA2 = connect(buildList(new int[]{1, 9, 1}), common2);
        ListNode headB2 = connect(buildList(new int[]{3}), common2);
        print(getIntersectionNode(headA2, headB2)); // 2

        // 用例 3：两个链表不相交
        ListNode headA3 = buildList(new int[]{2, 6, 4});
        ListNode headB3 = buildList(new int[]{1, 5});
        print(getIntersectionNode(headA3, headB3)); // null
    }

    public static ListNode buildList(int[] nums){
        ListNode dummy=new ListNode(0);
        ListNode curr=dummy;
        for(int num:nums){
            curr.next=new ListNode(num);
            curr=curr.next;
        }
        return dummy.next;
    }

    public static ListNode connect(ListNode head,ListNode common){
        if(head==null)return common;
        ListNode curr=head;
        while(curr.next!=null){
            curr=curr.next;
        }
        curr.next=common;
        return head;
    }

    public static void print(ListNode node){
        if(node==null){
            System.out.println("null");
        }else{
            System.out.println(node.val);
        }

    }
}

package linklist;

public class mergeTwoList21 {
    //将两个升序链表 合成一个升序链表
    public ListNode mergeTwoLists(ListNode list1,ListNode list2){
        //1.dummy 假头 curr当前指针
        ListNode dummy=new ListNode(-1);
        ListNode curr=dummy;

        //2.循环比较 谁小谁接
        while(list1!=null&&list2!=null){
            if(list1.val<list2.val){
                curr.next=list1;
                list1=list1.next;
            }else{
                curr.next=list2;
                list2= list2.next;
            }
            curr= curr.next;
        }

        //3.收尾工作
        if(list1!=null){
            curr.next=list1;
        }else{
            curr.next=list2;
        }

        return dummy.next;
    }
}

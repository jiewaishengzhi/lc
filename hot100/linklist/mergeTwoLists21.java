package hot100.linklist;

public class mergeTwoLists21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 1. 标准命名：dummy (假头), cur (当前指针)
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        // 2. 循环比较，谁小接谁
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) { // 注：题目说是升序，这里用 <= 也可以
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        // 3. 【神级优化】收尾工作：直接把剩下的一整串接上去
        // 你的两个 while 循环可以缩减为这几行
        if (list1 != null) {
            cur.next = list1;
        } else {
            // 如果 list1 空了，那剩下的肯定都在 list2 里（哪怕 list2 也是空的也没关系）
            cur.next = list2;
        }

        return dummy.next;
    }
}

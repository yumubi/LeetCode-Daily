package src.Merge_Two_Sorted_Lists_21;

import org.junit.Test;

import java.util.Iterator;

/**
 * Definition for singly-linked list.
 * * }
 *  */
  class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next;}

      }


public class Solution {


    /**
     * todo
     * 超时，而且感觉思路也不对
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode L = new ListNode();
        ListNode list = L;
        while(list1.next != null || list2.next != null) {
         if(list1 == null) {
             L.next = list2;
             L = L.next;
             list2 = list2.next;
         }
         else if(list2 == null) {
             L = list1;
             list1 = list1.next;
         }
         else if(list2.val < list1.val) {
             L.next = list2;
             L = L.next;
         }
         else {
             L.next = list1;
             L = L.next;
         }
        }
        return list;
    }

    /**
     * 上述思路的规范实现，同下面的leetcode官方题解
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsPlus(ListNode l1, ListNode l2) {
        // 类似归并排序中的合并过程
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }


    /**
     * 迭代
     * 时间复杂度O(m+n)
     * 空间复杂度O(1)
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
// 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }


    /**
     *递归实现
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoList3(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }


    /**
     * 递归解法
     * 两个链表头部值较小的一个节点与剩下元素的 merge 操作结果合并
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists4(ListNode l1, ListNode l2) {

        if(l1 == null) return l2;
        else if(l2 == null) return l1;
        else if(l1.val < l2.val)  {
            //合并l1剩下的和l2
            l1.next = mergeTwoLists4(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists4(l2.next, l1);
                return l2;
        }
    }

















    @Test
    public void test(){
        /**
         * 注意区分L和list并不会一直同时指向同一个地方
         */
        ListNode L = new ListNode(0);
        ListNode list = L;
        L = new ListNode(-1);
        System.out.println(L == list);
    }


}

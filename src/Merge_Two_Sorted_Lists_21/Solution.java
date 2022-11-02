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
        return null;
    }























    @Test
    public void test(){

    }


}

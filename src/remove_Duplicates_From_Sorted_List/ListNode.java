package src.remove_Duplicates_From_Sorted_List;

public class ListNode {
    int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next;
    }

    class Solution {

        /**
         * 不知道哪里错了，只能输出两个节点
         * @param head
         * @return
         */
          public ListNode deleteDuplicates(ListNode head) {
              if(head == null) return null;
                ListNode dummyHead = new ListNode(-1);
                dummyHead.next = head;
                ListNode prev = head;
                head = head.next;
               while(head != null) {
                   if(head.val != prev.val) {
                       prev.next = head;
                       prev = prev.next;
                   }
                   //这里我把prev放到if里面不加else的话，如果末尾几个同相同，就会把那几个都保留下来
                   else {
                       head = head.next;
                       prev.next = null;//切断后面的节点
                   }
               }
               return dummyHead.next;
          }
    }

}

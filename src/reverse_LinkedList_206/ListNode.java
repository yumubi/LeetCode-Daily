package src.reverse_LinkedList_206;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    class Solution {
        /**
         * tmd,内存超限
         *
         * @param head
         * @return
         */
        public ListNode reverseList(ListNode head) {

              if(head == null) return head;
              Stack<ListNode> stack = new Stack<>();
              while(head != null)
              stack.push(head);
              ListNode dummyHead = new ListNode(-1);
              ListNode ptr = stack.pop();
              dummyHead.next = ptr;

              while(!stack.isEmpty()) {
                  ptr.next = stack.pop();
                  ptr = ptr.next;
              }
              return dummyHead.next;



            //此版本超时
//            if (head == null || head.next == null) return head;
//            ListNode dummyHead = new ListNode(-1);
//            ListNode ptr = reverseList(head.next);
//            dummyHead.next = ptr;
//            while (ptr.next != null) ptr = ptr.next;
//            ptr.next = head;
//            return dummyHead.next;

        }


    }
}

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
         * 我的实现
         *
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



    //从指针 cur 指向链表的头节点，随后开始对链表进行遍历。如果当前 cur 与 cur.next 对应的元素相同，
    // 那么我们就将 cur.next 从链表中移除；否则说明链表中已经不存在其它与 cur 对应的元素相同的节点，
    // 因此可以将 cur 指向 cur.nex
//此方法在cur中访问了cur.next，故循环条件是cur.next != null
    public ListNode deleteDuplicates2(ListNode head) {
          if(head == null) return head;
          ListNode cur = head;
          while(cur.next != null) {
              if(cur.val == cur.next.val) cur.next = cur.next.next;
              else cur = cur.next;
        }
          return head;
    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public ListNode deleteDuplicates3(ListNode head) {
          if(head == null) return head;
          ListNode slow = head;
          ListNode fast = head;
          while(fast != null) {
              if(slow.val != fast.val) {
                  slow.next = fast;
                  slow = slow.next;
              }
              fast = fast.next;
          }
          //slow.next = nul 是断开与后面重复元素的连接
          slow.next = null;
          return head;
    }


    /**递归解决
     找终止条件：当head指向链表只剩一个元素的时候，自然是不可能重复的，因此return
     想想应该返回什么值：应该返回的自然是已经去重的链表的头节点
     每一步要做什么：宏观上考虑，此时head.next已经指向一个去重的链表了，而根据第二步，我应该返回一个去重的链表的头节点。
     因此这一步应该做的是判断当前的head和head.next是否相等，如果相等则说明重了，返回head.next，否则返回head
     * @param head
     * @return
     */
    public ListNode deleteDuplicates4(ListNode head) {
        if(head == null || head.next == null) return head;
        head.next = deleteDuplicates4(head.next);
        if(head.val == head.next.val) head = head.next;
        return head;
    }






}

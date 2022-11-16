package src.remove_LinkedList_Element_203;

import java.util.List;
import java.util.Stack;

public class ListNode {
    int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next;
       }

    class Solution {
           public ListNode removeElements(ListNode head, int val) {
                if(head == null) return null;
                if(head.val == val) head = removeElements(head.next, val);
                if(head != null)  head.next = removeElements(head.next, val);
                return head;
           }


        /**
         * 递归解法
         * 对于给定的链表，首先对除了头节点 head 以外的节点进行删除操作，然后判断 head 的节点值是否等于给定的val。
         * 如果 head 的节点值等于val，则head 需要被删除，因此删除操作后的头节点为 head.next；如果 head 的节点值不等于 val，
         * 则 head 保留，因此删除操作后的头节点还是 head。上述过程是一个递归的过程。
         * 递归的终止条件是 head 为空，此时直接返回head。当head 不为空时，递归地进行删除操作，
         * 然后判断 head 的节点值是否等于 val 并决定是否要删除 head。
         * @param head
         * @param val
         * @return
         */
           public ListNode removeElements2(ListNode head, int val) {
               if(head == null) return head;
               // 每次出栈，意味着当前指针往左平移一步，举例：1,2,6 val=6
               head.next = removeElements2(head.next, val);
               // 方法递归到最后节点，head.next=null，head=6 等于val，所以null是返回值
               // 一旦出栈，指针左移，此时 head=2，head.next指向的是返回值，即2->null，即6被删除了
               return head.val == val ? head.next : head;
           }

        /**
         * 由于链表的头节点head 有可能需要被删除，因此创建哑节点dummyHead，令dummyHead.next=head，
         * 初始化 temp=dummyHead，然后遍历链表进行删除操作。最终返回 dummyHead.next 即为删除操作后的头节点。
         * @param head
         * @param val
         * @return
         */
         public ListNode removeElements3(ListNode head, int val) {
               ListNode dummyHead = new ListNode(0);
               dummyHead.next = head;
               ListNode temp = dummyHead;
               while(temp.next != null) {
                   if(temp.next.val == val) temp.next = temp.next.next;
                   else temp = temp.next;
               }
               return dummyHead.next;
         }


        /**
         *一个直观的做法是：写一个递归函数来将某个值为 val 的节点从链表中移除。
         * 由于是单链表，无法通过某个节点直接找到「前一个节点」，
         * 因此为了方便，我们可以为递归函数多设置一个入参，代表「前一个节点」。
         * @param head
         * @param val
         * @return
         */
         public ListNode removeElement4(ListNode head, int val) {
               ListNode dummy = new ListNode(-1);
               dummy.next = head;
               dfs(dummy, dummy.next, val);
               return dummy.next;
         }
         void dfs(ListNode prev, ListNode root, int val) {
             if (root == null) return;
             if (root.val == val) {
                 prev.next = root.next;
             } else {
                 prev = root;
             }
             dfs(prev, prev.next, val);
         }


        /**
         * 迭代
         * @param head
         * @param val
         * @return
         */
         public ListNode removeElements01(ListNode head, int val) {
             ListNode dummy = new ListNode(-1);
             dummy.next = head;
             ListNode tmp = dummy.next, prev = dummy;
             while(tmp != null) {
                 if(tmp.val == val) prev.next = tmp.next;
                 else prev = tmp;
                 tmp = tmp.next;
             }
             return dummy.next;
         }

         public ListNode removeElements02(ListNode head, int val) {
             ListNode dummy = new ListNode(-1);
             dummy.next = head;
             for(ListNode tmp = dummy.next, prev = dummy; tmp != null; tmp = tmp.next) {
                 if(tmp.val == val) prev.next = tmp.next;
                 else prev = tmp;
             }
             return dummy.next;
         }

        /**
         *将值不等于 val 的节点依次压入栈中；
         * 将压入栈的节点重新连接，栈底的节点作为新的头节点返回。
         * @param head
         * @param val
         * @return
         */
         public ListNode removeElements03(ListNode head, int val) {
             Stack<ListNode> stack = new Stack<ListNode>();
             while( head != null) {
                 if(head.val != val) stack.push(head);
                 head = head.next;
             }
             while(!stack.isEmpty()) {
                 stack.peek().next = head;
                 head = stack.pop();
             }
             return head;
         }

        /**
         * 设置两个均指向头节点的指针，pre（记录待删除节点的前一节点）和 cur (记录当前节点)；
         * 遍历整个链表，查找节点值为 val 的节点，找到即删除该节点，否则继续查找。
         * 1 找到，将当前节点的前一节点（之前最近一个值不等于 val 的节点(pre)）连接到当前节点（cur）的下一个节点（
         * 即将 pre 的下一节点指向 cur 的下一节点：pre->next = cur->next）。
         * 2 没找到，更新最近一个值不等于 val 的节点（即 pre = cur），并继续遍历（cur = cur->next）。
         * @param head
         * @param val
         * @return
         */
         public ListNode removeElements04(ListNode head, int val) {
             while(null != head && head.val == val) head = head.next;
             ListNode cur = head;
             ListNode pre = head;
             while(cur != null) {
                 if(cur.val == val) pre.next = cur.next;
                 else pre = cur;
                 cur = cur.next;
             }
             return head;
         }
    }
}

package src.reverse_LinkedList_206;

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
         *成环了
         * @param head
         * @return
         */
        public ListNode reverseList(ListNode head) {

              if(head == null) return head;
              Stack<ListNode> stack = new Stack<>();
              while(head != null) {
                  stack.push(head);
                  head = head.next;
              }
              ListNode dummyHead = new ListNode(-1);
              ListNode ptr = stack.pop();
              dummyHead.next = ptr;

              while(!stack.isEmpty()) {
                  ptr.next = stack.pop();
                  ptr = ptr.next;
              }
    //成环了，少了这一行
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


        /**
         * 假设链表为1→2→3→∅，我们想要把它改成 ∅←1←2←3
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         * 在遍历链表时，将当前节点的 next 指针改为指向前一个节点。
         * 由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用。

         * @param head
         * @return
         */
        public ListNode reverseList2(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while(curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }


        /**
         * 假设链表为
         * N1->N2...->N(k-1)->N(k)-> N(k+1)->...->N(m)->∅
         * 若从节点N(k+1)到N(m)已经被反转，而我们正处于N(k)
         * N1->N2...->N(k-1)->N(k)-> N(k+1)<-...<-N(m)
         * 我们希望N(k+1)的下一节点指向N(k)
         * 所以N(k).next.next = N(k)
         * 需要注意的是N1的下一个节点必须指向∅。如果忽略了这一点，链表中可能会产生环。
         * 时间复杂度：O(n)其中 n为链表的长度。需要对链表的每个节点进行反转操作。
         * 空间复杂度：O(n)
         * @param head
         * @return
         */
        public ListNode reverseList3(ListNode head) {
            if(head == null || head.next == null) return head;
            ListNode newHead = reverseList3(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }


        /**
         * 栈实现
         * @param head
         * @return
         */
        public ListNode reverseList4(ListNode head) {
            Stack<ListNode> stack = new Stack<>();
            ListNode dummyHead = new ListNode(-1);
            ListNode ptr = dummyHead;
            while( head != null) {
                stack.push(head);
                head = head.next;
            }
            while(!stack.isEmpty()) {
                ptr.next = stack.pop();
                ptr = ptr.next;
            }
            ptr.next = null;
            return dummyHead.next;

        }


        // TODO: 2022/11/17 不太懂 
        /**
         * 头插法
          * @param head
         * @return
         */
        public ListNode reverseList5(ListNode head) {
            ListNode dummy = new ListNode(0);
            ListNode p = dummy, cur = head;
            while(cur != null) {
                //从head摘下一个头
                ListNode t = cur;
                cur = cur.next;//从cur移动到下一个
                t.next = p.next;//头插法插入
                p.next = t;
            }
            return dummy.next;
        }


// TODO: 2022/11/17 不懂 

        public ListNode reverseList6(ListNode head) {
            return reverse(null,head);
        }

        private  ListNode reverse(ListNode pre,ListNode cur){
            if(cur==null) return pre;
            ListNode next = cur.next;
            cur.next = pre;
            return reverse(cur,next);
        }





    }
}

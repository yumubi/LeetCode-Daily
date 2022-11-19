package src.palindrome_LinkedList_234;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public boolean isPalindrome(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while(head != null) {
            sb.append(head.val);
            head = head.next;
        }
        return sb.toString().equals(sb.reverse().toString());
    }


    /**
     * 第一步： 遍历链表并将值复制到数组中，O(n)。
     * 第二步：双指针判断是否为回文，执行了 O(n/2) 次的判断，即 O(n)。
     * 总的时间复杂度：O(2n) = O(n)。
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        ListNode currentNode = head;
        while(currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        int front = 0;
        int back = vals.size() - 1;
        while(front < back) {
            if(!vals.get(front).equals(vals.get(back))) return false;
            front++;
            back--;
        }
        return true;
    }


    /**
     * @param head
     * 整个流程可以分为以下五个步骤：
     * 找到前半部分链表的尾节点。
     * 反转后半部分链表。
     * 判断是否回文。
     * 恢复链表。
     * 返回结果。
     *
     * 执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。
     * 我们也可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。
     * 当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
     * 若链表有奇数个节点，则中间的节点应该看作是前半部分。
     * 步骤二可以使用「206. 反转链表」问题中的解决方法来反转链表的后半部分。
     * 步骤三比较两个部分的值，当后半部分到达末尾则比较完成，可以忽略计数情况中的中间节点。
     * 步骤四与步骤二使用的函数相同，再反转一次恢复链表本身。
     * @return
     */
    public boolean isPalindrome3(ListNode head) {
        if(head == null) return true;

        //找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while(result && p2 != null) {
            if(p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        //还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }




  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * currentNode 指针是先到尾节点，由于递归的特性再从后往前进行比较。frontPointer 是递归函数外的指针。
     * 若 currentNode.val != frontPointer.val 则返回 false。反之，frontPointer 向前移动并返回 true。
     * 算法的正确性在于递归处理节点的顺序是相反的（回顾上面打印的算法），
     * 而我们在函数外又记录了一个变量，因此从本质上，我们同时在正向和逆向迭代匹配。
     */
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome4(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }


///////////////////////////////////////////////////////////////////////////////////

    /**
     * 哈希法
     * @param head
     * @return
     */
    public boolean isPalindrome5(ListNode head) {
    ListNode t = head;
    int base = 11, mod = 1000000007;
    int left = 0, right = 0, mul = 1;
    while(t!=null){
        left = (int) (((long) left * base + t.val) % mod);
        right = (int) ((right + (long) mul * t.val) % mod);
        mul = (int) ((long) mul * base % mod);
        t=t.next;
    }
    return left==right;
}



//////////////////////////////////////////////////////////////////////

    /**
     * 逆序打印链表
     * @param head
     */
    private void printListNode(ListNode head) {
    if (head == null)
        return;
    printListNode(head.next);
    System.out.println(head.val);
}



    ListNode temp;

    public boolean isPalindrome02(ListNode head) {
        temp = head;
        return check(head);
    }

    private boolean check(ListNode head) {
        if (head == null)
            return true;
        boolean res = check(head.next) && (temp.val == head.val);
        temp = temp.next;
        return res;
    }



















//////////////////////////////////////////////////////////////

    /**
     * 使用栈解决、
     * @param head
     * @return
     */
    public boolean isPalindrome01(ListNode head) {
        if (head == null)
            return true;
        ListNode temp = head;
        Stack<Integer> stack = new Stack();
        //链表的长度
        int len = 0;
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
            len++;
        }
        //len长度除以2
        len >>= 1;
        //然后再出栈
        while (len-- >= 0) {
            if (head.val != stack.pop())
                return false;
            head = head.next;
        }
        return true;
    }



























    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;
        System.out.println(isPalindrome(head));
    }
}

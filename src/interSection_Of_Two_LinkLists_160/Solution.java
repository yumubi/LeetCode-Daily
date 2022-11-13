package src.interSection_Of_Two_LinkLists_160;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * 超时了，不知道对不对
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ptrA = headA;
        ListNode ptrB = headB;
        while(true) {
            int cnt = 0;
            while (ptrA.next != null && ptrB != null) {
              if(ptrA.next == ptrB.next) return ptrA.next;
                ptrA = ptrA.next;
                ptrB = ptrB.next;
            }
            if(cnt == 2) return null;
            if (ptrA.next == null)
            {
                ptrA.next = headB;
                cnt++;
            }
            if (ptrB.next == null){
                ptrB.next = headA;
                cnt++;
            }
        }
    }


    /**
     * 首先遍历链表headA，并将链表headA 中的每个节点加入哈希集合中。然后遍历链表headB，对
     * 于遍历到的每个节点，判断该节点是否在哈希集合中：
     * 如果当前节点不在哈希集合中，则继续遍历下一个节点；
     * 如果当前节点在哈希集合中，则后面的节点都在哈希集合中，即从当前节点开始的所有节点都在两个链表的相交部分，
     * 因此在链表 headB 中遍历到的第一个在哈希集合中的节点就是两个链表相交的节点，返回该节点。
     * 如果链表headB 中的所有节点都不在哈希集合中，则两个链表不相交，返回null。
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while(temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while(temp != null) {
            if(visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 双指针解法
     * 当链表 headA 和 headB 都不为空时，创建两个指针 pA和pB，初始时分别指向两个链表的头节点 headA 和 headB，
     * 然后将两个指针依次遍历两个链表的每个节点。具体做法如下：
     * 每步操作需要同时更新指针 pA 和 pB。
     * 如果指针 pA 不为空，则将指针 pA 移到下一个节点；如果指针 pB 不为空，则将指针 pB 移到下一个节点。
     * 如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针pB 为空，则将指针 pB 移到链表 headA 的头节点。
     * 当指针pA 和pB 指向同一个节点或者都为空时，返回它们指向的节点或者 null。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while(pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public ListNode getIntersectionNode4(ListNode a, ListNode b) {
        for(ListNode h1 = a; h1 != null; h1 = h1.next) {
            for(ListNode h2 = b; h2 != null; h2 = h2.next)
                if(h1.equals(h2)) return h1;
        }
        return null;
    }


    /**
     *  将两条链表分别压入两个栈中，然后循环比较两个栈的栈顶元素，同时记录上一位栈顶元素。
     * 当遇到第一个不同的节点时，结束循环，上一位栈顶元素即是答案。
     * @param a
     * @param b
     * @return
     */
    public ListNode getIntersectionNode5(ListNode a, ListNode b) {
        Deque<ListNode> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        while(a != null) {
            d1.addLast(a);
            a = a.next;
        }
        while(b != null) {
            d2.addLast(b);
            b = b.next;
        }
        ListNode ans = null;
        while(!d1.isEmpty() && !d2.isEmpty() && d1.peekLast().equals(d2.peekLast())) {
            ListNode c1 = d1.pollLast(), c2 = d2.pollLast();
            ans = c1;
        }
        return ans;
    }


    /**
     * 先对两条链表扫描一遍，取得两者长度，
     * 然后让长的链表先走「两者的长度差值」，然后再同时走，遇到第一个节点即是答案
     * @param a
     * @param b
     * @return
     */
    public ListNode getIntersectionNode6(ListNode a, ListNode b) {
        int c1 = 0, c2 = 0;
        ListNode t1 = a, t2 = b;
        while(t1 != null && ++c1 > 0) t1 = t1.next;
        while(t2 != null && ++c2 > 0) t2 = t2.next;
        int t = Math.abs(c1 - c2);
        while(t-- > 0) {
            if(c1 > c2) a = a.next;
            else b = b.next;
        }
        while(a != null && b != null) {
            if(a.equals(b)) {
                return a;
            } else {
                a = a.next;
                b = b.next;
            }
        }
        return null;
    }

}

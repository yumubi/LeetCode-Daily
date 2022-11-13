package src.linkedList_Cycle_141;

import java.lang.invoke.CallSite;
import java.util.HashSet;
import java.util.Set;

class ListNode{
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
           next = null;
       }
}

public class Solution {
    /**
     * ac了
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        do {
            if(fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        } while(slow != fast);
        return true;
    }

    /**
     * 使用哈希表来存储所有已经访问过的节点。
     * 每次我们到达一个节点，如果该节点已经存在于哈希表中，则说明该链表是环形链表，
     * 否则就将该节点加入哈希表中。重复这一过程，直到我们遍历完整个链表即可。
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();//set里存储的不是结点的值而是结点的指针，每个结点的内存地址是唯一的
        while (head != null) {
            if(!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * Floyd 判圈算法
     * 细节
     * 为什么我们要规定初始时慢指针在位置 head，快指针在位置 head.next，
     * 而不是两个指针都在位置 head（即与「乌龟」和「兔子」中的叙述相同）？
     * 观察下面的代码，我们使用的是 while 循环，循环条件先于循环体。
     * 由于循环条件一定是判断快慢指针是否重合，如果我们将两个指针初始都置于 head，
     * 那么 while 循环就不会执行。因此，我们可以假想一个在 head 之前的虚拟节点，慢指针从虚拟节点移动一步到达 head，
     * 快指针从虚拟节点移动两步到达 head.next，这样我们就可以使用 while 循环了。
     * 当然，我们也可以使用 do-while 循环。此时，我们就可以把快慢指针的初始值都置为 head。
     *   时间复杂度O(n)
     * 当链表中不存在环时，快指针将先于慢指针到达链表尾部，链表中每个节点至多被访问两次。
     * 当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 NN
     * @param head
     * @return
     */
    public boolean hasCycle3(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast) {
            if(fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    public boolean hasCycle3p(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }



    /**
     * try catch大法
     * 归根结底：存在循环链表则不存在空指针；不存在循环链表则一定有空指针
     * 检测有没有空指针异常即可
     * @param head
     * @return
     */
    public boolean hasCycle4(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true){
            try{
                slow = slow.next;
                fast = fast.next.next;
            }catch (Exception e){
                return false;
            }
            if (slow == fast)
                return true;
        }
    }



}

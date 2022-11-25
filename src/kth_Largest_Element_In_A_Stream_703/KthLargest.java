package src.kth_Largest_Element_In_A_Stream_703;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class KthLargest {


    /**
     * 暂时不知道哪里错了
     */
//    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
//
//    int ptr;
//    public KthLargest(int k, int[] nums) {
//        for (Integer num : nums) {
//            priorityQueue.add(num);
//        }
//        ptr = k;
//
//    }
//    public int add(int val) {
//        priorityQueue.add(val);
//        Iterator iterator = priorityQueue.iterator();
//        int count = 0;
//        while(count < ptr - 1) {
//               iterator.next();
//        }
//        return (int)iterator.next();
//    }


    /**
     * 优先队列
     * 我们可以使用一个大小为 k 的优先队列来存储前 k 大的元素，其中优先队列的队头为队列中最小的元素，也就是第 k大的元素。
     * 在单次插入的操作中，我们首先将元素 val 加入到优先队列中。如果此时优先队列的大小大于 k，
     * 我们需要将优先队列的队头元素弹出，以保证优先队列的大小为 k。
     * 时间复杂度：
     * 初始化时间复杂度为：O(nlogk) ，其中 n为初始化时 nums 的长度；
     * 单次插入时间复杂度为：O(logk)。
     * 空间复杂度：O(k)。需要使用优先队列存储前 k 大的元素。
     */
    PriorityQueue<Integer> pq;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>();
        for (int x : nums) add(x);
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) pq.poll();
        return pq.peek();
    }
}



//    @Test
//    void () {
//        int k = 3;
//        int[] arr = new int[] { 4, 5, 8, 2 };
//        KthLargest kthLargest = new KthLargest(k, arr);
//        assertEquals(4, kthLargest.add(3));
//        assertEquals(5, kthLargest.add(5));
//        assertEquals(5, kthLargest.add(10));
//        assertEquals(8, kthLargest.add(9));
//        assertEquals(8, kthLargest.add(4));
//    }



    //冒泡（TLE）
    // 每次调用 add 时先将数装入数组，然后遍历 k 次，通过找 k 次最大值来找到 Top K。
//    int k;
//    List<Integer> list = new ArrayList<>(10009);
//    public KthLargest(int _k, int[] _nums) {
//        k = _k;
//        for(int i : _nums) list.add(i);
//    }
//    public int add(int val) {
//        list.add(val);
//        int cur = 0;
//        for(int i =0; i < k; i++) {
//            int idx = findMax(cur, list.size() - 1);
//            swap(cur++, idx);
//        }
//        return list.get(cur - 1);
//    }
//    int findMax(int start, int end) {
//        int ans = 0, max = Integer.MIN_VALUE;
//        for(int i = start; i <= end; i++) {
//            int t = list.get(i);
//            if(t > max) {
//                max = t;
//                ans = i;
//            }
//        }
//        return ans;
//    }
//    void swap(int a, int b) {
//        int c = list.get(a);
//        list.set(a, list.get(b));
//        list.set(b, c);
//    }


    //快排
//    int k;
//    List<Integer> list = new ArrayList<>(10009);
//    public KthLargest(int _k, int[] _nums) {
//        k = _k;
//        for (int i : _nums) list.add(i);
//    }
//
//    public int add(int val) {
//        list.add(val);
//        Collections.sort(list);
//        return list.get(list.size() - k);
//
//}


/**
 * 使用优先队列构建一个容量为 k 的小根堆。
 * 将 nums 中的前 k 项放入优先队列（此时堆顶元素为前 k 项的最大值）。
 * 随后逐项加入优先队列：
 * 堆内元素个数达到 k 个：
 * 加入项小于等于堆顶元素：加入项排在第 k 大元素的后面。直接忽略
 * 加入项大于堆顶元素：将堆顶元素弹出，加入项加入优先队列，调整堆
 * 堆内元素个数不足 k 个，将加入项加入优先队列
 * 将堆顶元素进行返回（数据保证返回答案时，堆内必然有 k 个元素）：
 * 时间复杂度：最坏情况下，n 个元素都需要入堆。复杂度为 O(nlogk)
 * 空间复杂度：O(k)
 */
//    class KthLargest {
//        int k;
//        PriorityQueue<Integer> queue;
//        public KthLargest(int _k, int[] _nums) {
//            k = _k;
//            queue = new PriorityQueue<>(k, (a,b)->Integer.compare(a,b));
//            int n = _nums.length;
//            for (int i = 0; i < k && i < n; i++) queue.add(_nums[i]);
//            for (int i = k; i < n; i++) add(_nums[i]);
//        }
//        public int add(int val) {
//            int t = !queue.isEmpty() ? queue.peek() : Integer.MIN_VALUE;
//            if (val > t || queue.size() < k) {
//                if (!queue.isEmpty() && queue.size() >= k) queue.poll();
//                queue.add(val);
//            }
//            return queue.peek();
//        }
//    }

